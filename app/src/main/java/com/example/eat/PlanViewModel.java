package com.example.eat;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class PlanViewModel extends ViewModel {
    private MutableLiveData<List<Plan>> plans;
    private FirebaseDatabase db;
    private DatabaseReference reference;

    public PlanViewModel()
    {
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Plans");
        plans = new MutableLiveData<>();
        loadPlans();

    }
    public LiveData<List<Plan>> getPlans() {
        if (plans == null) {
            plans = new MutableLiveData<>();
        }
        return plans;
    }

    private void loadPlans()
    {
        reference.addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                List<Plan> planList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Plan plan = snapshot.getValue(Plan.class);
                    planList.add(plan);
                }
                plans.setValue(planList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                //todo: handle error
            }
        });
    }

    public void addPlan(Plan plan) {
        List<Plan> currentPlans = new ArrayList<>();
        if (plans!= null) {
            currentPlans.add(plan);
            plans.setValue(currentPlans);
            reference.setValue(currentPlans);
        }
    }

}


