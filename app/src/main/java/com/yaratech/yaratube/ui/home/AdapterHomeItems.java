package com.yaratech.yaratube.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.remote.APIClient;
import com.yaratech.yaratube.model.Product;
import com.yaratech.yaratube.util.Tool;

import java.util.ArrayList;

public class AdapterHomeItems extends RecyclerView.Adapter<AdapterHomeItems.viewHolder> {
    private ArrayList<Product> products;

    @Override
    public AdapterHomeItems.viewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_home_item, parent, false);
        return new AdapterHomeItems.viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHomeItems.viewHolder holder, int position) {
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

        viewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.list_item_home_item_tv_name);
            tvShortDescription = itemView.findViewById(R.id.list_item_home_item_tv_short_description);
            ivVideo=itemView.findViewById(R.id.list_item_home_item_iv_video);
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