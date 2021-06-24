package com.parrotdevs.wellness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.parrotdevs.wellness.R;
import com.parrotdevs.wellness.activities.EditProfileActivity;

public class ProfileActivity extends AppCompatActivity {

    Button btnEditProfile ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnEditProfile =  findViewById(R.id.btnEditProfile);

        btnEditProfile.setOnClickListener( v -> {
           Intent intent = new Intent(this, EditProfileActivity.class);
           startActivity(intent);
        });
    }
}