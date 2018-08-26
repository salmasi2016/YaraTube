package com.yaratech.yaratube.ui.login.step.verification;

import android.content.Context;

import com.yaratech.yaratube.data.model.Activation;
import com.yaratech.yaratube.data.source.local.LocalRepository;
import com.yaratech.yaratube.data.source.local.db.database.AppDataBase;
import com.yaratech.yaratube.data.source.local.db.entity.User;
import com.yaratech.yaratube.data.source.remote.ApiResult;
import com.yaratech.yaratube.data.source.remote.UserRepository;

public class PresenterVerification implements ContractVerification.Presenter {
    private ContractVerification.View iaView;
    private UserRepository repository;
    private AppDataBase database;
    private Context context;
    private LocalRepository localRepository;

    public PresenterVerification(ContractVerification.View iaView, Context context, AppDataBase database) {
        this.iaView = iaView;
        this.context = context;
        repository = new UserRepository(context);
        this.database = database;
        localRepository = new LocalRepository(database);
    }

    @Override
    public void sendVerificationCode(String phoneNumber, String deviceId, int verificationCode) {
        repository.sendVerificationCode(new ApiResult<Activation>() {
            @Override
            public void onSuccess(Activation result) {

                User user = new User();
                user.setToken(result.getToken());
                localRepository.loginUser(user);
                iaView.saveUser();
            }

            @Override
            public void onFail(String message) {
                iaView.showErrorMessage(message);
            }
        }, phoneNumber, deviceId, verificationCode);
    }
}