package com.example.eat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PlanDetailsActivity extends AppCompatActivity {
    TextView desc, mealType, date, time, spot, location;
    Button btnJoin;
    String id;
    PlanViewModel planViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_details);
        planViewModel = new ViewModelProvider(this).get(PlanViewModel.class);
        Intent intent = getIntent();
        id = intent.getExtras().getString("Id");

        desc = findViewById(R.id.txtView_desc);
        desc.setText(intent.getExtras().getString("desc"));

        date = findViewById(R.id.txtView_date);
        date.setText(intent.getExtras().getString("date"));

        time = findViewById(R.id.txtView_time);
        time.setText(intent.getExtras().getString("time"));

        spot = findViewById(R.id.txt_maxSpot);
        int maxSpots = intent.getExtras().getInt("spot");
        spot.setText("Maximum Spots: " + maxSpots);

        location = findViewById(R.id.txtView_location);
        location.setText(intent.getExtras().getString("location"));

        mealType = findViewById(R.id.txtView_mealType);
        mealType.setText(intent.getExtras().getString("mealType"));

        btnJoin = findViewById(R.id.btn_join);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newSpots = maxSpots - 1;
                planViewModel.updateSpots(id, newSpots);
                Intent intent = new Intent(getApplicationContext(), ActivePlanActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}