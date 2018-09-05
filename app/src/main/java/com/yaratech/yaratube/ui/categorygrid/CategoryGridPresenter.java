package com.yaratech.yaratube.ui.categorygrid;

import android.content.Context;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.source.remote.ApiResult;
import com.yaratech.yaratube.data.source.remote.Repository;

import java.util.ArrayList;

public class CategoryGridPresenter implements CategoryGridContract.Presenter {
    private CategoryGridContract.View iaView;
    private Repository repository;

    public CategoryGridPresenter(CategoryGridContract.View iaView) {
        this.iaView = iaView;
        repository = new Repository();
    }

    @Override
    public void loadData(Category category, int offset) {
        iaView.showProgress();
        repository.loadCategoryGrid(category, offset, new ApiResult<ArrayList<Product>>() {
            @Override
            public void onSuccess(ArrayList<Product> result) {
                iaView.hideProgress();
                iaView.showProducts(result);
            }

            @Override
            public void onFail(String errorMessage) {
                iaView.hideProgress();
                iaView.showErrorMessage(errorMessage);
            }
        });
    }
}