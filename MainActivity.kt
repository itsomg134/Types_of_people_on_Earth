<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/background_gradient_start"
    android:padding="16dp">

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fillViewport="true">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical"
            android:gravity="center_horizontal"
            android:padding="8dp">

            <!-- Title -->
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:textSize="28sp"
                android:textStyle="bold"
                android:textColor="@color/white"
                android:layout_marginTop="16dp"
                android:layout_marginBottom="16dp"
                android:shadowColor="#000000"
                android:shadowDx="2"
                android:shadowDy="2"
                android:shadowRadius="4" />

            <!-- Wheel Container -->
            <RelativeLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:maxWidth="500dp"
                android:minHeight="350dp">

                <com.yourdomain.wheelofwater.WheelView
                    android:id="@+id/wheelView"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:padding="8dp" />

                <!-- Pointer -->
                <ImageView
                    android:id="@+id/pointer"
                    android:layout_width="48dp"
                    android:layout_height="48dp"
                    android:layout_centerHorizontal="true"
                    android:layout_alignParentTop="true"
                    android:layout_marginTop="-12dp"
                    android:src="@drawable/ic_pointer"
                    android:contentDescription="Pointer" />

            </RelativeLayout>

            <!-- Score Panel -->
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal"
                android:gravity="center"
                android:background="@color/panel_background"
                android:padding="12dp"
                android:layout_marginTop="16dp"
                android:layout_marginBottom="16dp"
                android:radius="30dp">

                <LinearLayout
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:orientation="horizontal"
                    android:gravity="center">

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:textSize="24sp" />

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="SCORE"
                        android:textSize="14sp"
                        android:textColor="@color/white"
                        android:layout_marginStart="8dp" />

                    <TextView
                        android:id="@+id/scoreDisplay"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="0"
                        android:textSize="24sp"
                        android:textStyle="bold"
                        android:textColor="@color/score_text"
                        android:layout_marginStart="12dp"
                        android:background="@color/score_background"
                        android:paddingStart="16dp"
                        android:paddingEnd="16dp"
                        android:paddingTop="4dp"
                        android:paddingBottom="4dp"
                        android:radius="20dp" />

                </LinearLayout>

                <LinearLayout
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:orientation="horizontal"
                    android:gravity="center">

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:textSize="24sp" />

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="LAST"
                        android:textSize="14sp"
                        android:textColor="@color/white"
                        android:layout_marginStart="8dp" />

                    <TextView
                        android:id="@+id/lastWinDisplay"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="—"
                        android:textSize="20sp"
                        android:textStyle="bold"
                        android:textColor="@color/last_win_text"
                        android:layout_marginStart="8dp"
                        android:background="@color/last_win_background"
                        android:paddingStart="16dp"
                        android:paddingEnd="16dp"
                        android:paddingTop="4dp"
                        android:paddingBottom="4dp"
                        android:radius="20dp" />

                </LinearLayout>

            </LinearLayout>

            <!-- Action Buttons -->
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal"
                android:gravity="center"
                android:layout_marginTop="8dp"
                android:layout_marginBottom="8dp">

                <Button
                    android:id="@+id/spinButton"
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="2"
                    android:text="🎡 SPIN"
                    android:textSize="20sp"
                    android:textStyle="bold"
                    android:backgroundTint="@color/spin_button"
                    android:textColor="@color/button_text_dark"
                    android:padding="16dp"
                    android:layout_marginEnd="8dp"
                    android:radius="30dp"
                    android:elevation="6dp" />

                <Button
                    android:id="@+id/resetButton"
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="⟳ RESET"
                    android:textSize="16sp"
                    android:textStyle="bold"
                    android:backgroundTint="@color/reset_button"
                    android:textColor="@color/white"
                    android:padding="16dp"
                    android:layout_marginStart="8dp"
                    android:radius="30dp"
                    android:elevation="4dp" />

            </LinearLayout>

            <!-- Hint -->
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Click wheel or press SPIN"
                android:textSize="14sp"
                android:textColor="@color/hint_text"
                android:background="@color/hint_background"
                android:paddingStart="20dp"
                android:paddingEnd="20dp"
                android:paddingTop="6dp"
                android:paddingBottom="6dp"
                android:radius="30dp"
                android:layout_marginTop="8dp" />

        </LinearLayout>

    </ScrollView>

</RelativeLayout>