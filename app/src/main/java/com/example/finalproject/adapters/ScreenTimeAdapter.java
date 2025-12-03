package com.example.finalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.models.ScreenTimeModel;

import java.util.ArrayList;

public class ScreenTimeAdapter extends RecyclerView.Adapter<ScreenTimeAdapter.ScreenViewHolder> {

    private Context context;
    private ArrayList<ScreenTimeModel> list;

    public ScreenTimeAdapter(Context context, ArrayList<ScreenTimeModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ScreenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_screen_time, parent, false);
        return new ScreenViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ScreenViewHolder holder, int position) {
        ScreenTimeModel time = list.get(position);

        holder.date.setText(time.getDate());
        holder.minutes.setText(time.getMinutesUsed() + " min");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ScreenViewHolder extends RecyclerView.ViewHolder {

        TextView date, minutes;

        public ScreenViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.textViewDate);
            minutes = itemView.findViewById(R.id.textViewMinutesUsed);
        }
    }
}
