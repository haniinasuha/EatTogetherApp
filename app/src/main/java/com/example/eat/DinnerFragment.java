package com.example.eat;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DinnerFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ArrayList<Plan> list;
    PlanViewModel planViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dinner, container, false);
        planViewModel = new ViewModelProvider(this).get(PlanViewModel.class);
        recyclerView = view.findViewById(R.id.dinner_list);
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
                    String dateCurrent = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

                    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Plan plan = dataSnapshot.getValue(Plan.class);

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
                        if (plan.getMealType().equals("Dinner") && !(plan.getUserID().equals(userID)) && (plan.getSpots() > 0)) {
                            list.add(plan);
                        }
                    }else {
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