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
import com.parrotdevs.wellness.activities.MeditationActivity;
import com.parrotdevs.wellness.model.Exercise;

import java.util.ArrayList;

public class HistoyAdapter  extends RecyclerView.Adapter<HistoryView>{

    private ArrayList<Exercise> exerciseArrayList;
    private Context context;

    public HistoyAdapter(Context context){

    exerciseArrayList = new ArrayList<>();
    this.context = context;
    }

    @Override
    public HistoryView onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(this.context);
        View row = inflater.inflate(R.layout.card_history,parent,false);
        ConstraintLayout  rowRoot = (ConstraintLayout) row;
        return new HistoryView(rowRoot);
    }

    
    @Override
    public void onBindViewHolder(HistoryView holder, int position) {

        // TODO: 25/06/2021 mandar intent fondo 
        holder.getTvDayHistory().setText("Day "+ exerciseArrayList.get(position).getDay());
        holder.getTvNameHistory().setText(exerciseArrayList.get(position).getCategoryName());
        holder.getRoot().setOnClickListener(v->{

            Intent intent = new Intent(context, MeditationActivity.class);
            Gson gson = new Gson();
            String exerciseString = gson.toJson(exerciseArrayList.get(position));
            intent.putExtra("exercise",exerciseString);
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
