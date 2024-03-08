package com.example.eat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.android.material.textfield.TextInputEditText;

public class CreatePlanActivity extends AppCompatActivity {
    private static final String TAG = "CreatePlan";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);
        Log.d(TAG, "onCreate");

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

        Bundle bundle = new Bundle();
        bundle.putString("desc", text.getText().toString());
        bundle.putString("mealType", btn.getText().toString());
        bundle.putString("date", date.getText().toString());
        bundle.putString("time", time.getText().toString());
        bundle.putString("maxSpots", spot.getText().toString());
        bundle.putString("loc", loc);


        Fragment fragmentPlanDetails = new PlanDetailsFragment();
        fragmentPlanDetails.setArguments(bundle);

        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_createPlan, fragmentPlanDetails).commit();
    }

}