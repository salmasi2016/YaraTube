package com.yaratech.yaratube.ui.login.stepa.login;

import android.content.Context;

import com.yaratech.yaratube.data.model.GoogleResponse;
import com.yaratech.yaratube.data.source.local.db.LocalRepository;
import com.yaratech.yaratube.data.source.local.db.database.AppDataBase;
import com.yaratech.yaratube.data.source.local.db.entity.User;
import com.yaratech.yaratube.data.source.remote.ApiResult;
import com.yaratech.yaratube.data.source.remote.UserRepository;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View iaView;
    private UserRepository repository;
    private LocalRepository localRepository;

    public LoginPresenter(LoginContract.View iaView, Context context, AppDataBase database) {
        this.iaView = iaView;
        repository = new UserRepository(context);
        localRepository = new LocalRepository(database);
    }

    @Override
    public void dialogPhone() {
        iaView.showDialogPhone();
    }

    @Override
    public void getTokenIdUser(String tokenIdGoogle) {
        repository.sendGoogleLogin(tokenIdGoogle,
                new ApiResult<GoogleResponse>() {

                    @Override
                    public void onSuccess(GoogleResponse result) {
                        User user = new User();
                        user.setToken(result.getToken());
                        localRepository.InsertUser(user);
                        iaView.dissmissDialog();
                    }

                    @Override
                    public void onFail(String message) {
                        iaView.showErrorMessage(message);
                    }
                });
    }
}