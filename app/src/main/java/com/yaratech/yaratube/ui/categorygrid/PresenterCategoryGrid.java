package com.yaratech.yaratube.ui.categorygrid;

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
        repository.loadCategoryGrid(new LoadCallback() {
            @Override
            public void onLoadedData(Object data) {
                iaView.hideProgress();
                iaView.showProducts((ArrayList<Product>) data);
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
        }, category);
    }
}