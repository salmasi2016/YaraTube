package com.yaratech.yaratube.ui.category_grid;

import android.util.Log;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.source.remote.LoadCallback;
import com.yaratech.yaratube.data.source.remote.Repository;

import java.util.ArrayList;

public class PresenterCategoryGrid implements ContractCategoryGrid.Presenter {
    private ContractCategoryGrid.View iaView;
    private Repository repository = new Repository();

    public PresenterCategoryGrid(ContractCategoryGrid.View iaView) {
        this.iaView = iaView;
    }

    @Override
    public void loadData(Category category) {
        iaView.showProgress();
        repository.loadCategoryGrid(new LoadCallback.CategoryGrid() {
            @Override
            public void onLoadedData(ArrayList<Product> products) {
                iaView.hideProgress();
                iaView.showProducts(products);
            }
        },category);
    }
}