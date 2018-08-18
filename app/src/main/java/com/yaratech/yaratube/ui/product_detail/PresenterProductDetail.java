package com.yaratech.yaratube.ui.product_detail;

import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.HeaderItem;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.source.remote.LoadCallback;
import com.yaratech.yaratube.data.source.remote.Repository;

import java.util.ArrayList;

public class PresenterProductDetail implements ContractProductDetail.Presenter {
    private ContractProductDetail.View iaView;
    private Repository repository = new Repository();

    public PresenterProductDetail(ContractProductDetail.View iaView) {
        this.iaView = iaView;
    }

    @Override
    public void loadDataByProduct(Product product) {
        iaView.showProgress();
        repository.loadComment(new LoadCallback() {
            @Override
            public void onLoadedData(Object data) {
                iaView.hideProgress();
                iaView.showComments((ArrayList<Comment>) data);
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
        }, product);
    }

    @Override
    public void loadDataByHeaderItem(HeaderItem headerItem) {
        iaView.showProgress();
        repository.loadComment(new LoadCallback() {
            @Override
            public void onLoadedData(Object data) {
                iaView.hideProgress();
                iaView.showComments((ArrayList<Comment>) data);
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
        }, headerItem);
    }
}