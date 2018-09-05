package com.yaratech.yaratube.ui.productdetail;

import android.content.Context;

import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.source.remote.ApiResult;
import com.yaratech.yaratube.data.source.remote.Repository;

import java.util.ArrayList;

public class ProductDetailPresenter implements ProductDetailContract.Presenter {
    private ProductDetailContract.View iaView;
    private Repository repository;

    public ProductDetailPresenter(ProductDetailContract.View iaView, Context context) {
        this.iaView = iaView;
        repository = new Repository(context);
    }

    @Override
    public void loadProduct(int productId) {
        iaView.showProgress();
        repository.loadProduct(productId, new ApiResult<Product>() {
            @Override
            public void onSuccess(Product result) {
                iaView.isProductLoaded();
                iaView.showProduct(result);
            }

            @Override
            public void onFail(String errorMessage) {
                iaView.isProductLoaded();
                iaView.showErrorMessage(errorMessage);
            }
        });
    }

    @Override
    public void loadComment(int productId) {
        repository.loadComment(productId, new ApiResult<ArrayList<Comment>>() {
            @Override
            public void onSuccess(ArrayList<Comment> result) {
                iaView.isCommentLoaded();
                iaView.showComments(result);
            }

            @Override
            public void onFail(String errorMessage) {
                iaView.isCommentLoaded();
                iaView.showErrorMessage(errorMessage);
            }
        });
    }
}