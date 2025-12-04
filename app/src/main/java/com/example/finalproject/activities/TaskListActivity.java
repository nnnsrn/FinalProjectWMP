package com.example.finalproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.adapters.TaskAdapter;
import com.example.finalproject.models.TaskModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity {

    RecyclerView rvTasks;
    Button btnAdd;
    FirebaseFirestore db;
    ArrayList<TaskModel> list;
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        rvTasks = findViewById(R.id.recyclerViewTasks); // Pastikan ID ini sesuai xml (kadang rvTasks/recyclerViewTasks)
        btnAdd = findViewById(R.id.btnAddTask);

        db = FirebaseFirestore.getInstance();
        list = new ArrayList<>();

        // Setup RecyclerView
        // Note: Kita kasih 'null' dulu ke listener karena belum kita bahas fitur edit/hapus di tahap ini
        adapter = new TaskAdapter(this, list, null);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTasks.setAdapter(adapter);

        // Panggil fungsi untuk memantau data Firebase
        listenToFirebase();

        btnAdd.setOnClickListener(v ->
                startActivity(new Intent(this, AddTaskActivity.class)));
    }

    private void listenToFirebase() {
        // Ini akan berjalan otomatis setiap ada perubahan data di Cloud
        db.collection("tasks")
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        return;
                    }

                    list.clear();
                    for (QueryDocumentSnapshot doc : value) {
                        // Mengubah data JSON firebase jadi Java Object
                        TaskModel task = doc.toObject(TaskModel.class);
                        task.setId(doc.getId()); // Simpan ID dokumennya
                        list.add(task);
                    }
                    adapter.notifyDataSetChanged();
                });
    }
}