package com.example.eat;

import static androidx.fragment.app.FragmentManager.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Fragment fragmentProfileDetails = new ProfileFragment();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_profile, fragmentProfileDetails).commit();
    }
}
