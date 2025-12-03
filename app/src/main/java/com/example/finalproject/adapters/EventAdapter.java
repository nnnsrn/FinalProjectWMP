package com.example.finalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.models.EventModel;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Context context;
    private ArrayList<EventModel> eventList;
    private OnEventClickListener listener;

    public interface OnEventClickListener {
        void onEdit(EventModel event);
        void onDelete(EventModel event);
    }

    public EventAdapter(Context context, ArrayList<EventModel> eventList, OnEventClickListener listener) {
        this.context = context;
        this.eventList = eventList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventModel event = eventList.get(position);

        holder.name.setText(event.getEventName());
        holder.date.setText("Date: " + event.getEventDate());
        holder.notes.setText("Notes: " + event.getNotes());

        holder.btnEdit.setOnClickListener(v -> listener.onEdit(event));
        holder.btnDelete.setOnClickListener(v -> listener.onDelete(event));
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        TextView name, date, notes;
        Button btnEdit, btnDelete;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textViewEventName);
            date = itemView.findViewById(R.id.textViewEventDate);
            notes = itemView.findViewById(R.id.textViewNotes);
            btnEdit = itemView.findViewById(R.id.btnEditEvent);
            btnDelete = itemView.findViewById(R.id.btnDeleteEvent);
        }
    }
}
