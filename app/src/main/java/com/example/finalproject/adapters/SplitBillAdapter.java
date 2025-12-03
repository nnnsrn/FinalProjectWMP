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
import com.example.finalproject.models.SplitBillModel;

import java.util.ArrayList;

public class SplitBillAdapter extends RecyclerView.Adapter<SplitBillAdapter.SplitBillViewHolder> {

    private Context context;
    private ArrayList<SplitBillModel> list;
    private OnSplitBillClickListener listener;

    public interface OnSplitBillClickListener {
        void onEdit(SplitBillModel model);
        void onDelete(SplitBillModel model);
    }

    public SplitBillAdapter(Context context, ArrayList<SplitBillModel> list, OnSplitBillClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SplitBillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_split_bill, parent, false);
        return new SplitBillViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SplitBillViewHolder holder, int position) {
        SplitBillModel model = list.get(position);

        holder.desc.setText(model.getDescription());
        holder.total.setText("Total: Rp" + model.getTotalAmount());
        holder.perPerson.setText("Each: Rp" + model.getPerPersonAmount());
        holder.people.setText("People: " + model.getNumberOfPeople());
        holder.date.setText(model.getDate());

        holder.btnEdit.setOnClickListener(v -> listener.onEdit(model));
        holder.btnDelete.setOnClickListener(v -> listener.onDelete(model));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class SplitBillViewHolder extends RecyclerView.ViewHolder {

        TextView desc, total, perPerson, people, date;
        Button btnEdit, btnDelete;

        public SplitBillViewHolder(@NonNull View itemView) {
            super(itemView);

            desc = itemView.findViewById(R.id.textViewDescription);
            total = itemView.findViewById(R.id.textViewTotalAmount);
            perPerson = itemView.findViewById(R.id.textViewPerPersonAmount);
            people = itemView.findViewById(R.id.textViewNumberOfPeople);
            date = itemView.findViewById(R.id.textViewDate);
            btnEdit = itemView.findViewById(R.id.btnEditSplitBill);
            btnDelete = itemView.findViewById(R.id.btnDeleteSplitBill);
        }
    }
}
