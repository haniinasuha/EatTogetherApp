package com.example.eat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class CreatePlanActivity extends AppCompatActivity {
    private static final String TAG = "CreatePlan";
    private PlanViewModel viewModel;
    BottomNavigationView navBar;

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

                if(item_id == R.id.log_out) {
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

        //viewmodel
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Plan plan = new Plan(description, spots, mealType, loc ,dateStr, timeStr);
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

        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_createPlan, fragmentPlanDetails).addToBackStack("name").commit();
    }

}