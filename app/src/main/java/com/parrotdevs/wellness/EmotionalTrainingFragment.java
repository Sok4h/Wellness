package com.parrotdevs.wellness;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmotionalTrainingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmotionalTrainingFragment extends Fragment {




    public EmotionalTrainingFragment() {
        // Required empty public constructor
    }

    public static EmotionalTrainingFragment newInstance() {
        EmotionalTrainingFragment fragment = new EmotionalTrainingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    private RecyclerView vertical;
    private CategoryAdapter adapter;
    LinearLayoutManager layoutManager;
    FirebaseDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_emotional_training, container, false);
        vertical = root.findViewById(R.id.rvVerticalEmotional);
        layoutManager = new LinearLayoutManager(getContext());
        vertical.setLayoutManager(layoutManager);
        adapter= new CategoryAdapter();
        vertical.setAdapter(adapter);
        db = FirebaseDatabase.getInstance();
        db.getReference("Categories").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot child: snapshot.getChildren()
                     ) {
                    adapter.Clear();
                    Category currentCategory = child.getValue(Category.class);
                    adapter.AddCategory(currentCategory);
                }
            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });

        return root;
    }
}