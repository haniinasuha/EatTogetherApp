package com.example.eat;

import static com.google.android.gms.common.util.CollectionUtils.listOf;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Objects;


public class CreatePlanFragment extends Fragment implements OnMapReadyCallback {

    TextInputEditText descInput;
    Button btnCreate;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText date, time, spot;
    String location;
    private GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_plan, container, false);
        SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map));
        assert mapFragment != null;
        Places.initialize(getContext().getApplicationContext(),getString(R.string.map_api));
        AutocompleteSupportFragment autocompleteSupportFragment = (AutocompleteSupportFragment) getChildFragmentManager().findFragmentById(R.id.search_bar);

        assert autocompleteSupportFragment != null;
        autocompleteSupportFragment.setPlaceFields(listOf(Place.Field.ID,Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG));
        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onError(@NonNull Status status) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }

            final MarkerOptions markerOptions = new MarkerOptions();
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                LatLng latlng = place.getLatLng();
                map.clear();
                if(latlng != null) {
                    map.addMarker(markerOptions.position(latlng).title(place.getName()));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10));
                    location = place.getName();
                } else {
                    Toast.makeText(getActivity(), "Location Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mapFragment.getMapAsync(this);

        descInput = view.findViewById(R.id.txtInput_desc);
        btnCreate = view.findViewById(R.id.btn_createNew);
        radioGroup = view.findViewById(R.id.radio_group);
        date = view.findViewById(R.id.editTextDate);
        time = view.findViewById(R.id.editTextTime);
        spot = view.findViewById(R.id.editTextNumber);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getContext(), R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText((month + 1) + "/" + dayOfMonth + "/" + year);
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
                    @SuppressLint({"SetTextI18n", "DefaultLocale"})
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String amPm;
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        time.setText(String.format("%02d:%02d", hourOfDay, minute) + " " + amPm);
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
                String description = Objects.requireNonNull(descInput.getText()).toString();

                ((CreatePlanActivity) getActivity()).passData(description, mealType, dateStr, timeStr, spots, location);
            }
        });

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.map = googleMap;
    }

}