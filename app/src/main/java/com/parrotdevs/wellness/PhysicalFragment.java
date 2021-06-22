package com.parrotdevs.wellness;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PhysicalFragment extends Fragment {

 public PhysicalFragment() {
        // Required empty public constructor
    }



    public static PhysicalFragment newInstance() {
        PhysicalFragment fragment = new PhysicalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root =inflater.inflate(R.layout.fragment_physical, container, false);

        return root;
    }
}