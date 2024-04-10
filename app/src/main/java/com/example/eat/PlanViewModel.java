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
    private FirebaseDatabase db;
    private DatabaseReference refPlans;

    public PlanViewModel()
    {
        db = FirebaseDatabase.getInstance();
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

    public void updateTime(String planId, String newTime) {
        DatabaseReference planRef = refPlans.child(planId);
        planRef.child("time").setValue(newTime)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Spots update successful
                        Log.d("PlanViewModel", "Time updated successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to update spots
                        Log.e("PlanViewModel", "Error updating time", e);
                    }
                });
    }

    public void updateDate(String planId, String newDate) {
        DatabaseReference planRef = refPlans.child(planId);
        planRef.child("date").setValue(newDate)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Spots update successful
                        Log.d("PlanViewModel", "Date updated successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to update spots
                        Log.e("PlanViewModel", "Error updating date", e);
                    }
                });
    }

    public void updateLocation(String planId, String newLoc) {
        DatabaseReference planRef = refPlans.child(planId);
        planRef.child("location").setValue(newLoc)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Spots update successful
                        Log.d("PlanViewModel", "Location updated successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to update spots
                        Log.e("PlanViewModel", "Error updating location", e);
                    }
                });
    }

    public void updateDescription(String planId, String newDesc) {
        DatabaseReference planRef = refPlans.child(planId);
        planRef.child("description").setValue(newDesc)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Spots update successful
                        Log.d("PlanViewModel", "Description updated successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to update spots
                        Log.e("PlanViewModel", "Error updating description", e);
                    }
                });
    }

    public void updateMealType(String planId, String mealType) {
        DatabaseReference planRef = refPlans.child(planId);
        planRef.child("mealType").setValue(mealType)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Spots update successful
                        Log.d("PlanViewModel", "Meal updated successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to update spots
                        Log.e("PlanViewModel", "Error updating meal", e);
                    }
                });
    }

    public Plan getPlan(String PlanId)
    {
        List<Plan> planList = plans.getValue();
        assert planList != null;
        for (Plan p : planList){
            if(p.getId().equals(PlanId)) return p;
        }
        Log.d("PlanViewModel","Couldnt find plan");
        return null;
    }

}


