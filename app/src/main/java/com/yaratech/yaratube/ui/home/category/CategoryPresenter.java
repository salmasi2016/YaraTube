package com.yaratech.yaratube.ui.home.category;

import android.content.Context;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.source.remote.ApiResult;
import com.yaratech.yaratube.data.source.remote.Repository;

import java.util.ArrayList;

public class CategoryPresenter implements CategoryContract.Presenter {
    private CategoryContract.View iaView;
    private Repository repository;

    public CategoryPresenter(CategoryContract.View iaView, Context context) {
        this.iaView = iaView;
        repository = new Repository(context);
    }

    @Override
    public void loadData() {
        iaView.showProgress();
        repository.loadCategory(new ApiResult<ArrayList<Category>>() {
            @Override
            public void onSuccess(ArrayList<Category> result) {
                iaView.hideProgress();
                iaView.showCategories(result);
            }

            @Override
            public void onFail(String errorMessage) {
                iaView.hideProgress();
                iaView.showErrorMessage(errorMessage);
            }
        });
    }
}