package com.yaratech.yaratube.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.source.local.pref.AppPreferences;
import com.yaratech.yaratube.ui.login.stepa.LoginFragment;
import com.yaratech.yaratube.ui.login.stepb.phone.PhoneFragment;
import com.yaratech.yaratube.ui.login.stepc.VerificationFragment;

public class LoginDialogFragment extends DialogFragment
        implements LoginFragment.Interaction, PhoneFragment.Interaction,
        VerificationFragment.Interaction {

    private AppPreferences pref;

    public static LoginDialogFragment newInstance() {
        Bundle bundle = new Bundle();
        LoginDialogFragment fragment = new LoginDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = new AppPreferences(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.login_dialog_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (pref.isVerificationLogin()) {
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.login_dialog_fragment_fl_layout,
                            VerificationFragment.newInstance(), VerificationFragment.class.getName())
                    .commit();
        } else {
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.login_dialog_fragment_fl_layout,
                            LoginFragment.newInstance(), LoginFragment.class.getName())
                    .commit();
        }
    }

    @Override
    public void goToLoginByPhone() {
        pref.setVerificationLogin(false);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.login_dialog_fragment_fl_layout,
                        PhoneFragment.newInstance(), PhoneFragment.class.getName())
                .commit();
    }

    @Override
    public void goToLoginByGoogle() {

    }

    @Override
    public void goToLoginVerification() {
        pref.setVerificationLogin(true);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.login_dialog_fragment_fl_layout,
                        VerificationFragment.newInstance(), VerificationFragment.class.getName())
                .commit();
    }

    @Override
    public void dissmissDialog() {
        pref.setVerificationLogin(false);
        dismiss();
    }
}