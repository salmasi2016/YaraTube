package com.yaratech.yaratube.ui.home.mainpage.mainpage;

import com.yaratech.yaratube.data.model.Home;
import com.yaratech.yaratube.data.source.remote.LoadCallback;
import com.yaratech.yaratube.data.source.remote.Repository;

public class MainPagePresenter implements MainPageContract.Presenter {
    private MainPageContract.View iaView;
    private Repository repository = new Repository();

    public MainPagePresenter(MainPageContract.View iaView) {
        this.iaView = iaView;
    }

    @Override
    public void loadData() {
        iaView.showProgress();
        repository.loadMainPage(new LoadCallback() {
            @Override
            public void onLoadedData(Object data) {
                Home home = (Home) data;
                iaView.hideProgress();
                iaView.showMainPage(home.getHeaderItems(), home.getHomeItems());
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