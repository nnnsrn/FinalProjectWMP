package com.example.finalproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;

public class MainActivity extends AppCompatActivity {

    Button btnTodo, btnEvent, btnPomodoro, btnScreenTime, btnLeaderboard, btnSplitBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTodo = findViewById(R.id.btnTodo);
        btnEvent = findViewById(R.id.btnEvent);
        btnPomodoro = findViewById(R.id.btnPomodoro);
        btnScreenTime = findViewById(R.id.btnScreenTime);
        btnLeaderboard = findViewById(R.id.btnLeaderboard);
        btnSplitBill = findViewById(R.id.btnSplitBill);

        btnTodo.setOnClickListener(v -> startActivity(new Intent(this, TaskListActivity.class)));
        btnEvent.setOnClickListener(v -> startActivity(new Intent(this, EventListActivity.class)));
        btnPomodoro.setOnClickListener(v -> startActivity(new Intent(this, PomodoroActivity.class)));
        btnScreenTime.setOnClickListener(v -> startActivity(new Intent(this, ScreenTimeActivity.class)));
        btnLeaderboard.setOnClickListener(v -> startActivity(new Intent(this, LeaderboardActivity.class)));
        btnSplitBill.setOnClickListener(v -> startActivity(new Intent(this, SplitBillActivity.class)));
    }
}
