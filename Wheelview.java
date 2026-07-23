package com.yourdomain.wheelofwater;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class WheelView extends View {
    private Paint[] segmentPaints;
    private Paint textPaint;
    private Paint strokePaint;
    private float rotation = 0;
    private String[] segmentLabels = {"1", "2", "3", "5", "10", "15", "20", "30", "50", "100"};
    private int[] segmentColors = {
        Color.parseColor("#B3E0F0"),
        Color.parseColor("#9AD4E8"),
        Color.parseColor("#7FC8DF"),
        Color.parseColor("#64BCD6"),
        Color.parseColor("#4AAFCC"),
        Color.parseColor("#2FA3C3"),
        Color.parseColor("#1A96B9"),
        Color.parseColor("#0F8AAE"),
        Color.parseColor("#057DA2"),
        Color.parseColor("#006F94")
    };
    private int segmentCount = 10;

    public WheelView(Context context) {
        super(context);
        init();
    }

    public WheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        segmentPaints = new Paint[segmentCount];
        for (int i = 0; i < segmentCount; i++) {
            segmentPaints[i] = new Paint(Paint.ANTI_ALIAS_FLAG);
            segmentPaints[i].setColor(segmentColors[i]);
            segmentPaints[i].setStyle(Paint.Style.FILL);
        }

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.parseColor("#01212B"));
        textPaint.setTextSize(28);
        textPaint.setTextAlign(Paint.Align.CENTER);

        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        strokePaint.setColor(Color.WHITE);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height);
        float radius = size * 0.44f;
        float centerX = width / 2f;
        float centerY = height / 2f;
        
        float anglePerSegment = (float) (2 * Math.PI / segmentCount);
        
        for (int i = 0; i < segmentCount; i++) {
            float startAngle = i * anglePerSegment + rotation;
            float endAngle = startAngle + anglePerSegment;
            
            Path path = new Path();
            path.moveTo(centerX, centerY);
            path.arcTo(new RectF(centerX - radius, centerY - radius, 
                                 centerX + radius, centerY + radius), 
                       (float) Math.toDegrees(startAngle), 
                       (float) Math.toDegrees(anglePerSegment));
            path.close();
            
            canvas.drawPath(path, segmentPaints[i]);
            canvas.drawPath(path, strokePaint);
            
            // Draw label
            float midAngle = startAngle + anglePerSegment / 2;
            float labelRadius = radius * 0.72f;
            float x = centerX + (float) Math.cos(midAngle) * labelRadius;
            float y = centerY + (float) Math.sin(midAngle) * labelRadius;
            
            canvas.save();
            canvas.translate(x, y);
            canvas.rotate((float) Math.toDegrees(midAngle));
            canvas.drawText(segmentLabels[i], 0, 0, textPaint);
            canvas.restore();
        }
        
        Paint centerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        centerPaint.setColor(Color.parseColor("#F5FAFF"));
        centerPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, radius * 0.13f, centerPaint);
        
        Paint centerStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        centerStroke.setColor(Color.parseColor("#1C6679"));
        centerStroke.setStyle(Paint.Style.STROKE);
        centerStroke.setStrokeWidth(6);
        canvas.drawCircle(centerX, centerY, radius * 0.13f, centerStroke);
        
        textPaint.setTextSize(40);
        canvas.drawText( centerX, centerY + 12, textPaint);
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
        invalidate();
    }

    public float getRotation() {
        return rotation;
    }
}