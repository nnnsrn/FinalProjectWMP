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

        rvHistory = findViewById(R.id.recyclerViewSplitBillHistory);
        db = new DBHelper(this);

        loadHistory();
    }

    private void loadHistory() {
        ArrayList<SplitBillModel> list = new ArrayList<>();
        Cursor c = db.getSplitBillHistory();

        while (c.moveToNext()) {
            // Urutan index kolom sesuai DBHelper.onCreate
            // 0:id, 1:desc, 2:date, 3:total, 4:count, 5:result
            list.add(new SplitBillModel(
                    c.getInt(0),        // id
                    c.getString(1),     // description
                    c.getString(2),     // date
                    c.getDouble(3),     // totalAmount
                    c.getInt(4),        // personCount
                    c.getDouble(5)      // resultPerPerson
            ));
        }

        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(new SplitBillAdapter(this, list, null));
    }
}