package com.yaratech.yaratube.ui.productdetail.comment;

import android.content.Context;

import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.CommentResponse;
import com.yaratech.yaratube.data.source.local.db.LocalRepository;
import com.yaratech.yaratube.data.source.local.db.database.AppDataBase;
import com.yaratech.yaratube.data.source.remote.ApiResult;
import com.yaratech.yaratube.data.source.remote.UserRepository;

public class CommentPresenter implements CommentContract.Presenter {
    private CommentContract.View iaView;
    private UserRepository repository;
    private LocalRepository localRepository;

    public CommentPresenter(CommentContract.View iaView, Context context, AppDataBase database) {
        this.iaView = iaView;
        repository = new UserRepository(context);
        localRepository = new LocalRepository(database);
    }

    @Override
    public void sendComment(int productId, Comment comment) {
        repository.sendComment(new ApiResult<CommentResponse>() {
            @Override
            public void onSuccess(CommentResponse result) {
                iaView.showToast();
                iaView.dismissDialog();
            }

            @Override
            public void onFail(String errorMessage) {
                iaView.showErrorMessage(errorMessage);
            }
        }, productId, comment, localRepository.getToken());
    }
}