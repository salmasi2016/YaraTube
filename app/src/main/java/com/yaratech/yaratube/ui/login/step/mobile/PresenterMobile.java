package com.yaratech.yaratube.ui.login.step.mobile;

public class PresenterMobile implements ContractMobile.Presenter {
    private ContractMobile.View iaView;

    public PresenterMobile(ContractMobile.View iaView) {
        this.iaView = iaView;
    }

    @Override
    public void dialogVerification() {
        iaView.showDialogVerification();
    }
}