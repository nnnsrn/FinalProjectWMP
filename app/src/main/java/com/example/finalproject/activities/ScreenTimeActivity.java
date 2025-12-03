package com.example.finalproject.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.finalproject.R;
import com.example.finalproject.adapters.ScreenTimeAdapter;
import com.example.finalproject.database.DBHelper;
import com.example.finalproject.models.ScreenTimeModel;

public class ScreenTimeActivity extends AppCompatActivity {

    Chronometer chronometer;
    Button btnStart, btnStop;
    RecyclerView rvHistory;

    DBHelper db;
    long pauseOffset;
    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_time);

        chronometer = findViewById(R.id.chronometer);
        btnStart = findViewById(R.id.btnStartScreen);
        btnStop = findViewById(R.id.btnStopScreen);
        rvHistory = findViewById(R.id.rvScreenHistory);

        db = new DBHelper(this);

        btnStart.setOnClickListener(v -> startTimer());
        btnStop.setOnClickListener(v -> stopTimer());

        loadHistory();
    }

    private void startTimer() {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    private void stopTimer() {
        if (running) {
            chronometer.stop();
            long elapsed = SystemClock.elapsedRealtime() - chronometer.getBase();
            int seconds = (int) (elapsed / 1000);

            String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            db.addScreenTime(date, seconds);

            pauseOffset = 0;
            running = false;

            loadHistory();
        }
    }

    private void loadHistory() {
        ArrayList<ScreenTimeModel> list = new ArrayList<>();
        Cursor c = db.getScreenTime();

        while (c.moveToNext()) {
            list.add(new ScreenTimeModel(
                    c.getInt(0),
                    c.getString(1),
                    c.getInt(2)
            ));
        }

        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(new ScreenTimeAdapter(this, list));
    }
}
