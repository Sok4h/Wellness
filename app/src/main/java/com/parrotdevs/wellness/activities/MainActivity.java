package com.parrotdevs.wellness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.parrotdevs.wellness.R;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        auth.addAuthStateListener(firebaseAuth -> {

            user= firebaseAuth.getCurrentUser();
            ChangeActivity();

        });

    }



    private void ChangeActivity() {

        Handler handler = new Handler();

        handler.postDelayed(() -> {

            if(user != null){

                Intent intent = new Intent(this,HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
            else{

                Intent intent = new Intent(this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }


        }, 2000);
    }

}