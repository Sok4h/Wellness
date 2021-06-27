package com.parrotdevs.wellness.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.parrotdevs.wellness.adapters.HistoyAdapter;
import com.parrotdevs.wellness.model.Exercise;
import com.parrotdevs.wellness.model.User;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    Button btnEditProfile ;
    CircleImageView profilePic;
    RecyclerView historyEmotional;
    HistoyAdapter adapter;
    LinearLayoutManager layoutManager;
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
        historyEmotional = findViewById(R.id.historyEmotional);
        adapter=new HistoyAdapter(this);
        layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        historyEmotional.setAdapter(adapter);
        historyEmotional.setLayoutManager(layoutManager);

        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        LoadUser();
        LoadHistory();


        btnEditProfile.setOnClickListener( v -> {
           Intent intent = new Intent(this, EditProfileActivity.class);
           startActivity(intent);
        });
    }

    private void LoadHistory() {

        db.getReference("UsersPath").child(FirebaseAuth.getInstance().getUid()).orderByChild("categoryType").equalTo("emotional").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {

                for (DataSnapshot child:
                      snapshot.getChildren()) {

                    Exercise tempExercise = child.getValue(Exercise.class);
                    adapter.AddExercise(tempExercise);


                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }


    private void LoadUser(){
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

