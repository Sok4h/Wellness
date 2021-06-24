package com.parrotdevs.wellness.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.parrotdevs.wellness.R;
import com.parrotdevs.wellness.activities.ExerciseActivity;
import com.parrotdevs.wellness.model.Exercise;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseView>{

    private Context mContext;
    private ArrayList<Exercise> exerciseArrayList;

    public ExerciseAdapter(Context mContext){

        this.mContext = mContext;
        exerciseArrayList= new ArrayList<>();
    }
    @Override
    public ExerciseView onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View row = inflater.inflate(R.layout.exercise_card,parent,false);
        ConstraintLayout rowRoot = (ConstraintLayout) row;
        return new ExerciseView(rowRoot);
    }

    @Override
    public void onBindViewHolder(ExerciseView holder, int position) {

    holder.getTitlePath().setText(exerciseArrayList.get(position).getDay());

    holder.getRoot().setOnClickListener(v -> {

        Intent intent = new Intent(mContext, ExerciseActivity.class);
        Gson gson = new Gson();
        String exerciseString = gson.toJson(exerciseArrayList.get(position));
        intent.putExtra("exercise",exerciseString);
        mContext.startActivity(intent);

    });
    }

    @Override
    public int getItemCount() {
        return exerciseArrayList.size();
    }

    public void AddExercise(Exercise exercise) {
        exerciseArrayList.add(exercise);
        notifyDataSetChanged();

    }
}
