package com.example.eat;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.AdapterViewHolder> {

    Context context;
    ArrayList<Plan> list;

    public RecyclerViewAdapter(Context context, ArrayList<Plan> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {

        Plan plan = list.get(position);
        holder.name.setText(plan.getId());
        holder.date.setText(plan.getDate());
        holder.desc.setText(plan.getDescription());
        holder.spots.setText("Spots: " + plan.getSpots());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView name, desc, date, spots;
        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_displayName);
            date = itemView.findViewById(R.id.txt_displayDate);
            desc = itemView.findViewById(R.id.txt_displayDesc);
            spots = itemView.findViewById(R.id.txt_displaySpots);
        }
    }
}
