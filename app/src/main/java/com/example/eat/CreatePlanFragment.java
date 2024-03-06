package com.example.eat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

public class CreatePlanFragment extends Fragment {

    TextInputEditText descInput;
    Button btnCreate;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText date;
    EditText time;
    EditText spot;

    String[] locations = {"Berry Cafe", "KSA cafe", "Mirror Lake Eatery", "Curl Market",
            "Oxley's by the numbers", "Terra Byte Cafe", "Traditions by Scott", "Woody's Tavern",
            "Union Market"};


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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, locations);
        Spinner spinner = view.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioId);

                int radioIdd = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioIdd);


                ((CreatePlan) getActivity()).passData(descInput, radioButton, date, time, spot);

            }
        });


        return view;
    }
}