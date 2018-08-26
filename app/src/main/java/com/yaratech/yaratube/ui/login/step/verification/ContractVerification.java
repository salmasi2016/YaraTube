package com.yaratech.yaratube.ui.login.step.verification;

public interface ContractVerification {

    interface View {

        void saveUser();

        void dismissDialog();
    }

    interface Presenter {

        void loginInApp();
    }
}