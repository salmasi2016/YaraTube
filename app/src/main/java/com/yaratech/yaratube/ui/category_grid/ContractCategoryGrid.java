package com.yaratech.yaratube.ui.category_grid;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Product;

import java.util.ArrayList;

public interface ContractCategoryGrid {

    interface View {
        void showProgress();

        void hideProgress();

        void showProducts(ArrayList<Product> products);

        void showToast();
    }

    interface Presenter {
        void loadData(Category category);
    }
}