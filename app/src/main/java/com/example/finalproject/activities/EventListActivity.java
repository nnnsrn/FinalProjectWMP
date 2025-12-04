package com.example.finalproject.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.finalproject.R;
import com.example.finalproject.adapters.EventAdapter;
import com.example.finalproject.database.DBHelper;
import com.example.finalproject.models.EventModel;

public class EventListActivity extends AppCompatActivity {

    RecyclerView rvEvents;
    Button btnAddEvent;
    DBHelper db;
    ArrayList<EventModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        // ID harus sesuai XML
        rvEvents = findViewById(R.id.recyclerViewEvents);
        btnAddEvent = findViewById(R.id.btnAddEvent);
        db = new DBHelper(this);

        loadEvents();

        btnAddEvent.setOnClickListener(v ->
                startActivity(new Intent(this, AddEventActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadEvents();
    }

    private void loadEvents() {
        list = new ArrayList<>();
        Cursor c = db.getAllEvents();

        while (c.moveToNext()) {
            // PERBAIKAN DI SINI:
            // Kita ambil 4 data sesuai urutan kolom di DBHelper (id, eventName, eventDate, notes)
            list.add(new EventModel(
                    c.getInt(0),      // id
                    c.getString(1),   // eventName
                    c.getString(2),   // eventDate
                    c.getString(3)    // notes (INI YANG TADI KURANG)
            ));
        }

        rvEvents.setLayoutManager(new LinearLayoutManager(this));

        // Parameter ke-3 null karena kita belum pasang listener klik
        rvEvents.setAdapter(new EventAdapter(this, list, null));
    }
}