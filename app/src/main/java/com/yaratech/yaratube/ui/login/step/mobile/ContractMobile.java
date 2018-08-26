package com.yaratech.yaratube.ui.login.step.mobile;

public interface ContractMobile {

    interface View {

        void showDialogVerification(String phoneNumber);

        void showErrorMessage(String message);
    }

    interface Presenter {

        void sendPhoneNumber(String phoneNumber, String deviceId, String deviceModel, String deviceOs);
    }
}