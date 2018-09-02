package com.yaratech.yaratube.ui.categorygrid;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Product;

import java.util.ArrayList;

public interface CategoryGridContract {

    interface View {
        void showProgress();

        void hideProgress();

        void showProducts(ArrayList<Product> products);

        void showToast();
    }

    interface Presenter {
        void loadData(Category category,int offset);
    }
}