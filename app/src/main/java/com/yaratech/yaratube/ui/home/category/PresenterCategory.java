package com.yaratech.yaratube.ui.home.category;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.source.remote.LoadCallback;
import com.yaratech.yaratube.data.source.remote.Repository;

import java.util.ArrayList;

public class PresenterCategory implements ContractCategory.Presenter {
    private ContractCategory.View iaView;
    private Repository repository = new Repository();

    public PresenterCategory(ContractCategory.View iaView) {
        this.iaView = iaView;
    }

    @Override
    public void loadData() {
        iaView.showProgress();
        repository.loadCategory(new LoadCallback.Categories() {
            @Override
            public void onLoadedData(ArrayList<Category> categories) {
                iaView.hideProgress();
                iaView.showCategories(categories);
            }
        });
    }
}