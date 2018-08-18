package com.yaratech.yaratube.ui.product_detail;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.HeaderItem;
import com.yaratech.yaratube.data.model.Product;

import java.util.ArrayList;

public interface ContractProductDetail {

    interface View {
        void showProgress();

        void hideProgress();

        void showComments(ArrayList<Comment> comments);

        void showToast();
    }

    interface Presenter {

        void loadDataByProduct(Product product);

        void loadDataByHeaderItem(HeaderItem headerItem);
    }
}