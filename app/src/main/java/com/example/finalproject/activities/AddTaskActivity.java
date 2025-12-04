package com.example.finalproject.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
// Import yang wajib ada untuk Firebase Firestore
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddTaskActivity extends AppCompatActivity {

    // Deklarasi variabel view
    EditText etTitle, etDesc;
    Button btnSave;

    // Deklarasi variabel Firestore
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Menghubungkan variabel dengan XML
        // Pastikan ID di XML kamu benar (etTaskName/etTitle, dst).
        // Berdasarkan file xml kamu sebelumnya: editTextTaskName, editTextPoints (tapi di java kamu pakai etTitle/etDesc)
        // Saya sesuaikan dengan XML 'activity_add_task.xml' kamu yang asli:

        etTitle = findViewById(R.id.editTextTaskName);
        // Note: Di XML kamu tidak ada EditText khusus deskripsi,
        // tapi di Java lama ada 'etDesc'. Kalau mau pakai 'Points' sebagai deskripsi sementara:
        etDesc  = findViewById(R.id.editTextPoints);

        btnSave = findViewById(R.id.btnSaveTask); // ID tombol di XML adalah btnSaveTask

        // Inisialisasi Firestore
        db = FirebaseFirestore.getInstance();

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String desc = etDesc.getText().toString(); // Ini akan mengambil isi Points jika mapping di atas dipakai

            if (title.isEmpty()) {
                Toast.makeText(this, "Task Name cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            // Siapkan data untuk dikirim ke Cloud
            Map<String, Object> task = new HashMap<>();
            task.put("title", title);
            task.put("description", desc);
            task.put("isDone", 0); // 0 = Belum selesai

            // Tambahan: Simpan points juga kalau mau
            // task.put("points", ...);

            // Kirim ke koleksi "tasks" di Firebase
            db.collection("tasks")
                    .add(task)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(this, "Task Added to Cloud!", Toast.LENGTH_SHORT).show();
                        finish(); // Tutup activity dan kembali ke list
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }
}