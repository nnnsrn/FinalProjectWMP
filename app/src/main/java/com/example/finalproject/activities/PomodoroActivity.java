package com.example.finalproject.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;

public class PomodoroActivity extends AppCompatActivity {

    TextView tvTimer;
    Button btnStart, btnReset;

    CountDownTimer timer;
    long timeLeft = 1500000; // 25 minutes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);

        tvTimer = findViewById(R.id.tvTimer);
        btnStart = findViewById(R.id.btnStart);
        btnReset = findViewById(R.id.btnReset);

        updateDisplay();

        btnStart.setOnClickListener(v -> startPomodoro());
        btnReset.setOnClickListener(v -> {
            timeLeft = 1500000;
            if (timer != null) timer.cancel();
            updateDisplay();
        });
    }

    private void startPomodoro() {
        timer = new CountDownTimer(timeLeft, 1000) {
            public void onTick(long millis) {
                timeLeft = millis;
                updateDisplay();
            }

            public void onFinish() {
                tvTimer.setText("DONE!");
            }
        }.start();
    }

    private void updateDisplay() {
        int minutes = (int) (timeLeft / 60000);
        int seconds = (int) (timeLeft % 60000 / 1000);

        tvTimer.setText(String.format("%02d:%02d", minutes, seconds));
    }
}
