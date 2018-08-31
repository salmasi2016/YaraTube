package com.yaratech.yaratube.ui.home.category;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.util.Constant;
import com.yaratech.yaratube.data.model.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {
    private ArrayList<Category> categories;
    private Interaction interaction;

    public CategoryAdapter(Interaction interaction) {
        this.interaction = interaction;
    }

    @Override
    public CategoryAdapter.viewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_category, parent, false);
        return new CategoryAdapter.viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.viewHolder holder, int position) {
        Category category = categories.get(position);
        holder.tvTitle.setText(category.getTitle());
        Glide.with(holder.itemView.getContext()).load(Constant.BASE_URL + category.getAvatar()).into(holder.ivCategory);
    }

    @Override
    public int getItemCount() {
        if (categories == null) {
            return 0;
        }
        return categories.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivCategory;

        viewHolder(final View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.list_item_category_tv_title);
            ivCategory = itemView.findViewById(R.id.list_item_category_iv_category);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    interaction.setCategoryToFragmentCategory(getCategories().get(getAdapterPosition()));
                }
            });
        }
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    public interface Interaction {

        void setCategoryToFragmentCategory(Category category);
    }
}