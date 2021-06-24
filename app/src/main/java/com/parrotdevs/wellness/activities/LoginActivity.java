package com.parrotdevs.wellness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.parrotdevs.wellness.R;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout email,password;
    private Button btnLogin;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email =  findViewById(R.id.inputEmailLogin);
        password = findViewById(R.id.inputPasswordLogin);

        btnLogin =  findViewById(R.id.btnLogin);
        auth=FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(v->{

            auth.signInWithEmailAndPassword(email.getEditText().getText().toString(),password.getEditText().getText().toString()).addOnCompleteListener(

                    task->{

                        if(task.isSuccessful()){

                            Intent intent = new Intent(this,HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        else{

                            Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
            );


        });

    }

}