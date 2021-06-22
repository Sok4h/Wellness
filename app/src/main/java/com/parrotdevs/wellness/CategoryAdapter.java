package com.parrotdevs.wellness;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.UUID;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryView>{

    private ArrayList<Category> categoryArray;
    private ViewGroup group;

    public CategoryAdapter(){

        categoryArray = new ArrayList<Category>();

        categoryArray.add(new Category(UUID.randomUUID().toString(),"Kundalini","Spiritual activities to relax your mind and body","https://mychiptuningfiles.com/es/image/models/YEdPJDTEJGtIpmJUdcezezZIfZtG5p.png"
                ,"30"));
        categoryArray.add(new Category(UUID.randomUUID().toString(),"Tonglen","Clear your mind and let out the pain and anguish of your being.","https://mychiptuningfiles.com/es/image/models/YEdPJDTEJGtIpmJUdcezezZIfZtG5p.png"
                ,"30"));
        categoryArray.add(new Category(UUID.randomUUID().toString(),"Kundalini ","Spiritual activities to relax your mind and body","https://mychiptuningfiles.com/es/image/models/YEdPJDTEJGtIpmJUdcezezZIfZtG5p.png"
                ,"30"));
        categoryArray.add(new Category(UUID.randomUUID().toString(),"Kundalini ","Spiritual activities to relax your mind and body","https://mychiptuningfiles.com/es/image/models/YEdPJDTEJGtIpmJUdcezezZIfZtG5p.png"
                ,"30"));
        categoryArray.add(new Category(UUID.randomUUID().toString(),"Kundalini ","Spiritual activities to relax your mind and body","https://mychiptuningfiles.com/es/image/models/YEdPJDTEJGtIpmJUdcezezZIfZtG5p.png"
                ,"30"));



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
        holder.getTvDescription().setText(categoryArray.get(position).description);
        Glide.with(group.getContext()).load(categoryArray.get(position).getUrlImage()).into(holder.getImage());

    }

    @Override
    public int getItemCount() {
        return categoryArray.size();
    }
}
