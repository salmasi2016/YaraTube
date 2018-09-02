package com.yaratech.yaratube.ui.categorygrid;

import android.support.v7.util.DiffUtil;

import com.yaratech.yaratube.data.model.Product;

import java.util.ArrayList;

import io.reactivex.annotations.Nullable;

public class CategoryGridDiffUtil extends DiffUtil.Callback {

    ArrayList<Product> oldProducts;
    ArrayList<Product> newProducts;

    public CategoryGridDiffUtil(ArrayList<Product> oldProducts, ArrayList<Product> newProducts) {
        this.oldProducts = oldProducts;
        this.newProducts = newProducts;
    }

    @Override
    public int getOldListSize() {
        return oldProducts.size();
    }

    @Override
    public int getNewListSize() {
        return newProducts.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldProducts.get(oldItemPosition).getId() == newProducts.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldProducts.get(oldItemPosition).equals(newProducts.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}