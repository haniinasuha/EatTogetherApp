package com.example.eat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;

public class CreatePlanFragment extends Fragment {

    private PlanViewModel viewModel;
    TextInputEditText descInput;
    Button btnCreate;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText date;
    EditText time;
    EditText spot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_plan, container, false);

        descInput = view.findViewById(R.id.txtInput_desc);
        btnCreate = view.findViewById(R.id.btn_createNew);
        radioGroup = view.findViewById(R.id.radio_group);
        date = view.findViewById(R.id.editTextDate);
        time = view.findViewById(R.id.editTextTime);
        spot = view.findViewById(R.id.editTextNumber);
        viewModel = new ViewModelProvider(this).get(PlanViewModel.class);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioId);

                String description = descInput.getText().toString();

                int radioIdd = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioIdd);
                String mealType = radioButton.getText().toString();

                String dateStr = date.getText().toString();
                String timeStr = time.getText().toString();

                int spots = Integer.parseInt(spot.getText().toString());
                Plan plan = new Plan(description, spots, mealType, "TODO NOT ADDED LOC" ,dateStr, timeStr);
                viewModel.addPlan(plan);

                ((CreatePlan) getActivity()).passData(descInput, radioButton, date, time, spot);

            }
        });

        return view;
    }
}