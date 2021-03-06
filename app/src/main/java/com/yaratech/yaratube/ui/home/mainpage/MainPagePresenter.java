package com.yaratech.yaratube.ui.home.mainpage;

import android.util.Log;

import com.yaratech.yaratube.data.model.Home;
import com.yaratech.yaratube.data.source.remote.ApiResult;
import com.yaratech.yaratube.data.source.remote.Repository;

public class MainPagePresenter implements MainPageContract.Presenter {
    private MainPageContract.View iaView;
    private Repository repository;

    public MainPagePresenter(MainPageContract.View iaView) {
        this.iaView = iaView;
        repository = new Repository();
    }

    @Override
    public void loadData() {
        iaView.showProgress();
        repository.loadMainPage(new ApiResult<Home>() {
            @Override
            public void onSuccess(Home result) {
                iaView.hideProgress();
                iaView.showMainPage(result.getHeaderItems(), result.getHomeItems());
            }

            @Override
            public void onFail(String errorMessage) {
                iaView.hideProgress();
                iaView.showErrorMessage(errorMessage);
            }
        });
    }
}