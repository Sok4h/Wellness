package com.parrotdevs.wellness.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.parrotdevs.wellness.R;
import com.parrotdevs.wellness.activities.MeditationActivity;
import com.parrotdevs.wellness.model.Exercise;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseView>{

    private ArrayList<Exercise> exerciseArrayList;
    private Context context;
    public ExerciseAdapter(Context context){

    exerciseArrayList = new ArrayList<>();

    this.context = context;
    }

    @Override
    public ExerciseView onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(this.context);
        View row = inflater.inflate(R.layout.exercise_card,parent,false);
        ConstraintLayout  rowRoot = (ConstraintLayout) row;
        return new ExerciseView(rowRoot);
    }

    
    @Override
    public void onBindViewHolder(ExerciseView holder, int position) {


        holder.getTvDayHistory().setText("Day "+ exerciseArrayList.get(position).getDay());
        holder.getTvNameHistory().setText(exerciseArrayList.get(position).getCategoryName());
        holder.getRoot().setOnClickListener(v->{

            Intent intent = new Intent(context, MeditationActivity.class);
            Gson gson = new Gson();
            String exerciseString = gson.toJson(exerciseArrayList.get(position));
            intent.putExtra("exercise",exerciseString);
            intent.putExtra("bgimg",exerciseArrayList.get(position).getBackgroundImg());
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return exerciseArrayList.size();
    }

    public void AddExercise(Exercise exercise){

        exerciseArrayList.add(exercise);
        notifyDataSetChanged();
    }
}
