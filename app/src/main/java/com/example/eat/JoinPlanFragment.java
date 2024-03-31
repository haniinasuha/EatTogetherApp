package com.example.eat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class JoinPlanFragment extends Fragment {

    PlanViewModel planViewModel;
    TextView desc, mealType, date, time, spot, location;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join_plan, container, false);
        planViewModel = new ViewModelProvider(this).get(PlanViewModel.class);

        return view;
    }
}