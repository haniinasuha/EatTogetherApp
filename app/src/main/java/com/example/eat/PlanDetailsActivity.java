package com.example.eat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PlanDetailsActivity extends AppCompatActivity {
    TextView desc, mealType, date, time, spot, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_details);
        Intent intent = getIntent();
        //String id = intent.getExtras().getString("id");

        desc = findViewById(R.id.txtView_desc);
        desc.setText(intent.getExtras().getString("desc"));

        date = findViewById(R.id.txtView_date);
        date.setText(intent.getExtras().getString("date"));

        time = findViewById(R.id.txtView_time);
        time.setText(intent.getExtras().getString("time"));

        spot = findViewById(R.id.txtView_maxSpot);
        spot.setText(intent.getExtras().getString("spot"));

        location = findViewById(R.id.txtView_location);
        location.setText(intent.getExtras().getString("location"));

        mealType = findViewById(R.id.txtView_mealType);
        mealType.setText(intent.getExtras().getString("mealType"));

    }
}