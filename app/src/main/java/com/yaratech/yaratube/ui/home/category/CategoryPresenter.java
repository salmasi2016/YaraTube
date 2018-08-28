package com.yaratech.yaratube.ui.home.category;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.source.remote.LoadCallback;
import com.yaratech.yaratube.data.source.remote.Repository;

import java.util.ArrayList;

public class CategoryPresenter implements CategoryContract.Presenter {
    private CategoryContract.View iaView;
    private Repository repository = new Repository();

    public CategoryPresenter(CategoryContract.View iaView) {
        this.iaView = iaView;
    }

    @Override
    public void loadData() {
        iaView.showProgress();
        repository.loadCategory(new LoadCallback() {
            @Override
            public void onLoadedData(Object data) {
                iaView.hideProgress();
                iaView.showCategories((ArrayList<Category>) data);
            }

            @Override
            public void noInternet() {
                iaView.hideProgress();
                iaView.showToast();
            }

            @Override
            public void onDataNotAvailable() {
                iaView.hideProgress();
                iaView.showToast();
            }
        });
    }
}