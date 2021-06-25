package com.parrotdevs.wellness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import com.parrotdevs.wellness.R;
import com.parrotdevs.wellness.model.User;
import com.parrotdevs.wellness.model.UserPath;

import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout inputName, inputEmail, inputPassword;
    private Button btnRegister;

    FirebaseDatabase db;
    FirebaseAuth auth;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        inputName = findViewById(R.id.inputNameRegister);
        inputEmail = findViewById(R.id.inputEmailRegister);
        inputPassword = findViewById(R.id.inputPasswordRegister);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            register();
        });
    }

    private void register() {

        String name, email, password;
        name = inputName.getEditText().getText().toString();
        email = inputEmail.getEditText().getText().toString();
        password = inputPassword.getEditText().getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()){

            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();

        }else{

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {

                if (task.isSuccessful()) {

                    String id = task.getResult().getUser().getUid();

                    user = new User(id, name, email);

                    db.getReference("users").child(id).setValue(user).addOnCompleteListener(register -> {

                        if (register.isSuccessful()) {

                            UploadPath();

                        } else {

                            Toast.makeText(this, register.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    });

                } else {

                    Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    private void UploadPath() {


        UserPath userPath = new UserPath();
        db.getReference("UsersPath").child(user.getId()).setValue(userPath).addOnCompleteListener(task->{

           if(task.isSuccessful()){
               Intent intent = new Intent(this, HomeActivity.class);
               startActivity(intent);
               Toast.makeText(this, "registrado correctamente", Toast.LENGTH_SHORT).show();
               finish();

           }
           else{

               Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
           }

        });
    }
}
