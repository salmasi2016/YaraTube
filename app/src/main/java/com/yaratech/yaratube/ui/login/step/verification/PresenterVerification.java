package com.yaratech.yaratube.ui.login.step.verification;

public class PresenterVerification implements ContractVerification.Presenter {
    private ContractVerification.View iaView;

    public PresenterVerification(ContractVerification.View iaView) {
        this.iaView = iaView;
    }

    @Override
    public void loginInApp() {
        iaView.dismissDialog();
        iaView.saveUser();
    }
}
