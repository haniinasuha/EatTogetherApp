package com.example.eat;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ProfilePictures {
    FirebaseStorage storage;
    StorageReference storageRef;


    public ProfilePictures(String Uid){
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference().child("Profile_pictures").child(Uid);
    }
    public void uploadImageToFirebase(Uri imageUri) {
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

    public void loadProfilePicture(ImageView profilepic, Context context) {
        // Get the download URL for the profile picture from Firebase Storage
        storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
            // Load the image into the ImageView using Glide
            Glide.with(context)
                    .load(uri)
                    .apply(RequestOptions.circleCropTransform())
                    .into(profilepic);
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.e("Profile", "Failed to load profile picture", exception);
        });
    }
}
