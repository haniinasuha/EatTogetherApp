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
    private FirebaseDatabase db;
    private DatabaseReference refUsers;

    public UserAccViewModel()
    {
        db = FirebaseDatabase.getInstance();
        refUsers = db.getReference().child("Users");
        users = new MutableLiveData<>();
        loadUsers();
    }

    public void loadUsers()
    {
        refUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<UserAccounts> userList = new ArrayList<>();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    UserAccounts user = userSnapshot.getValue(UserAccounts.class);
                    userList.add(user);
                }
                users.setValue(userList);
                Log.d("UserAccViewModel", "IN USERS: " + users.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("UserAccViewModel", "Failed to load users: " + databaseError.getMessage());
            }
        });
    }

    public UserAccounts getUser(String email)
    {
        List<UserAccounts> userList = users.getValue();
        if (userList != null) {
            for (UserAccounts user : userList) {
                if (user.getEmail().equals(email)) {
                    return user;
                }
            }
        }
        return null;
    }
}
