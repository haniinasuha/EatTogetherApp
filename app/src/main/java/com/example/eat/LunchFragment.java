package com.example.eat;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class LunchFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ArrayList<Plan> list;
    ArrayList<String> participant;
    PlanViewModel planViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lunch, container, false);
        planViewModel = new ViewModelProvider(this).get(PlanViewModel.class);
        recyclerView = view.findViewById(R.id.lunch_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(this.getContext(), list);
        recyclerView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Plans");

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String dateCurrent = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());

                    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Plan plan = dataSnapshot.getValue(Plan.class);
                    assert plan != null;
                    participant = plan.getMembers();

                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                    String userDate = plan.getDate();
                    Date dateUser = null;
                    Date currentDate = null;
                    try {
                        dateUser = sdf.parse(userDate);
                        currentDate = sdf.parse(dateCurrent);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

                    if(dateUser.after(currentDate)) {
                        if (plan.getMealType().equals("Lunch") && !(plan.getUserID().equals(userID)) && (plan.getSpots() > 0)&& !(participant.contains(userID))) {
                            list.add(plan);
                        }
                    } else {
                        String planId = plan.getId();
                        planViewModel.deletePlan(planId);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}