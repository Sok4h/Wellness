package com.parrotdevs.wellness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerlayout;
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
        emotionalFragment= EmotionalTrainingFragment.newInstance();
        physicalFragment= PhysicalFragment.newInstance();
        ShowFragment(physicalFragment);

        //abre el nav drawer al pulsar en el boton
        btnMenu.setOnClickListener(v->{

        drawerlayout.openDrawer(GravityCompat.START);
        });

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
}