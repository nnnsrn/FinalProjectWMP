package com.example.finalproject.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.database.DBHelper;

public class AddTaskActivity extends AppCompatActivity {

    EditText etTitle, etDesc;
    Button btnSave;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        etTitle = findViewById(R.id.etTitle);
        etDesc  = findViewById(R.id.etDesc);
        btnSave = findViewById(R.id.btnSave);
        db = new DBHelper(this);

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String desc = etDesc.getText().toString();

            if (db.addTask(title, desc)) {
                Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
