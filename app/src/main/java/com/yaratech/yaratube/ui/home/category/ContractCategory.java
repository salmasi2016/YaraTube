package com.yaratech.yaratube.ui.home.category;

import com.yaratech.yaratube.data.model.Category;

import java.util.ArrayList;

public interface ContractCategory {

    interface View {
        void showProgress();

        void hideProgress();

        void showCategories(ArrayList<Category> categories);
    }

    interface Presenter {
        void loadData();
    }
}