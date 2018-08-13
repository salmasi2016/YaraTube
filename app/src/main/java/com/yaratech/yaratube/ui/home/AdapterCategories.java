package com.yaratech.yaratube.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.model.Category;
import com.yaratech.yaratube.util.Tool;

import java.util.ArrayList;

public class AdapterCategories extends RecyclerView.Adapter<AdapterCategories.viewHolder> {
    private ArrayList<Category> categories;

    @Override
    public AdapterCategories.viewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_category, parent, false);
        return new AdapterCategories.viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterCategories.viewHolder holder, int position) {
        Category category = categories.get(position);
        holder.tvCategory.setText(category.getTitle());
        Glide.with(holder.itemView.getContext()).load(Tool.BASE_URL+category.getAvatar().getXxxdpi()).into(holder.ivLogo);
    }

    @Override
    public int getItemCount() {
        if (categories == null) {
            return 0;
        }
        return categories.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        ImageView ivLogo;

        viewHolder(View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.list_item_category_tv_category);
            ivLogo = itemView.findViewById(R.id.list_item_category_iv_img);
        }
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }
}