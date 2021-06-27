package com.parrotdevs.wellness.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.parrotdevs.wellness.R;
import com.parrotdevs.wellness.adapters.ExerciseAdapter;
import com.parrotdevs.wellness.model.Category;
import com.parrotdevs.wellness.model.Exercise;

public class ExercisesActivity extends AppCompatActivity {

    RecyclerView rvExercises;
    TextView titlePath;
    FirebaseDatabase db;
    Category currentCategory;
    ExerciseAdapter exerciseAdapter;
    LinearLayoutManager layoutManager;
    ConstraintLayout crPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        titlePath=findViewById(R.id.titlePath2);
        db= FirebaseDatabase.getInstance();
        Gson gson = new Gson();
        String categoryString= getIntent().getStringExtra("category");
        currentCategory= gson.fromJson(categoryString,Category.class);
        exerciseAdapter = new ExerciseAdapter(this);
        rvExercises=findViewById(R.id.rvExercises);
        rvExercises.setAdapter(exerciseAdapter);
        layoutManager=new LinearLayoutManager(this);
        rvExercises.setLayoutManager(layoutManager);

        titlePath.setText(currentCategory.getName());
         crPath= findViewById(R.id.root);
        LoadExercises();

        Glide.with(this).load(currentCategory.getBackgroundImg()).into((new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady( Drawable resource, Transition<? super Drawable> transition) {
                crPath.setBackground(resource);
            }

            @Override
            public void onLoadCleared( Drawable placeholder) {

            }

        }));

    }

    private void LoadExercises() {
        db.getReference("Exercises").orderByChild("categoryId").equalTo(currentCategory.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                Log.e("TAG", String.valueOf(snapshot.getChildrenCount()));
                for (DataSnapshot child:
                     snapshot.getChildren()) {
                    Exercise tempExercise = child.getValue(Exercise.class);
                    exerciseAdapter.AddExercise(tempExercise);

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
}