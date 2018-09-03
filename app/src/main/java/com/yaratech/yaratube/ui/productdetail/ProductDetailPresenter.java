package com.yaratech.yaratube.ui.productdetail;

import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.source.remote.LoadCallback;
import com.yaratech.yaratube.data.source.remote.Repository;
import com.yaratech.yaratube.ui.productdetail.ProductDetailContract;

import java.util.ArrayList;

public class ProductDetailPresenter implements ProductDetailContract.Presenter {
    private ProductDetailContract.View iaView;
    private Repository repository = new Repository();

    public ProductDetailPresenter(ProductDetailContract.View iaView) {
        this.iaView = iaView;
    }

    @Override
    public void loadProduct(int productId) {
        iaView.showProgress();
        repository.loadProduct(productId,new LoadCallback() {

            @Override
            public void onLoadedData(Object data) {
                iaView.isProductLoaded();
                iaView.showProduct((Product) data);
            }

            @Override
            public void noInternet() {
                iaView.isProductLoaded();
                iaView.showToast();
            }

            @Override
            public void onDataNotAvailable() {
                iaView.isProductLoaded();
                iaView.showToast();
            }
        });
    }

    @Override
    public void loadComment(int productId) {
        repository.loadComment(productId,new LoadCallback() {
            @Override
            public void onLoadedData(Object data) {
                iaView.isCommentLoaded();
                iaView.showComments((ArrayList<Comment>) data);
            }

            @Override
            public void noInternet() {
                iaView.isCommentLoaded();
                iaView.showToast();
            }

            @Override
            public void onDataNotAvailable() {
                iaView.isCommentLoaded();
                iaView.showToast();
            }
        });
    }
}