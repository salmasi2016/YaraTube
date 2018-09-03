package com.yaratech.yaratube.ui.productdetail;

import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.Product;

import java.util.ArrayList;

public interface ProductDetailContract {

    interface View {
        void showProgress();

        void isProductLoaded();

        void isCommentLoaded();

        void showComments(ArrayList<Comment> comments);

        void showProduct(Product product);

        void showToast();
    }

    interface Presenter {

        void loadComment(int productId);

        void loadProduct(int productId);
    }
}