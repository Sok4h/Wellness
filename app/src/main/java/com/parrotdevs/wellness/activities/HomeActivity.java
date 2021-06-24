package com.parrotdevs.wellness.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationView;
import com.parrotdevs.wellness.fragments.EmotionalTrainingFragment;
import com.parrotdevs.wellness.R;
import com.parrotdevs.wellness.fragments.PhysicalFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerlayout;
    NavigationView navView;
    ImageView btnMenu;
    BottomNavigationView bottomNavigationView;
    private EmotionalTrainingFragment emotionalFragment;
    private PhysicalFragment physicalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerlayout = findViewById(R.id.drawer_layout);
        btnMenu = findViewById(R.id.btnMenu);
        bottomNavigationView = findViewById(R.id.navigator);
        navView = findViewById(R.id.navViewHome);
        emotionalFragment= EmotionalTrainingFragment.newInstance();
        physicalFragment= PhysicalFragment.newInstance();
        ShowFragment(physicalFragment);

        //abre el nav drawer al pulsar en el boton
        btnMenu.setOnClickListener(v->{
            drawerlayout.openDrawer(GravityCompat.START);
        });

        navView.setNavigationItemSelectedListener(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(item ->{

            switch(item.getItemId()) {

                case R.id.physicalTraining:

                    ShowFragment(physicalFragment);
                    break;

                case R.id.emotionalTraining:

                    ShowFragment(emotionalFragment);
                    break;
            }

            return true;
        } );


    }

    public void ShowFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Intent intent = new Intent(this,ProfileActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}