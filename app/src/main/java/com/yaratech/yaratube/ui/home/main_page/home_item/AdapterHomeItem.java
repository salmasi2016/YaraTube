package com.yaratech.yaratube.ui.home.main_page.home_item;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.ui.category_grid.AdapterCategoryGrid;
import com.yaratech.yaratube.util.Tool;

import java.util.ArrayList;

public class AdapterHomeItem extends RecyclerView.Adapter<AdapterHomeItem.viewHolder> {
    private ArrayList<Product> products;
    private Interaction interaction;

    public AdapterHomeItem(Interaction interaction) {
        this.interaction = interaction;
    }

    @Override
    public AdapterHomeItem.viewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_home_item, parent, false);
        return new AdapterHomeItem.viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHomeItem.viewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvName.setText(product.getName());
        holder.tvShortDescription.setText(product.getShortDescription());
        Glide.with(holder.itemView.getContext()).load(Tool.BASE_URL + product.getAvatar().getXxxdpi()).into(holder.ivVideo);
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
            ivVideo = itemView.findViewById(R.id.list_item_home_item_iv_video);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    interaction.setProductToFragmentProductDetail(getProducts().get(getAdapterPosition()));
                }
            });
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public interface Interaction {
        void setProductToFragmentProductDetail(Product product);
    }
}