package com.parrotdevs.wellness.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.parrotdevs.wellness.R;
import com.parrotdevs.wellness.model.Exercise;

import java.io.IOException;

public class MeditationActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Exercise currentExercise;
    ImageView btnPlay;
    TextView tvMeditationTime, tvMeditationDay;
    ConstraintLayout playScreenRoot;
    String imgbg;
    Gson gson;

    private Runnable mRunnable;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);

        btnPlay = findViewById(R.id.btnPlay);
        tvMeditationDay = findViewById(R.id.tvMeditationDay);
        playScreenRoot = findViewById(R.id.playScreenRoot);
        tvMeditationTime = findViewById(R.id.tvMeditationTime);

        Gson gson = new Gson();
        mHandler = new Handler();

        String exerciseString = getIntent().getStringExtra("exercise");
        imgbg = getIntent().getStringExtra("bgimg");

        currentExercise = gson.fromJson(exerciseString, Exercise.class);
        tvMeditationDay.setText("Day " + currentExercise.getDay());


        Glide.with(this).load(imgbg).into((new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                playScreenRoot.setBackground(resource);
            }

            @Override
            public void onLoadCleared(Drawable placeholder) {

            }

        }));

        mediaPlayer = new MediaPlayer();
        Prepare();
        btnPlay.setOnClickListener(v -> {

            Play();
        });

        mediaPlayer.setOnCompletionListener(mp -> {
            btnPlay.setImageResource(R.drawable.btn_play);
            Toast.makeText(this,"Completastes esta lección", Toast.LENGTH_SHORT).show();
        });

    }

    private void Prepare() {

        try {
            mediaPlayer.setDataSource(currentExercise.getAudioLink());
            mediaPlayer.setOnPreparedListener(mp -> {

                int length = mediaPlayer.getDuration();

                String durationText = DateUtils.formatElapsedTime(length / 1000);
                tvMeditationTime.setText(durationText);
            });
            mediaPlayer.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Play() {

        if (mediaPlayer.isPlaying()) {

            mediaPlayer.pause();
            btnPlay.setImageResource(R.drawable.btn_play);
        } else {

            mediaPlayer.start();
            getAudioStats();
            initializeTimer();

            btnPlay.setImageResource(R.drawable.pause_btn);
        }

    }

    protected void initializeTimer(){

        mRunnable = () -> {
            if(mediaPlayer!=null){
                getAudioStats();
            }
            mHandler.postDelayed(mRunnable,1000);
        };
        mHandler.postDelayed(mRunnable,1000);
    }

    protected void getAudioStats() {
        int due = (mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition()) / 1000;
        String durationText = DateUtils.formatElapsedTime(due);
        tvMeditationTime.setText(durationText);
    }

}