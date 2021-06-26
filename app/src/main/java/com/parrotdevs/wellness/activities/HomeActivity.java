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
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.parrotdevs.wellness.fragments.EmotionalTrainingFragment;
import com.parrotdevs.wellness.R;
import com.parrotdevs.wellness.fragments.PhysicalFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerlayout;
    NavigationView navView;
    ImageView btnMenu;
    BottomNavigationView bottomNavigationView;
    TextView tvLogOut;
    private EmotionalTrainingFragment emotionalFragment;
    private PhysicalFragment physicalFragment;
    
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerlayout = findViewById(R.id.drawer_layout);
        btnMenu = findViewById(R.id.btnMenu);
        bottomNavigationView = findViewById(R.id.navigator);
        navView = findViewById(R.id.navViewHome);
        tvLogOut = findViewById(R.id.tvLogOut);
        auth = FirebaseAuth.getInstance();
        emotionalFragment= EmotionalTrainingFragment.newInstance();
        physicalFragment= PhysicalFragment.newInstance();
        ShowFragment(physicalFragment);

        // TODO: 25/06/2021 cargar datos del usuario en la navegación lateral 
        // TODO: 25/06/2021 centrar elementos del menú 
        //abre el nav drawer al pulsar en el boton
        btnMenu.setOnClickListener(v->{
            drawerlayout.openDrawer(GravityCompat.START);
        });

        tvLogOut.setOnClickListener(v -> {
           auth.signOut();
            Intent i = new Intent(this, MainActivity.class);        // Specify any activity here e.g. home or splash or login etc
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("EXIT", true);
            startActivity(i);
            finish();
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