package com.parrotdevs.wellness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;
import com.parrotdevs.wellness.R;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email =  findViewById(R.id.inputEmailLogin);

    }

}