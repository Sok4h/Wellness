package com.parrotdevs.wellness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        btnLogin = findViewById(R.id.btnLoginSplash);
        btnRegister = findViewById(R.id.btnRegisterSplash);

        btnLogin.setOnClickListener( v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        btnRegister.setOnClickListener( v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

    }
}