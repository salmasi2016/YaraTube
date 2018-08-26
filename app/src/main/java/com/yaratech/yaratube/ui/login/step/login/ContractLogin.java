package com.yaratech.yaratube.ui.login.step.login;

public interface ContractLogin {

    interface View {

        void showDialogMobile();

        void showDialogGoogle();
    }

    interface Presenter {

        void dialogMobile();

        void dialogGoogle();
    }
}