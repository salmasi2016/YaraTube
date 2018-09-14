package com.yaratech.yaratube.ui.home.more.profile;

import android.content.Context;

import com.yaratech.yaratube.data.model.UserResponse;
import com.yaratech.yaratube.data.source.local.db.entity.User;
import com.yaratech.yaratube.data.source.remote.ApiResult;
import com.yaratech.yaratube.data.source.remote.UserRepository;

public class ProfilePresenter implements ProfileContract.Presenter {
    private ProfileContract.View iaView;
    private UserRepository repository;

    public ProfilePresenter(ProfileContract.View iaView, Context context) {
        this.iaView = iaView;
        repository = new UserRepository(context);
    }

    @Override
    public void postUser(User user) {
        iaView.showProgress();
        repository.sendUser(user,
                new ApiResult<UserResponse>() {

                    @Override
                    public void onSuccess(UserResponse result) {
                        iaView.hideProgress();
                        iaView.saveUser();
                    }

                    @Override
                    public void onFail(String message) {
                        iaView.hideProgress();
                        iaView.showErrorMessage(message);
                    }
                });
    }
}