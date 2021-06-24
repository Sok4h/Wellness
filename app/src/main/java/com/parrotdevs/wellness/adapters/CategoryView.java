package com.parrotdevs.wellness.adapters;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.parrotdevs.wellness.R;

public class CategoryView extends RecyclerView.ViewHolder{

    private ConstraintLayout root;
    private TextView tvTitle,tvDescription;
    private ImageView image;

    public CategoryView(ConstraintLayout root) {

        super(root);
        this.root = root;

        tvTitle = root.findViewById(R.id.categoryCardTitle);
        tvDescription = root.findViewById(R.id.categoryCardDescription);
        image = root.findViewById(R.id.categoryCardImg);

    }

    public ConstraintLayout getRoot() {
        return root;
    }

    public void setRoot(ConstraintLayout root) {
        this.root = root;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public TextView getTvDescription() {
        return tvDescription;
    }

    public void setTvDescription(TextView tvDescription) {
        this.tvDescription = tvDescription;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
