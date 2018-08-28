package com.yaratech.yaratube.ui.login.verification;

public interface VerificationContract {

    interface View {

        void saveUser();

        void showErrorMessage(String message);

        void dismissDialog();
    }

    interface Presenter {

        void sendVerificationCode(int verificationCode);
    }
}