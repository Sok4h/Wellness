package com.parrotdevs.wellness.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.parrotdevs.wellness.R;
import com.parrotdevs.wellness.activities.ExercisesActivity;
import com.parrotdevs.wellness.activities.PathActivity;
import com.parrotdevs.wellness.model.Category;
import com.parrotdevs.wellness.model.Exercise;
import com.parrotdevs.wellness.model.UserPath;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryView>{

    private ArrayList<Category> categoryArray;
    private ViewGroup group;
    Gson gson;
    String category;
    FirebaseDatabase db;

    public CategoryAdapter(){

        categoryArray = new ArrayList<>();
        db=FirebaseDatabase.getInstance();
        gson= new Gson();

    }
    @Override
    public CategoryView onCreateViewHolder(ViewGroup parent, int viewType) {
        group=parent;
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.meditation_card,parent,false);
        ConstraintLayout rowRoot = (ConstraintLayout) row;
        return new CategoryView(rowRoot);
    }

    @Override
    public void onBindViewHolder(CategoryView holder, int position) {

        holder.getTvTitle().setText(categoryArray.get(position).getName());
        holder.getTvDescription().setText(categoryArray.get(position).getCardDescription());
        Glide.with(group.getContext()).load(categoryArray.get(position).getImg()).into(holder.getImage());

        holder.getRoot().setOnClickListener(v->{

            category = gson.toJson(categoryArray.get(position));
            CheckForPath(position);



        });

    }

    private void CheckForPath(int position) {

    db.getReference("UsersPath").child(FirebaseAuth.getInstance().getUid()).orderByChild("categoryId").equalTo(categoryArray.get(position).getId()).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot snapshot) {

            if(snapshot.exists()){
                Intent intent = new Intent(group.getContext(), ExercisesActivity.class);
                intent.putExtra("category",category);
                group.getContext().startActivity(intent);
            }
            else{

                Intent intent = new Intent(group.getContext(), PathActivity.class);
                intent.putExtra("category",category);
                group.getContext().startActivity(intent);
            }
        }

        @Override
        public void onCancelled( DatabaseError error) {

        }
    });

    }

    @Override
    public int getItemCount() {
        return categoryArray.size();
    }

    public void AddCategory(Category category){
        categoryArray.add(category);
        notifyDataSetChanged();

    }

    public void Clear(){

        categoryArray.clear();
    }
}
