package com.yaratech.yaratube.ui.login.phone;

import android.content.Context;

import com.yaratech.yaratube.data.model.SmsResponse;
import com.yaratech.yaratube.data.source.remote.ApiResult;
import com.yaratech.yaratube.data.source.remote.UserRepository;

public class PhonePresenter implements PhoneContract.Presenter {
    private PhoneContract.View iaView;
    private UserRepository repository;

    public PhonePresenter(PhoneContract.View iaView, Context context) {
        this.iaView = iaView;
        repository = new UserRepository(context);
    }

    @Override
    public void sendPhoneNumber() {
        repository.sendPhoneNumber(new ApiResult<SmsResponse>() {
            @Override
            public void onSuccess(SmsResponse response) {
                iaView.showDialogVerification();
            }

            @Override
            public void onFail(String errorMessage) {
                iaView.showErrorMessage(errorMessage);
            }
        });
    }
}