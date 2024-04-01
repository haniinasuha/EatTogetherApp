package com.example.eat;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserAccViewModel extends ViewModel {
    private MutableLiveData<List<UserAccounts>> users;
    private UserAccounts user;
    private FirebaseDatabase db;
    private DatabaseReference refUsers;

    public UserAccViewModel()
    {
        db = FirebaseDatabase.getInstance();
        refUsers = db.getReference().child("Users");
        users = new MutableLiveData<>();
    }


    public void setUser(String userID)
    {
        DatabaseReference currentUserRef = refUsers.child(userID);
        currentUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(UserAccounts.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that occur during the data retrieval process
                Log.e("User retrieval", "Failed to retrieve user: " + databaseError.getMessage());
            }
        });
    }

    public UserAccounts getUser(String userID)
    {
        setUser(userID);
        return user;
    }
}
