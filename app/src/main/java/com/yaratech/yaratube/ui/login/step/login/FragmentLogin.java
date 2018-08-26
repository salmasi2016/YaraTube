package com.yaratech.yaratube.ui.login.step.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yaratech.yaratube.R;

public class FragmentLogin extends Fragment implements ContractLogin.View, View.OnClickListener {
    private Button btnMobile, btnGoogle;
    private Interaction interaction;
    private ContractLogin.Presenter iaPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            interaction = (Interaction) context;
        } catch (ClassCastException e){
            throw new ClassCastException("Context Is Not Instance Of Interaction");
        }
    }


    public static FragmentLogin newInstance() {
        Bundle args = new Bundle();
        FragmentLogin fragment = new FragmentLogin();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iaPresenter = new PresenterLogin(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnMobile = view.findViewById(R.id.fragment_login_btn_mobile);
        btnGoogle = view.findViewById(R.id.fragment_login_btn_google);
        btnMobile.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_login_btn_mobile:
                iaPresenter.dialogMobile();
                break;
            case R.id.fragment_login_btn_google:
                //iaPresenter.dialogGoogle();
                break;
        }
    }

    @Override
    public void showDialogMobile() {
        interaction.goToLoginByMobile();
    }

    @Override
    public void showDialogGoogle() {
        interaction.goToLoginByGoogle();
    }

    public interface Interaction {

        void goToLoginByMobile();

        void goToLoginByGoogle();
    }
}