package com.yaratech.yaratube.ui.login.phone;

public interface PhoneContract {

    interface View {

        void showDialogVerification();

        void showErrorMessage(String message);
    }

    interface Presenter {

        void sendPhoneNumber();
    }
}