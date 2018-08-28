package com.yaratech.yaratube.ui.login.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yaratech.yaratube.R;

public class LoginFragment extends Fragment implements LoginContract.View, View.OnClickListener {
    private Button btnPhone, btnGoogle;
    private Interaction interaction;
    private LoginContract.Presenter iaPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            interaction = (Interaction) getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Context Is Not Instance Of Interaction");
        }
    }


    public static LoginFragment newInstance() {
        Bundle bundle = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iaPresenter = new LoginPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnPhone = view.findViewById(R.id.login_fragment_btn_phone);
        btnGoogle = view.findViewById(R.id.login_fragment_btn_google);
        btnPhone.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_fragment_btn_phone:
                iaPresenter.dialogPhone();
                break;
            case R.id.login_fragment_btn_google:
                //iaPresenter.dialogGoogle();
                break;
        }
    }

    @Override
    public void showDialogPhone() {
        interaction.goToLoginByPhone();
    }

    @Override
    public void showDialogGoogle() {
        interaction.goToLoginByGoogle();
    }

    public interface Interaction {

        void goToLoginByPhone();

        void goToLoginByGoogle();
    }
}