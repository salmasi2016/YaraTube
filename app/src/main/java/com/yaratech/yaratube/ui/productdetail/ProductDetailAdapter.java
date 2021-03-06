package com.yaratech.yaratube.ui.productdetail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Comment;

import java.util.ArrayList;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.viewHolder> {
    private ArrayList<Comment> comments;

    public ProductDetailAdapter() {
        comments = new ArrayList<>();
    }

    @Override
    public ProductDetailAdapter.viewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_product_detail, parent, false);
        return new ProductDetailAdapter.viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductDetailAdapter.viewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.tvCommentText.setText(comment.getCommentText());
    }

    @Override
    public int getItemCount() {
        if (comments == null) {
            return 0;
        }
        return comments.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvCommentText;

        viewHolder(final View itemView) {
            super(itemView);
            tvCommentText = itemView.findViewById(R.id.list_item_product_detail_tv_commentText);
        }
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
        notifyDataSetChanged();
    }
}