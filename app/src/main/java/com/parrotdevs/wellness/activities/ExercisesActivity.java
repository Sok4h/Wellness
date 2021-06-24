package com.parrotdevs.wellness.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.parrotdevs.wellness.R;
import com.parrotdevs.wellness.model.Category;

public class ExercisesActivity extends AppCompatActivity {

    RecyclerView rvExercises;
    TextView titlePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        rvExercises=findViewById(R.id.rvExercises);
        titlePath=findViewById(R.id.titlePath);

        Category currentCategory;
        Gson gson = new Gson();
        String categoryString= getIntent().getStringExtra("category");
        currentCategory= gson.fromJson(categoryString,Category.class);
    }
}