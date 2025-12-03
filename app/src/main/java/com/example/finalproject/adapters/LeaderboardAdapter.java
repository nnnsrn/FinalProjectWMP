package com.example.finalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.models.LeaderboardModel;

import java.util.ArrayList;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.LeaderViewHolder> {

    private Context context;
    private ArrayList<LeaderboardModel> data;

    public LeaderboardAdapter(Context context, ArrayList<LeaderboardModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public LeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_leaderboard, parent, false);
        return new LeaderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderViewHolder holder, int position) {
        LeaderboardModel model = data.get(position);

        holder.rank.setText("#" + (position + 1));
        holder.name.setText(model.getUsername());
        holder.score.setText(String.valueOf(model.getScore()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class LeaderViewHolder extends RecyclerView.ViewHolder {

        TextView rank, name, score;

        public LeaderViewHolder(@NonNull View itemView) {
            super(itemView);

            rank = itemView.findViewById(R.id.textViewRank);
            name = itemView.findViewById(R.id.textViewUsername);
            score = itemView.findViewById(R.id.textViewScore);
        }
    }
}
