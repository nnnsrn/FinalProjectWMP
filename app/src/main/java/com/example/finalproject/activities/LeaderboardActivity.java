package com.example.finalproject.activities;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.finalproject.R;
import com.example.finalproject.adapters.LeaderboardAdapter;
import com.example.finalproject.database.DBHelper;
import com.example.finalproject.models.LeaderboardModel;

public class LeaderboardActivity extends AppCompatActivity {

    RecyclerView rvLeaderboard;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        rvLeaderboard = findViewById(R.id.rvLeaderboard);
        db = new DBHelper(this);

        loadData();
    }

    private void loadData() {
        ArrayList<LeaderboardModel> list = new ArrayList<>();
        Cursor c = db.getLeaderboard();

        while (c.moveToNext()) {
            list.add(new LeaderboardModel(
                    c.getInt(0),
                    c.getString(1),
                    c.getInt(2)
            ));
        }

        rvLeaderboard.setLayoutManager(new LinearLayoutManager(this));
        rvLeaderboard.setAdapter(new LeaderboardAdapter(this, list));
    }
}
