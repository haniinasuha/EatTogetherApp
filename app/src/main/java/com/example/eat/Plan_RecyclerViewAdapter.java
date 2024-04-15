package com.example.eat;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Plan_RecyclerViewAdapter extends RecyclerView.Adapter<Plan_RecyclerViewAdapter.AdapterViewHolder> {

    Context context;
    ArrayList<Plan> list;
    ProfilePictures pfpManager;
    public Plan_RecyclerViewAdapter(Context context, ArrayList<Plan> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.plan_recycler_view, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        pfpManager = new ProfilePictures(userID);

        Plan plan = list.get(position);
        holder.meal.setText(plan.getMealType());
        holder.spots.setText("Spots: " + plan.getSpots());
        holder.desc.setText(plan.getDescription());
        holder.date.setText("Date: " + plan.getDate());
        holder.time.setText("Time: " + plan.getTime());
        holder.location.setText(plan.getLocation());
        holder.member.setVisibility(View.GONE);
        holder.profile_pic.setVisibility(View.VISIBLE);

        if (plan.getUserID().equals(userID)) {
            holder.profile_pic.setVisibility(View.GONE);
            holder.member.setVisibility(View.VISIBLE);
            holder.desc.setText("-");

            for (int i = 1; i < plan.getMembers().size(); i++) {
                String memberId = plan.getMembers().get(i);

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(memberId);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserAccounts user = snapshot.getValue(UserAccounts.class);
                        String name = user.getFirstName() + " " + user.getLastName();
                        holder.desc.setText(name + " - ");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }
        pfpManager.loadProfilePicture(holder.profile_pic, context);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView desc, spots, date, time, meal, location, member;
        ImageView profile_pic;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            spots = itemView.findViewById(R.id.txt_displaySpot);
            desc = itemView.findViewById(R.id.txt_displayDesc);
            date = itemView.findViewById(R.id.txt_displayDate);
            time = itemView.findViewById(R.id.txt_displayTime);
            meal = itemView.findViewById(R.id.txt_displayMeal);
            profile_pic = itemView.findViewById(R.id.img_profile2);
            location = itemView.findViewById(R.id.txt_displayLocation);
            member = itemView.findViewById(R.id.txt_displayMembers);
        }
    }
}
