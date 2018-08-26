package com.yaratech.yaratube.ui.login.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.login.step.login.FragmentLogin;
import com.yaratech.yaratube.ui.login.step.mobile.FragmentMobile;
import com.yaratech.yaratube.ui.login.step.verification.FragmentVerification;

public class DialogFragmentLogin extends DialogFragment implements FragmentLogin.Interaction
        , FragmentMobile.Interaction, FragmentVerification.Interaction {

    public static DialogFragmentLogin newInstance() {
        Bundle args = new Bundle();
        DialogFragmentLogin fragment = new DialogFragmentLogin();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getChildFragmentManager().beginTransaction()
        .replace(R.id.dialog_fragment_login_fl_layout, FragmentLogin.newInstance(), "FragmentLogin")
        .commit();
    }

    @Override
    public void goToLoginByMobile() {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.dialog_fragment_login_fl_layout, FragmentMobile.newInstance(), "FragmentMobile")
                .commit();
    }

    @Override
    public void goToLoginByGoogle() {

    }

    @Override
    public void goToLoginVerification(String phoneNumber) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.dialog_fragment_login_fl_layout, FragmentVerification.newInstance(phoneNumber), "FragmentVerification")
                .commit();
    }

    @Override
    public void dissmissDialog() {
        dismiss();
    }
}