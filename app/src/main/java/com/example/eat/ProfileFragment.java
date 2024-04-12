package com.example.eat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class ProfileFragment extends Fragment {
    TextView email;
    TextView username;
    FirebaseAuth auth;
    FirebaseUser user;
    ImageView profilepic;
    Uri selectedImageUri;
    ProfilePictures pfpManager;
    ActivityResultLauncher<Intent> imagePickLauncher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        pfpManager = new ProfilePictures(user.getUid());

        username = view.findViewById(R.id.txtView_username);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserAccounts user = snapshot.getValue(UserAccounts.class);
                String name = user.getFirstName() + " " + user.getLastName();
                username.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        email = view.findViewById(R.id.txtView_email);
        String desc = "Email:\n" + user.getEmail();
        email.setText(desc);

        //profile picture
        imagePickLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data != null && data.getData() != null){
                            selectedImageUri = data.getData();
                            pfpManager.uploadImageToFirebase(selectedImageUri);
                        }
                    }
                }
                );
        profilepic = view.findViewById(R.id.img_profile);

        pfpManager.loadProfilePicture(profilepic, requireContext());

        return view;
    }

/*    private void uploadImageToFirebase(Uri imageUri) {
        if (imageUri != null) {
            storageRef.putFile(imageUri).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d("Profile", "Profile picture uploaded successfully");
                } else {
                    Log.e("Profile", "Failed to upload profile picture", task.getException());
                }
            });
        }
    }

    private void loadProfilePicture() {
        // Get the download URL for the profile picture from Firebase Storage
        storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
            // Load the image into the ImageView using Glide
            Glide.with(requireContext())
                    .load(uri)
                    .apply(RequestOptions.circleCropTransform())
                    .into(profilepic);
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.e("Profile", "Failed to load profile picture", exception);
        });
    }*/

}
