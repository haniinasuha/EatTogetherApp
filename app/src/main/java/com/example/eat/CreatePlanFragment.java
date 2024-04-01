package com.example.eat;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreatePlanFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    TextInputEditText descInput;
    Button btnCreate;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText date, time, spot;
    Spinner spinnerLoc;

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
        spinnerLoc = view.findViewById(R.id.spinner_loc);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.location, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLoc.setAdapter(adapter);
        spinnerLoc.setOnItemSelectedListener(this);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String timeCurrent = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
                String dateCurrent = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getContext(),R.style.DialogTheme,  new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText((month + 1) + "/" + String.valueOf(dayOfMonth) + "/" + String.valueOf(year));
                    }
                }, mYear, mMonth, mDay);

                dialog.getDatePicker().setMinDate(c.getTimeInMillis());
                dialog.show();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMin = c.get(Calendar.MINUTE);
                TimePickerDialog dialog = new TimePickerDialog(getContext(), R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String amPm;
                        if(hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        time.setText(String.format("%02d:%02d", hourOfDay, minute) + " " +amPm);
                    }
                }, mHour, mMin, false);
                dialog.show();
            }

        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioId);
                String mealType = radioButton.getText().toString();
                String dateStr = date.getText().toString();
                String timeStr = time.getText().toString();

                int spots = Integer.parseInt(spot.getText().toString());
                String description = descInput.getText().toString();
                String loc = spinnerLoc.getSelectedItem().toString();
                ((CreatePlanActivity) getActivity()).passData(description, mealType, dateStr, timeStr, spots, loc);
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}