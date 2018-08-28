package com.yaratech.yaratube.ui.login.login;

public interface LoginContract {

    interface View {

        void showDialogPhone();

        void showDialogGoogle();
    }

    interface Presenter {

        void dialogPhone();

        void dialogGoogle();
    }
}