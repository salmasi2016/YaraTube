package com.yaratech.yaratube.ui.home.main_page.main_page;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.HeaderItem;
import com.yaratech.yaratube.data.model.HomeItem;
import com.yaratech.yaratube.data.source.remote.LoadCallback;
import com.yaratech.yaratube.data.source.remote.Repository;
import com.yaratech.yaratube.ui.home.category.ContractCategory;

import java.util.ArrayList;

public class PresenterMainPage implements ContractMainPage.Presenter {
    private ContractMainPage.View iaView;
    private Repository repository = new Repository();

    public PresenterMainPage(ContractMainPage.View iaView) {
        this.iaView = iaView;
    }

    @Override
    public void loadData() {
        iaView.showProgress();
        repository.loadMainPage(new LoadCallback.MainPage() {
            @Override
            public void onLoadedData(ArrayList<HeaderItem> headerItems, ArrayList<HomeItem> homeItems) {
                iaView.hideProgress();
                iaView.showMainPage(headerItems,homeItems);
            }
        });
    }
}