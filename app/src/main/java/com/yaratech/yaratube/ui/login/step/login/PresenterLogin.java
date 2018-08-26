package com.yaratech.yaratube.ui.login.step.login;

public class PresenterLogin implements ContractLogin.Presenter {
    private ContractLogin.View iaView;

    public PresenterLogin(ContractLogin.View iaView) {
        this.iaView = iaView;
    }

    @Override
    public void dialogMobile() {
        iaView.showDialogMobile();
    }

    @Override
    public void dialogGoogle() {
        iaView.showDialogGoogle();
    }
}