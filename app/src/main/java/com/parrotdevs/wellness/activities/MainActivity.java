package com.parrotdevs.wellness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.parrotdevs.wellness.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChangeActivity();
    }

    private void ChangeActivity() {

        Handler handler = new Handler();

        handler.postDelayed(() -> {

            Intent intent = new Intent(this, SplashActivity.class);
            startActivity(intent);
            finish();

        }, 2000);
    }

}