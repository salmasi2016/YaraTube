package com.yaratech.yaratube.ui.login.stepa;

import com.yaratech.yaratube.ui.login.stepa.LoginContract;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View iaView;

    public LoginPresenter(LoginContract.View iaView) {
        this.iaView = iaView;
    }

    @Override
    public void dialogPhone() {
        iaView.showDialogPhone();
    }

    @Override
    public void dialogGoogle() {
        iaView.showDialogGoogle();
    }
}