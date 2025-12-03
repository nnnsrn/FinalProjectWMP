package com.example.finalproject.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.database.DBHelper;

import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity {

    private EditText editTextEventName, editTextEventDate, editTextNotes;
    private Button btnSaveEvent;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        dbHelper = new DBHelper(this);

        editTextEventName = findViewById(R.id.editTextEventName);
        editTextEventDate = findViewById(R.id.editTextEventDate);
        editTextNotes = findViewById(R.id.editTextNotes);
        btnSaveEvent = findViewById(R.id.btnSaveEvent);

        // Date Picker for Event Date
        editTextEventDate.setOnClickListener(v -> showDatePicker());

        // Save Button listener
        btnSaveEvent.setOnClickListener(v -> saveEvent());
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dp = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    editTextEventDate.setText(date);
                },
                year, month, day
        );
        dp.show();
    }

    private void saveEvent() {
        String name = editTextEventName.getText().toString().trim();
        String date = editTextEventDate.getText().toString().trim();
        String notes = editTextNotes.getText().toString().trim();

        if (name.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "Event name and date cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean inserted = dbHelper.insertEvent(name, date, notes);

        if (inserted) {
            Toast.makeText(this, "Event saved!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Failed to save event", Toast.LENGTH_SHORT).show();
        }
    }
}
