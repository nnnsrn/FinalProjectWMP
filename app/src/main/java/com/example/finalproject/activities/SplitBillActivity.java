package com.example.finalproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.database.DBHelper;

public class SplitBillActivity extends AppCompatActivity {

    EditText etTotal, etPerson;
    Button btnCalculate, btnHistory;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_bill);

        etTotal = findViewById(R.id.etTotal);
        etPerson = findViewById(R.id.etPerson);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnHistory = findViewById(R.id.btnHistory);

        db = new DBHelper(this);

        btnCalculate.setOnClickListener(v -> {
            double total = Double.parseDouble(etTotal.getText().toString());
            int persons = Integer.parseInt(etPerson.getText().toString());
            double each = total / persons;

            db.addSplitBill(total, persons, each);

            Toast.makeText(this, "Each person pays: " + each, Toast.LENGTH_LONG).show();
        });

        btnHistory.setOnClickListener(v ->
                startActivity(new Intent(this, SplitBillHistoryActivity.class)));
    }
}
