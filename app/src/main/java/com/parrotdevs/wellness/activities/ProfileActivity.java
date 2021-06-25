package com.parrotdevs.wellness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.parrotdevs.wellness.R;
import com.parrotdevs.wellness.activities.EditProfileActivity;
import com.parrotdevs.wellness.model.User;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    Button btnEditProfile ;
    CircleImageView profilePic;

    private FirebaseDatabase db;
    private User currentUser;
    private FirebaseAuth auth;
    private TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnEditProfile =  findViewById(R.id.btnEditProfile);
        profilePic = findViewById(R.id.profile_image);
        tvDescription = findViewById(R.id.tvDescriptionProfile);

        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        loadUser();

        btnEditProfile.setOnClickListener( v -> {
           Intent intent = new Intent(this, EditProfileActivity.class);
           startActivity(intent);
        });
    }


    private void loadUser(){
        db.getReference("users").child(Objects.requireNonNull(auth.getUid())).addValueEventListener(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        currentUser = snapshot.getValue(User.class);
                        tvDescription.setText(currentUser.getDescription());
                        Glide.with(getApplicationContext()).load(currentUser.getUserPhoto()).placeholder(R.drawable.ic_profile).into(profilePic);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                }
        );
    }

}

