package com.parrotdevs.wellness.adapters;


import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.parrotdevs.wellness.R;


public class ExerciseView extends RecyclerView.ViewHolder {
    ConstraintLayout root;
    TextView tvDayHistory,tvNameHistory;

    public ExerciseView(ConstraintLayout root) {

        super(root);
        this.root = root;

        tvDayHistory =root.findViewById(R.id.tvDayHistory);
        tvNameHistory =root.findViewById(R.id.tvNameHistory);
    }

    public ConstraintLayout getRoot() {
        return root;
    }

    public void setRoot(ConstraintLayout root) {
        this.root = root;
    }

    public TextView getTvDayHistory() {
        return tvDayHistory;
    }

    public void setTvDayHistory(TextView tvDayHistory) {
        this.tvDayHistory = tvDayHistory;
    }

    public TextView getTvNameHistory() {
        return tvNameHistory;
    }

    public void setTvNameHistory(TextView tvNameHistory) {
        this.tvNameHistory = tvNameHistory;
    }
}
