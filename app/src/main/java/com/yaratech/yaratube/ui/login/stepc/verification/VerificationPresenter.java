package com.yaratech.yaratube.ui.login.stepc.verification;

import android.content.Context;

import com.yaratech.yaratube.data.model.Activation;
import com.yaratech.yaratube.data.source.local.db.LocalRepository;
import com.yaratech.yaratube.data.source.local.db.database.AppDataBase;
import com.yaratech.yaratube.data.source.local.db.entity.User;
import com.yaratech.yaratube.data.source.remote.ApiResult;
import com.yaratech.yaratube.data.source.remote.UserRepository;

public class VerificationPresenter implements VerificationContract.Presenter {
    private VerificationContract.View iaView;
    private UserRepository repository;
    private LocalRepository localRepository;

    public VerificationPresenter(VerificationContract.View iaView, Context context, AppDataBase database) {
        this.iaView = iaView;
        repository = new UserRepository(context);
        localRepository = new LocalRepository(database);
    }

    @Override
    public void sendVerificationCode(int verificationCode) {
        repository.sendVerificationCode(verificationCode,
                new ApiResult<Activation>() {

                    @Override
                    public void onSuccess(Activation result) {
                        User user = new User();
                        user.setToken(result.getToken());
                        localRepository.InsertUser(user);
                        iaView.saveUser();
                        iaView.dismissDialog();
                    }

                    @Override
                    public void onFail(String message) {
                        iaView.showErrorMessage(message);
                    }
                });
    }
}