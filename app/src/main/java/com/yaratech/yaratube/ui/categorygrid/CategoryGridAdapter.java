package com.yaratech.yaratube.ui.categorygrid;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.util.Constant;

import java.util.ArrayList;

public class CategoryGridAdapter extends RecyclerView.Adapter<CategoryGridAdapter.viewHolder> {
    private ArrayList<Product> products;
    private Interaction interaction;

    public CategoryGridAdapter(Interaction interaction) {
        this.interaction = interaction;
        products = new ArrayList<>();
    }

    @Override
    public CategoryGridAdapter.viewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_category_grid, parent, false);
        return new CategoryGridAdapter.viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryGridAdapter.viewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvName.setText(product.getName());
        holder.tvShortDescription.setText(product.getShortDescription());
        Glide.with(holder.itemView.getContext()).load(Constant.BASE_URL + product.getFeatureAvatar().getXxxdpi()).into(holder.ivVideo);
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
            ivVideo = itemView.findViewById(R.id.list_item_category_grid_iv_video);
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
        ArrayList<Product> total = new ArrayList<>();
        total.addAll(this.products);
        total.addAll(products);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new CategoryGridDiffUtil(this.products, total));
        this.products.addAll(products);
        diffResult.dispatchUpdatesTo(this);
    }

    public interface Interaction {
        void setProductToFragmentProductDetail(Product product);
    }
}