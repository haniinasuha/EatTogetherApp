package com.example.eat;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class PlanViewModel extends ViewModel {
    private MutableLiveData<List<Plan>> plans;
    private final DatabaseReference refPlans;

    public PlanViewModel()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        refPlans = db.getReference().child("Plans");
        plans = new MutableLiveData<>();
        loadPlans();
    }
    public LiveData<List<Plan>> getPlans() {
        if (plans == null) {
            plans = new MutableLiveData<>();
        }
        return plans;
    }

    public void loadPlans()
    {

        refPlans.addValueEventListener(new ValueEventListener()
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
                System.out.println("The read failed");
            }
        });
    }

    public void addPlan(Plan plan) {
        List<Plan> currentPlans = plans.getValue();
        if (plans!= null) {
            DatabaseReference newPlanRef = refPlans.push(); // Generate a unique key
            String planId = newPlanRef.getKey(); // Retrieve the generated key
            plan.setId(planId); // Set the ID of the plan
            newPlanRef.setValue(plan);
        }
    }

    public void deletePlan(String planId) {
        DatabaseReference planRef = refPlans.child(planId); // Get reference to the node with the given ID
        planRef.removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Plan deletion successful
                        Log.d("PlanViewModel", "Plan deleted successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to delete plan
                        Log.e("PlanViewModel", "Error deleting plan", e);
                    }
                });
    }


    public void updateSpots(String planId, int newSpots) {
        DatabaseReference planRef = refPlans.child(planId);
        planRef.child("spots").setValue(newSpots)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Spots update successful
                        Log.d("PlanViewModel", "Spots updated successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to update spots
                        Log.e("PlanViewModel", "Error updating spots", e);
                    }
                });
    }

}


