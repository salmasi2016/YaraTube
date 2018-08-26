package com.yaratech.yaratube.ui.login.step.mobile;

public interface ContractMobile {

    interface View {

        void showDialogVerification();
    }

    interface Presenter {

        void dialogVerification();
    }
}