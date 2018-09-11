package com.yaratech.yaratube.ui.login.stepa.login;

public interface LoginContract {

    interface View {

        void showDialogPhone();
    }

    interface Presenter {

        void dialogPhone();
    }
}