package com.parrotdevs.wellness.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.parrotdevs.wellness.R;
import com.parrotdevs.wellness.model.Exercise;

public class ExerciseActivity extends AppCompatActivity {

    TextView tvExerciseDay,tvDayDescription,tvDayLength;
    Gson gson;
    Exercise currentExercise;
    Button btnStartExercise;
    String categoryString;
    ConstraintLayout constraint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        gson = new Gson();
        constraint= findViewById(R.id.exerciseRoot);
        tvExerciseDay =  findViewById(R.id.tvExerciseDay);
        tvDayDescription= findViewById(R.id.tvDayDescription);
        tvDayLength = findViewById(R.id.tvDayLength);
        btnStartExercise=findViewById(R.id.btnStartExercise);
        String exerciseString= getIntent().getStringExtra("exercise");
        categoryString= getIntent().getStringExtra("bgimg");
        currentExercise = gson.fromJson(exerciseString,Exercise.class);
        LoadExercise();

        btnStartExercise.setOnClickListener( v -> {

            Intent intent = new Intent(this,MeditationActivity.class);
            intent.putExtra("exercise",exerciseString);
            intent.putExtra("bgimg",categoryString);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

    }

    private void LoadExercise() {

        tvExerciseDay.setText("Day "+currentExercise.getDay());
        tvDayDescription.setText(currentExercise.getDescription());
        tvDayLength.setText(currentExercise.getLength());

        Glide.with(this).load(categoryString).into((new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                constraint.setBackground(resource);
            }

            @Override
            public void onLoadCleared( Drawable placeholder) {

            }

        }));
    }
}