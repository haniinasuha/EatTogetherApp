package com.example.eat;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class PlanDetailsFragment extends Fragment {

    TextView desc;
    TextView mealType;
    TextView date;
    TextView time;
    TextView spot;
    TextView location;
    String planId;
    Button btnDelete;
    PlanViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plan_details, container, false);
        viewModel = new ViewModelProvider(this).get(PlanViewModel.class);
        planId = getArguments().getString("planId");

        Plan selectedPlan = viewModel.getPlan(planId);

        btnDelete = view.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deletePlan(planId);
                Intent intent = new Intent(getActivity(), ActivePlanActivity.class);
                startActivity(intent);
            }
        });

        desc = view.findViewById(R.id.txtView_desc);
        desc.setText(selectedPlan.getDescription());

        mealType = view.findViewById(R.id.txtView_mealType);
        mealType.setText(selectedPlan.getMealType());

        location = view.findViewById(R.id.txtView_location);
        location.setText(selectedPlan.getLocation());

        date = view.findViewById(R.id.txtView_date);
        date.setText(selectedPlan.getDate());

        time = view.findViewById(R.id.txtView_time);
        time.setText(selectedPlan.getTime());

        spot = view.findViewById(R.id.txtView_maxSpot);
        spot.setText(selectedPlan.getSpots());

        return view;
    }
}