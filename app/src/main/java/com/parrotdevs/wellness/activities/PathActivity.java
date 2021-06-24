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
import com.parrotdevs.wellness.model.Category;
import com.parrotdevs.wellness.R;

public class PathActivity extends AppCompatActivity {

    TextView tvPathTitle,tvType,tvPathDescription,tvPathLength;
    ConstraintLayout crPath;
    Button btnStartPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);

        tvPathTitle = findViewById(R.id.tvPathTitle);
        tvType = findViewById(R.id.tvType);
        tvPathDescription = findViewById(R.id.tvPathDescription);
        tvPathLength = findViewById(R.id.tvPathLength);
        crPath= findViewById(R.id.crPath);
        btnStartPath = findViewById(R.id.btnStartPath);
        Category currentCategory;
        Gson gson = new Gson();
        String categoryString= getIntent().getStringExtra("category");
        currentCategory= gson.fromJson(categoryString,Category.class);

        tvPathTitle.setText(currentCategory.getName());
        tvType.setText(currentCategory.getType());
        tvPathDescription.setText(currentCategory.getDescription());
        tvPathLength.setText(currentCategory.getDays());



        Glide.with(this).load(currentCategory.getBackgroundImg()).into((new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady( Drawable resource, Transition<? super Drawable> transition) {
                crPath.setBackground(resource);
            }

            @Override
            public void onLoadCleared( Drawable placeholder) {

            }

        }));

        btnStartPath.setOnClickListener( v -> {


            Intent intent = new Intent(this, ExercisesActivity.class);

            String category = gson.toJson(currentCategory);
            intent.putExtra("category",category);
            startActivity(intent);
        });
    }
}