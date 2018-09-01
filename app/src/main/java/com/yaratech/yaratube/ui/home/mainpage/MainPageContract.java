package com.yaratech.yaratube.ui.home.mainpage;

import com.yaratech.yaratube.data.model.HeaderItem;
import com.yaratech.yaratube.data.model.HomeItem;

import java.util.ArrayList;

public interface MainPageContract {

    interface View {
        void showProgress();

        void hideProgress();

        void showMainPage(ArrayList<HeaderItem> headerItems, ArrayList<HomeItem> homeItems);

        void showToast();
    }

    interface Presenter {
        void loadData();
    }
}