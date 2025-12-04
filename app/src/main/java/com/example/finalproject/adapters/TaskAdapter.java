package com.example.finalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.models.TaskModel;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private Context context;
    private ArrayList<TaskModel> taskList;
    private OnTaskClickListener listener;

    public interface OnTaskClickListener {
        void onEdit(TaskModel task);
        void onDelete(TaskModel task);
        void onChecked(TaskModel task, boolean isChecked);
    }

    public TaskAdapter(Context context, ArrayList<TaskModel> taskList, OnTaskClickListener listener) {
        this.context = context;
        this.taskList = taskList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TaskModel task = taskList.get(position);

        holder.taskName.setText(task.getTitle()); // Gunakan getTitle()
        holder.taskDesc.setText(task.getDescription()); // Tampilkan deskripsi jika ada
        holder.taskDone.setChecked(task.getIsDone() == 1);

        // Tombol Edit & Delete
        // Kita cek null dulu biar aman kalau listener belum dipasang
        holder.btnEdit.setOnClickListener(v -> {
            if (listener != null) listener.onEdit(task);
        });
        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) listener.onDelete(task);
        });
        holder.taskDone.setOnCheckedChangeListener((button, checked) -> {
            if (listener != null) listener.onChecked(task, checked);
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskName, taskDesc;
        CheckBox taskDone;
        Button btnEdit, btnDelete;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            taskName = itemView.findViewById(R.id.textViewTaskName);
            taskDesc = itemView.findViewById(R.id.textViewDueDate); // Sementara pakai ID ini untuk deskripsi
            taskDone = itemView.findViewById(R.id.checkBoxIsDone);
            btnEdit = itemView.findViewById(R.id.btnEditTask);
            btnDelete = itemView.findViewById(R.id.btnDeleteTask);
        }
    }
}