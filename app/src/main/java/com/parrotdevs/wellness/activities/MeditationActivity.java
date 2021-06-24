package com.parrotdevs.wellness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.parrotdevs.wellness.R;
import com.parrotdevs.wellness.model.Exercise;

import java.io.IOException;

public class MeditationActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Exercise currentExercise;
    ImageView btnPlay;
    TextView tvMeditationTime;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);
        btnPlay=findViewById(R.id.btnPlay);
        Gson gson = new Gson();
        tvMeditationTime= findViewById(R.id.tvMeditationTime);
        String exerciseString= getIntent().getStringExtra("exercise");
        currentExercise = gson.fromJson(exerciseString, Exercise.class);
        mediaPlayer = new MediaPlayer();
        Prepare();
        btnPlay.setOnClickListener( v -> {

            Play();
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

    if(mediaPlayer.isPlaying()){

        mediaPlayer.pause();
        btnPlay.setImageResource(R.drawable.pause_btn);
    }
    else{

        mediaPlayer.start();
        btnPlay.setImageResource(R.drawable.btn_play);
    }

    }
}