package com.parrotdevs.wellness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.parrotdevs.wellness.R;
import com.parrotdevs.wellness.model.Exercise;

public class ExerciseActivity extends AppCompatActivity {

    TextView tvExerciseDay,tvDayDescription,tvDayLength;
    Gson gson;
    Exercise currentExercise;
    Button btnStartExercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        gson = new Gson();
        tvExerciseDay =  findViewById(R.id.tvExerciseDay);
        tvDayDescription= findViewById(R.id.tvDayDescription);
        tvDayLength = findViewById(R.id.tvDayLength);
        btnStartExercise=findViewById(R.id.btnStartExercise);
        String exerciseString= getIntent().getStringExtra("exercise");
        currentExercise = gson.fromJson(exerciseString,Exercise.class);
        LoadExercise();

        btnStartExercise.setOnClickListener( v -> {

            Intent intent = new Intent(this,MeditationActivity.class);
            intent.putExtra("exercise",exerciseString);
            startActivity(intent);
        });

    }

    private void LoadExercise() {

        tvExerciseDay.setText(currentExercise.getDay());
        tvDayDescription.setText(currentExercise.getDescription());
        tvDayLength.setText(currentExercise.getLength());
    }
}