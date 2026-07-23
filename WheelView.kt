package com.yourdomain.typesofpeople

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.PI
import kotlin.random.Random

class WheelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    data class WheelSegment(
        val label: String,
        val color: Int, 
        val personalityId: Int      // References a personality in PersonalityManager
    )

    var segments: List<WheelSegment> = emptyList()
        set(value) {
            field = value
            segmentCount = value.size
            segmentPaints = Array(segmentCount) { index ->
                Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    color = value[index].color
                    style = Paint.Style.FILL
                }
            }
            anglePerSegment = if (segmentCount > 0) (2 * PI / segmentCount).toFloat() else 0f
            invalidate()
        }

    private var segmentPaints: Array<Paint> = emptyArray()
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#01212B")
        textSize = 28f
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }

    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.STROKE
        strokeWidth = 3f
    }

    private val centerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#F5FAFF")
        style = Paint.Style.FILL
    }

    private val centerStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#1C6679")
        style = Paint.Style.STROKE
        strokeWidth = 6f
    }

    private val dropPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 44f
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }

    private var segmentCount = 0
    private var anglePerSegment = 0f
    private var rotation = 0f
    private var isSpinning = false
    private var spinAnimator: ValueAnimator? = null

    private var downX = 0f
    private var downY = 0f
    private var isDragging = false
    private var dragRotation = 0f

    private var onSegmentSelectedListener: ((personalityId: Int) -> Unit)? = null

    init {
        isClickable = true
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (segments.isEmpty()) {
            canvas.drawText("No segments", width / 2f, height / 2f, textPaint)
            return
        }

        val width = width
        val height = height
        val size = minOf(width, height)
        val radius = size * 0.44f
        val centerX = width / 2f
        val centerY = height / 2f

        for (i in 0 until segmentCount) {
            val startAngle = i * anglePerSegment + rotation
            val endAngle = startAngle + anglePerSegment

            val path = Path().apply {
                moveTo(centerX, centerY)
                arcTo(
                    RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius),
                    Math.toDegrees(startAngle.toDouble()).toFloat(),
                    Math.toDegrees(anglePerSegment.toDouble()).toFloat()
                )
                close()
            }

            canvas.drawPath(path, segmentPaints[i])
            canvas.drawPath(path, strokePaint)
            val midAngle = startAngle + anglePerSegment / 2
            val labelRadius = radius * 0.68f
            val x = centerX + Math.cos(midAngle.toDouble()).toFloat() * labelRadius
            val y = centerY + Math.sin(midAngle.toDouble()).toFloat() * labelRadius

            canvas.save()
            canvas.translate(x, y)

            val rotationDeg = Math.toDegrees(midAngle.toDouble()).toFloat()
            if (rotationDeg > 90 && rotationDeg < 270) {
                canvas.rotate(rotationDeg + 180)
            } else {
                canvas.rotate(rotationDeg)
            }

            val labelText = segments[i].label
            canvas.drawText(labelText, 0f, 0f, textPaint)
            canvas.restore()
        }

        val centerRadius = radius * 0.12f
        canvas.drawCircle(centerX, centerY, centerRadius, centerPaint)
        canvas.drawCircle(centerX, centerY, centerRadius, centerStrokePaint)

        canvas.drawText(, centerX, centerY + 14f, dropPaint)

        drawDecorations(canvas, centerX, centerY, radius)
    }
    private fun drawDecorations(canvas: Canvas, cx: Float, cy: Float, radius: Float) {
        val dotCount = 20
        val dotRadius = 4f
        val dotPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor("#40FFFFFF")
        }

        for (i in 0 until dotCount) {
            val angle = (i / dotCount.toFloat()) * 2 * PI
            val dotX = cx + Math.cos(angle).toFloat() * (radius + 12f)
            val dotY = cy + Math.sin(angle).toFloat() * (radius + 12f)
            canvas.drawCircle(dotX, dotY, dotRadius, dotPaint)
        }
    }

    fun setRotation(rotation: Float) {
        this.rotation = rotation
        invalidate()
    }

    fun getRotation(): Float = rotation
    fun startSpin() {
        if (isSpinning || segments.isEmpty()) return
        isSpinning = true

        val extraRotations = (4 + Random.nextDouble() * 3).toFloat()
        val extraAngle = Random.nextDouble() * 2 * PI
        val targetRotation = rotation + extraRotations * (2 * PI).toFloat() + extraAngle.toFloat()

        spinAnimator = ValueAnimator.ofFloat(rotation, targetRotation).apply {
            duration = (3000 + Random.nextInt(1500)).toLong()

            interpolator = android.view.animation.DecelerateInterpolator(1.8f)

            addUpdateListener { animation ->
                val value = animation.animatedValue as Float
                rotation = value
                invalidate()
            }

            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    isSpinning = false
                    spinAnimator = null

                    val selectedId = getSelectedPersonalityId()
                    onSegmentSelectedListener?.invoke(selectedId)
                }
            })

            start()
        }
    }

    fun getSelectedPersonalityId(): Int {
        if (segments.isEmpty()) return -1

        val pointerAngle = -PI / 2
        var raw = (pointerAngle - rotation) % (2 * PI)
        if (raw < 0) raw += 2 * PI
        val index = (raw / anglePerSegment).toInt()
        val safeIndex = if (index >= segmentCount) 0 else index
        return segments[safeIndex].personalityId
    }

    fun stopSpin() {
        spinAnimator?.cancel()
        spinAnimator = null
        isSpinning = false
    }
    fun isSpinning(): Boolean = isSpinning

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (isSpinning) return true

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                isDragging = true
                dragRotation = rotation
                parent?.requestDisallowInterceptTouchEvent(true)
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                if (isDragging) {
                    val centerX = width / 2f
                    val centerY = height / 2f
                    val angle = Math.atan2(
                        (event.y - centerY).toDouble(),
                        (event.x - centerX).toDouble()
                    ).toFloat()

                    val downAngle = Math.atan2(
                        (downY - centerY).toDouble(),
                        (downX - centerX).toDouble()
                    ).toFloat()

                    val deltaAngle = angle - downAngle
                    rotation = dragRotation + deltaAngle
                    invalidate()
                }
                return true
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                isDragging = false
                parent?.requestDisallowInterceptTouchEvent(false)

                val dx = event.x - downX
                val dy = event.y - downY
                val distance = Math.sqrt((dx * dx + dy * dy).toDouble())

                if (distance < 20) {
                    startSpin()
                }
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    fun setOnSegmentSelectedListener(listener: (personalityId: Int) -> Unit) {
        onSegmentSelectedListener = listener
    }
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        spinAnimator?.cancel()
        spinAnimator = null
    }
    fun resetRotation() {
        stopSpin()
        rotation = 0f
        invalidate()
    }

    fun getCurrentPersonalityId(): Int {
        return if (segments.isNotEmpty()) segments[0].personalityId else -1
    }
}