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
import com.example.finalproject.adapters.TaskAdapter;
import com.example.finalproject.database.DBHelper;
import com.example.finalproject.models.TaskModel;

public class TaskListActivity extends AppCompatActivity {

    RecyclerView rvTasks;
    Button btnAdd;
    DBHelper db;
    ArrayList<TaskModel> list;
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        rvTasks = findViewById(R.id.rvTasks);
        btnAdd = findViewById(R.id.btnAddTask);
        db = new DBHelper(this);

        loadTasks();

        btnAdd.setOnClickListener(v ->
                startActivity(new Intent(this, AddTaskActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTasks(); // refresh data
    }

    private void loadTasks() {
        list = new ArrayList<>();
        Cursor c = db.getAllTasks();

        while (c.moveToNext()) {
            list.add(new TaskModel(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getInt(3)
            ));
        }

        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskAdapter(this, list);
        rvTasks.setAdapter(adapter);
    }
}
