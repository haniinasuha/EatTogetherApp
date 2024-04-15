package com.example.eat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class CreatePlanActivity extends AppCompatActivity {
    private static final String TAG = "CreatePlan";
    private PlanViewModel viewModel;

    BottomNavigationView navBar;
    ArrayList<String> participants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);
        Log.d(TAG, "onCreate");
        viewModel = new ViewModelProvider(this).get(PlanViewModel.class);

        navBar = findViewById(R.id.nav_bar);
        navBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int item_id = item.getItemId();

                if (item_id == R.id.log_out) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (item_id == R.id.home) {
                    Intent intent = new Intent(getApplicationContext(), ActivePlanActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (item_id == R.id.profile) {
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (item_id == R.id.plan) {
                    Intent intent = new Intent(getApplicationContext(), PlanActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    public void passData(String description, String mealType, String dateStr, String timeStr, int spots, String loc) {

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        participants = new ArrayList<>();
        participants.add(userID);
        Plan plan = new Plan(description, spots, mealType, loc, dateStr, timeStr, participants);
        plan.setUserID(userID);
        viewModel.addPlan(plan);

        Bundle bundle = new Bundle();

        bundle.putString("desc", description);
        bundle.putString("mealType", mealType);
        bundle.putString("date", dateStr);
        bundle.putString("time", timeStr);
        bundle.putString("maxSpots", String.valueOf(spots));
        bundle.putString("loc", loc);

        bundle.putString("planId", plan.getId());


        Fragment fragmentPlanDetails = new PlanDetailsFragment();
        fragmentPlanDetails.setArguments(bundle);

        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_createPlan, fragmentPlanDetails).addToBackStack("").commit();
    }

    /*private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(CreatePlanActivity.this);
                }
            }
        });
    }
/*
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.map = googleMap;
        //LatLng loc = new LatLng(39, 83);
        LatLng loc = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        map.addMarker(new MarkerOptions().position(loc).title("My location"));
        map.moveCamera(CameraUpdateFactory.newLatLng(loc));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == FINE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Toast.makeText(this, "Permission Denied. Please allow the permission", Toast.LENGTH_SHORT).show();
            }
        }
    }
*/

}