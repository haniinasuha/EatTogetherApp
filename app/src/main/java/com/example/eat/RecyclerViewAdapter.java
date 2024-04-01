package com.example.eat;


import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        String userId = plan.getUserID();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserAccounts user = snapshot.getValue(UserAccounts.class);
                String name = user.getFirstName() + " " + user.getLastName();
                holder.name.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.spots.setText("Spots: " + plan.getSpots());
        holder.desc.setText(plan.getDescription());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PlanDetailsActivity.class);
                intent.putExtra("Id", plan.getId());
                intent.putExtra("mealType", plan.getMealType());
                intent.putExtra("time", plan.getTime());
                intent.putExtra("date", plan.getDate());
                intent.putExtra("spot", plan.getSpots());
                intent.putExtra("location", plan.getLocation());
                intent.putExtra("desc", plan.getDescription());

                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class AdapterViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView name, desc, spots;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            name = itemView.findViewById(R.id.txt_displayName);
            spots = itemView.findViewById(R.id.txt_displaySpot);
            desc = itemView.findViewById(R.id.txt_displayDesc);
        }
    }
}
