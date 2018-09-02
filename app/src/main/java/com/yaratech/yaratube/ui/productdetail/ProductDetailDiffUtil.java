package com.yaratech.yaratube.ui.productdetail;

import android.support.v7.util.DiffUtil;

import com.yaratech.yaratube.data.model.Comment;

import java.util.ArrayList;

import io.reactivex.annotations.Nullable;

public class ProductDetailDiffUtil extends DiffUtil.Callback {

    ArrayList<Comment> oldComments;
    ArrayList<Comment> newComments;

    public ProductDetailDiffUtil(ArrayList<Comment> oldComments, ArrayList<Comment> newComments) {
        this.oldComments = oldComments;
        this.newComments = newComments;
    }

    @Override
    public int getOldListSize() {
        return oldComments.size();
    }

    @Override
    public int getNewListSize() {
        return newComments.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldComments.get(oldItemPosition).getId() == newComments.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldComments.get(oldItemPosition).equals(newComments.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}