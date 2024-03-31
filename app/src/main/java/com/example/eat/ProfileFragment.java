package com.example.eat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {
    TextView email;
    TextView username;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        username = view.findViewById(R.id.txtView_username);
        String userFullName = user.getDisplayName();
        username.setText(userFullName);

        email = view.findViewById(R.id.txtView_email);
        String desc = "Email:\n" + user.getEmail();
        email.setText(desc);

        return view;
    }
}
