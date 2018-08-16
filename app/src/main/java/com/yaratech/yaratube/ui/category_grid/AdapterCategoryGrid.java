package com.yaratech.yaratube.ui.category_grid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.util.Tool;

import java.util.ArrayList;

public class AdapterCategoryGrid extends RecyclerView.Adapter<AdapterCategoryGrid.viewHolder> {
    private ArrayList<Product> products;

    @Override
    public AdapterCategoryGrid.viewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_category_grid, parent, false);
        return new AdapterCategoryGrid.viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterCategoryGrid.viewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvName.setText(product.getName());
        holder.tvShortDescription.setText(product.getShortDescription());
        Glide.with(holder.itemView.getContext()).load(Tool.BASE_URL+product.getAvatar().getXxxdpi()).into(holder.ivVideo);
    }

    @Override
    public int getItemCount() {
        if (products == null) {
            return 0;
        }
        return products.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvShortDescription;
        ImageView ivVideo;

        viewHolder(final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.list_item_category_grid_tv_name);
            tvShortDescription = itemView.findViewById(R.id.list_item_category_grid_tv_short_description);
            ivVideo=itemView.findViewById(R.id.list_item_category_grid_iv_video);
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }
}