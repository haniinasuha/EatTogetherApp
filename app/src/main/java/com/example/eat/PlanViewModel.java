package com.example.eat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;

import java.util.List;


public class PlanViewModel extends ViewModel {
    private MutableLiveData<List<Plan>> plans;
    private DatabaseReference database;

    public LiveData<List<Plan>> getPlans() {
        if (plans == null) {
            plans = new MutableLiveData<>();
        }
        return plans;
    }


}


