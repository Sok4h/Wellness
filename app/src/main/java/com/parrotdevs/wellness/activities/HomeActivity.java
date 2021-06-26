package com.parrotdevs.wellness.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.parrotdevs.wellness.fragments.EmotionalTrainingFragment;
import com.parrotdevs.wellness.R;
import com.parrotdevs.wellness.fragments.PhysicalFragment;
import com.parrotdevs.wellness.model.User;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerlayout;
    NavigationView navView;
    ImageView btnMenu, profilePic;
    BottomNavigationView bottomNavigationView;
    TextView tvLogOut, nameTab;
    FirebaseDatabase db;
    private EmotionalTrainingFragment emotionalFragment;
    private PhysicalFragment physicalFragment;
    Context context;
    private FirebaseAuth auth;
    View header;


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
        emotionalFragment = EmotionalTrainingFragment.newInstance();
        physicalFragment = PhysicalFragment.newInstance();
        db = FirebaseDatabase.getInstance();
        header = navView.getHeaderView(0);
        ShowFragment(physicalFragment);
        profilePic = (CircleImageView) header.findViewById(R.id.profilePicNav);
        nameTab = (TextView) header.findViewById(R.id.tvUsernameDrawer);
        context = this;
        Log.e("TAG", nameTab.getText().toString());
        LoadUserInfo();
        //abre el nav drawer al pulsar en el boton
        btnMenu.setOnClickListener(v -> {
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

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.physicalTraining:

                    ShowFragment(physicalFragment);
                    break;

                case R.id.emotionalTraining:

                    ShowFragment(emotionalFragment);
                    break;
            }

            return true;
        });

    }

    private void LoadUserInfo() {

        db.getReference("users").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                User user = snapshot.getValue(User.class);
                Log.e("TAG", user.getName());
                Glide.with(context).load(user.getUserPhoto()).centerCrop().placeholder(R.drawable.ic_outline_person_24).into(profilePic);
                nameTab.setText(user.getName());
            }


            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }

    public void ShowFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}