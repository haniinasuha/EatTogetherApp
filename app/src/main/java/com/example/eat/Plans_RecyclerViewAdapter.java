package com.example.eat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Plans_RecyclerViewAdapter extends RecyclerView.Adapter<Plans_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    PlanViewModel activePlans;
    public Plans_RecyclerViewAdapter(Context context, PlanViewModel activePlans){
        this.context = context;
        this.activePlans = activePlans;
    }
    @NonNull
    @Override
    public Plans_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Plans_RecyclerViewAdapter.MyViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return activePlans.getSize();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //maybe fix this later
        ImageView profilePicture;
        TextView time;
        TextView location;
        TextView username;
        TextView desc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.txt_active_time);
            location = itemView.findViewById(R.id.txt_active_location);
            username = itemView.findViewById(R.id.txt_active_user);
            desc = itemView.findViewById(R.id.txt_active_description);

        }
    }
}
