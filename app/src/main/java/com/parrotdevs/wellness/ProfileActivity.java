package com.parrotdevs.wellness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {

    Button btnEditProfile ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnEditProfile =  findViewById(R.id.btnEditProfile);

        btnEditProfile.setOnClickListener( v -> {
           Intent intent = new Intent(this,EditProfileActivity.class);
           startActivity(intent);
        });
    }
}