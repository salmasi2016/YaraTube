package com.yaratech.yaratube.data.source.remote;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.HeaderItem;
import com.yaratech.yaratube.data.model.HomeItem;
import com.yaratech.yaratube.data.model.Product;

import java.util.ArrayList;

public interface LoadCallback {

    interface Categories {
        void onLoadedData(ArrayList<Category> categories);
    }

    interface MainPage {
        void onLoadedData(ArrayList<HeaderItem> headerItems, ArrayList<HomeItem> homeItems);
    }

    interface CategoryGrid {
        void onLoadedData(ArrayList<Product> products);
    }
}