package com.parrotdevs.wellness.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.color.MaterialColors;
import com.parrotdevs.wellness.R;

public class WelcomeActivity extends AppCompatActivity {

    TextView btnEmotional, btnPhysical;
    TextView textName, textDescription, textLogOut;
    Button btnStart;
    ConstraintLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnPhysical =  findViewById(R.id.textPhysicalWelcome);
        btnEmotional = findViewById(R.id.textEmotionalWelcome);
        textName = findViewById(R.id.textNameWelcome);
        textDescription = findViewById(R.id.textDescriptionWelcome);
        textLogOut = findViewById(R.id.textLogOutWelcome);

        btnStart = findViewById(R.id.btnStartWelcome);

        bg = findViewById(R.id.bgWelcome);

        btnPhysical.setOnClickListener(v ->{
            bg.setBackgroundColor(MaterialColors.getColor(bg,R.attr.colorPrimary));
            textName.setTextColor(getResources().getColor(R.color.white));
            textDescription.setTextColor(getResources().getColor(R.color.white));
            textLogOut.setTextColor(getResources().getColor(R.color.white));
            btnPhysical.setTextColor(getResources().getColor(R.color.white));
            btnPhysical.setTextSize(28);
            btnPhysical.setTypeface(null, Typeface.BOLD);
            btnEmotional.setTextColor(getResources().getColor(R.color.white));
            btnEmotional.setTextSize(18);
            btnEmotional.setTypeface(null, Typeface.NORMAL);

            btnStart.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            btnStart.setTextColor(MaterialColors.getColor(bg,R.attr.colorPrimary));

        });

        btnEmotional.setOnClickListener(v ->{
            bg.setBackgroundColor(MaterialColors.getColor(bg,R.attr.colorOnPrimary));
            textName.setTextColor(MaterialColors.getColor(bg,R.attr.colorPrimary));
            textDescription.setTextColor(MaterialColors.getColor(bg,R.attr.colorPrimary));
            textLogOut.setTextColor(MaterialColors.getColor(bg,R.attr.colorPrimary));
            btnPhysical.setTextColor(MaterialColors.getColor(bg,R.attr.colorPrimary));
            btnPhysical.setTextSize(18);
            btnPhysical.setTypeface(null, Typeface.NORMAL);
            btnEmotional.setTextColor(MaterialColors.getColor(bg,R.attr.colorPrimary));
            btnEmotional.setTextSize(28);
            btnEmotional.setTypeface(null, Typeface.BOLD);

            btnStart.setBackgroundTintList(ColorStateList.valueOf(MaterialColors.getColor(bg,R.attr.colorPrimary)));
            btnStart.setTextColor(getResources().getColor(R.color.white));

        });

    }
}