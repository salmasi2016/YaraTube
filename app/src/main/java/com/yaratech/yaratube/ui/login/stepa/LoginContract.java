package com.yaratech.yaratube.ui.login.stepa;

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