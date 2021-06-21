package com.parrotdevs.wellness;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmotionalTrainingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmotionalTrainingFragment extends Fragment {




    public EmotionalTrainingFragment() {
        // Required empty public constructor
    }

    public static EmotionalTrainingFragment newInstance(String param1, String param2) {
        EmotionalTrainingFragment fragment = new EmotionalTrainingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_emotional_training, container, false);
        return root;
    }
}