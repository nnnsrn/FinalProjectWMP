package com.example.finalproject.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.database.DBHelper;

import java.util.Calendar;

public class SplitBillActivity extends AppCompatActivity {

    EditText etTotal, etPerson, etDesc, etDate;
    Button btnCalculate, btnHistory;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_bill);

        etTotal = findViewById(R.id.editTextTotalAmount);
        etPerson = findViewById(R.id.editTextNumberOfPeople);
        // ID tambahan dari XML
        etDesc = findViewById(R.id.editTextDescription);
        etDate = findViewById(R.id.editTextDate);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnHistory = findViewById(R.id.btnViewHistory);

        db = new DBHelper(this);

        // Fitur Date Picker sederhana
        etDate.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, day) ->
                    etDate.setText(day + "/" + (month + 1) + "/" + year),
                    c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });

        btnCalculate.setOnClickListener(v -> {
            String totalStr = etTotal.getText().toString();
            String personStr = etPerson.getText().toString();
            String desc = etDesc.getText().toString();
            String date = etDate.getText().toString();

            if (totalStr.isEmpty() || personStr.isEmpty()) {
                Toast.makeText(this, "Isi Total & Jumlah Orang", Toast.LENGTH_SHORT).show();
                return;
            }

            double total = Double.parseDouble(totalStr);
            int persons = Integer.parseInt(personStr);
            double each = total / persons;

            // Simpan ke DB dengan data lengkap
            db.addSplitBill(desc, date, total, persons, each);

            Toast.makeText(this, "Per orang: " + each, Toast.LENGTH_LONG).show();
        });

        btnHistory.setOnClickListener(v ->
                startActivity(new Intent(this, SplitBillHistoryActivity.class)));
    }
}