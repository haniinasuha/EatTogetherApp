package com.example.eat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PlanDetailsFragment extends Fragment {

    TextView desc;
    TextView mealType;
    TextView date;
    TextView time;
    TextView spot;
    TextView location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plan_details, container, false);

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