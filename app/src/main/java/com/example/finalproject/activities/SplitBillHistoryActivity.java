package com.example.finalproject.activities;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.finalproject.R;
import com.example.finalproject.adapters.SplitBillAdapter;
import com.example.finalproject.database.DBHelper;
import com.example.finalproject.models.SplitBillModel;

public class SplitBillHistoryActivity extends AppCompatActivity {

    RecyclerView rvHistory;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_bill_history);

        rvHistory = findViewById(R.id.rvSplitHistory);
        db = new DBHelper(this);

        loadHistory();
    }

    private void loadHistory() {
        ArrayList<SplitBillModel> list = new ArrayList<>();
        Cursor c = db.getSplitBillHistory();

        while (c.moveToNext()) {
            list.add(new SplitBillModel(
                    c.getInt(0),
                    c.getDouble(1),
                    c.getInt(2),
                    c.getDouble(3)
            ));
        }

        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(new SplitBillAdapter(this, list));
    }
}
