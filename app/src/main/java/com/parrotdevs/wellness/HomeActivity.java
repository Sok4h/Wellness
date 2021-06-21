package com.parrotdevs.wellness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerlayout;
    ImageView btnMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerlayout = findViewById(R.id.drawer_layout);
        btnMenu = findViewById(R.id.btnMenu);

        btnMenu.setOnClickListener(v->{

        drawerlayout.openDrawer(GravityCompat.START);
        });
    }
}