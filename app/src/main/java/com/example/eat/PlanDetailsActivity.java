package com.example.eat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class PlanDetailsActivity extends AppCompatActivity {
    TextView desc, mealType, date, time, spot, location;
    Button btnJoin;
    String id, userId;
    PlanViewModel planViewModel;
    ArrayList<String> participant;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_details);
        planViewModel = new ViewModelProvider(this).get(PlanViewModel.class);

        //participant = new ArrayList<>();
        Intent intent = getIntent();
        id = Objects.requireNonNull(intent.getExtras()).getString("Id");
        //Plan plan = planViewModel.getPlan(id);

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

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

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Plans").child(id);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Plan plan = snapshot.getValue(Plan.class);
                        assert plan != null;
                        participant = plan.getMembers();
                        participant.add(userId);
                        plan.setMembers(participant);
                        reference.child("members").setValue(participant);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Intent intent = new Intent(getApplicationContext(), ActivePlanActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}