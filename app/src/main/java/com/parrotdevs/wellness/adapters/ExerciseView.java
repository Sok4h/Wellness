package com.parrotdevs.wellness.adapters;

import android.view.View;
import android.widget.TextView;


import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.parrotdevs.wellness.R;

public class ExerciseView extends RecyclerView.ViewHolder {

    private ConstraintLayout root;
    private TextView titlePath;
    public ExerciseView( ConstraintLayout root) {

        super(root);
        this.root = root;
        titlePath=root.findViewById(R.id.exerciseDay);

    }

    public ConstraintLayout getRoot() {
        return root;
    }

    public void setRoot(ConstraintLayout root) {
        this.root = root;
    }

    public TextView getTitlePath() {
        return titlePath;
    }

    public void setTitlePath(TextView titlePath) {
        this.titlePath = titlePath;
    }
}
