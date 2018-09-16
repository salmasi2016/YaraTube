package com.yaratech.yaratube.ui.login.stepa.login;

public interface LoginContract {

    interface View {

        void saveUser();

        void showDialogPhone();

        void showErrorMessage(String message);

        void dissmissDialog();
    }

    interface Presenter {

        void dialogPhone();

        void getTokenIdUser(String tokenIdGoogle);
    }
}