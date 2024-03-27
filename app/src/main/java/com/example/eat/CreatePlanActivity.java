package com.example.eat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.android.material.textfield.TextInputEditText;

public class CreatePlanActivity extends AppCompatActivity {
    private static final String TAG = "CreatePlan";
    private PlanViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);
        Log.d(TAG, "onCreate");
        viewModel = new ViewModelProvider(this).get(PlanViewModel.class);
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

    public void passData(TextInputEditText text, RadioButton btn, EditText date, EditText time, EditText spot, String loc) {

        // viewmodel
        String dateStr = date.getText().toString();
        String timeStr = time.getText().toString();

        int spots = Integer.parseInt(spot.getText().toString());
        String description = text.getText().toString();
        String mealType = btn.getText().toString();
        Plan plan = new Plan(description, spots, mealType, loc ,dateStr, timeStr);
        viewModel.addPlan(plan);

        Bundle bundle = new Bundle();
        bundle.putString("planId", plan.getId());


        Fragment fragmentPlanDetails = new PlanDetailsFragment();
        fragmentPlanDetails.setArguments(bundle);

        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_createPlan, fragmentPlanDetails).commit();
    }

}