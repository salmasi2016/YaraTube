package com.yaratech.yaratube.ui.login.step.verification;

public interface ContractVerification {

    interface View {

        void saveUser();

        void showErrorMessage(String message);

        void dismissDialog();
    }

    interface Presenter {

        void sendVerificationCode(String phoneNumber, String deviceId, int verificationCode);
    }
}