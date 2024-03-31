package com.example.eat;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class PlanDetailsFragment extends Fragment {

    TextView desc, mealType, date, time, spot, location;
    String planId;
    Button btnDelete, btnEdit;
    PlanViewModel planViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plan_details, container, false);
        planViewModel = new ViewModelProvider(this).get(PlanViewModel.class);
        planId = getArguments().getString("planId");

        btnDelete = view.findViewById(R.id.btn_delete);
        btnEdit = view.findViewById(R.id.btn_edit);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planViewModel.deletePlan(planId);
                getParentFragmentManager().popBackStack();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planViewModel.deletePlan(planId);
                Intent intent = new Intent(getActivity(), ActivePlanActivity.class);
                startActivity(intent);
            }
        });

        desc = view.findViewById(R.id.txtView_desc);
        String description = getArguments().getString("desc");
        desc.setText(description);

        mealType = view.findViewById(R.id.txtView_mealType);
        String type = getArguments().getString("mealType");
        mealType.setText(type);

        location = view.findViewById(R.id.txtView_location);
        String loc = getArguments().getString("loc");
        location.setText(loc);

        date = view.findViewById(R.id.txtView_date);
        String dateText = getArguments().getString("date");
        date.setText(dateText);

        time = view.findViewById(R.id.txtView_time);
        String timeText = getArguments().getString("time");
        time.setText(timeText);

        spot = view.findViewById(R.id.txtView_maxSpot);
        String maxSpot = getArguments().getString("maxSpots");
        spot.setText(maxSpot);

        return view;
    }
}