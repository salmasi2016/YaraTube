package com.yaratech.yaratube.ui.home.category;

import com.yaratech.yaratube.data.model.Category;

import java.util.ArrayList;

public interface CategoryContract {

    interface View {
        void showProgress();

        void hideProgress();

        void showCategories(ArrayList<Category> categories);

        void showErrorMessage(String message);
    }

    interface Presenter {
        void loadData();
    }
}