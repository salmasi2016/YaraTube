package com.yaratech.yaratube.ui.productdetail.comment;

import com.yaratech.yaratube.data.model.Comment;

public interface CommentContract {

    interface View {

        void showErrorMessage(String message);

        void dismissDialog();

        void showToast();
    }

    interface Presenter {

        void sendComment(int productId,Comment comment);
    }
}