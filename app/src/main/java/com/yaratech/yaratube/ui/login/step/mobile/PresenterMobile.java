package com.yaratech.yaratube.ui.login.step.mobile;

import android.content.Context;

import com.yaratech.yaratube.data.model.SmsResponse;
import com.yaratech.yaratube.data.source.remote.ApiResult;
import com.yaratech.yaratube.data.source.remote.UserRepository;

public class PresenterMobile implements ContractMobile.Presenter {
    private ContractMobile.View iaView;
    private UserRepository repository;

    public PresenterMobile(ContractMobile.View iaView, Context context) {
        this.iaView = iaView;
        repository=new UserRepository(context);
    }

    @Override
    public void sendPhoneNumber(final String phoneNumber, String deviceId, String deviceModel, String deviceOs) {
        repository.sendPhoneNumber(new ApiResult<SmsResponse>() {
            @Override
            public void onSuccess(SmsResponse response) {
                iaView.showDialogVerification(phoneNumber);
            }

            @Override
            public void onFail(String errorMessage) {
                iaView.showErrorMessage(errorMessage);
            }
        }, phoneNumber, deviceId, deviceModel, deviceOs);
    }
}