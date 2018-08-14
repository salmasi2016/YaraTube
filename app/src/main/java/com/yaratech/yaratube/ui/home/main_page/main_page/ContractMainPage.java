package com.yaratech.yaratube.ui.home.main_page.main_page;

import com.yaratech.yaratube.data.model.HeaderItem;
import com.yaratech.yaratube.data.model.HomeItem;

import java.util.ArrayList;

public interface ContractMainPage {

    interface View {
        void showProgress();

        void hideProgress();

        void showMainPage(ArrayList<HeaderItem> headerItems, ArrayList<HomeItem> homeItems);
    }

    interface Presenter {
        void loadData();
    }
}