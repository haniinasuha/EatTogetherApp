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

        return view;
    }
}