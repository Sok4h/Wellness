package com.parrotdevs.wellness.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.parrotdevs.wellness.R;
import com.parrotdevs.wellness.activities.MeditationActivity;
import com.parrotdevs.wellness.model.Exercise;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseView>{

    private ArrayList<Exercise> exerciseArrayList;
    private Context context;
    private FirebaseDatabase db;
    private FirebaseAuth auth;
    Gson gson ;
    public ExerciseAdapter(Context context){

    exerciseArrayList = new ArrayList<>();

    this.context = context;
    auth = FirebaseAuth.getInstance();
    db=FirebaseDatabase.getInstance();
    gson = new Gson();
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




                    VerifyDay(position);




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

    public void VerifyDay(int position){

        db.getReference("UsersPath").child(auth.getUid()).orderByChild("categoryId").equalTo(exerciseArrayList.get(position).getCategoryId()).addListenerForSingleValueEvent(

                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        if(!snapshot.hasChildren()){

                             if(!exerciseArrayList.get(position).getDay().equals("1")){

                                Log.e("TAG", "Debes empezar por el dia 1" );
                                Toast.makeText(context, "Debes empezar por el dia 1", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent intent = new Intent(context, MeditationActivity.class);

                                String exerciseString = gson.toJson(exerciseArrayList.get(position));
                                intent.putExtra("exercise",exerciseString);
                                intent.putExtra("bgimg",exerciseArrayList.get(position).getBackgroundImg());
                                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                context.startActivity(intent);
                            }

                        }
                        else{

                            for(DataSnapshot child:snapshot.getChildren()){

                                Exercise tempExercise = child.getValue(Exercise.class);

                                if(Integer.parseInt(exerciseArrayList.get(position).getDay())<=Integer.parseInt(tempExercise.getDay())+1){
                                    Intent intent = new Intent(context, MeditationActivity.class);

                                    String exerciseString = gson.toJson(exerciseArrayList.get(position));
                                    intent.putExtra("exercise",exerciseString);
                                    intent.putExtra("bgimg",exerciseArrayList.get(position).getBackgroundImg());
                                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    context.startActivity(intent);

                                }
                                else{
                                    Log.e("TAG", "necesitas completar las lecciones anteriores" );
                                    Toast.makeText(context, "necesitas completar las lecciones anteriores", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }





                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                }
        );
    }
}
