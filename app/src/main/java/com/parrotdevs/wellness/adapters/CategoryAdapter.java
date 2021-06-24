package com.parrotdevs.wellness.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.parrotdevs.wellness.R;
import com.parrotdevs.wellness.activities.PathActivity;
import com.parrotdevs.wellness.model.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryView>{

    private ArrayList<Category> categoryArray;
    private ViewGroup group;

    public CategoryAdapter(){

        categoryArray = new ArrayList<>();

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

        Log.e("TAG", categoryArray.get(position).getId());
        holder.getTvTitle().setText(categoryArray.get(position).getName());
        holder.getTvDescription().setText(categoryArray.get(position).getCardDescription());
        Glide.with(group.getContext()).load(categoryArray.get(position).getImg()).into(holder.getImage());
        holder.getRoot().setOnClickListener(v->{

            Intent intent = new Intent(group.getContext(), PathActivity.class);
            Gson gson= new Gson();
            String category = gson.toJson(categoryArray.get(position));
            intent.putExtra("category",category);
            group.getContext().startActivity(intent);

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
