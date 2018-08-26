package com.yaratech.yaratube.ui.login.step.verification;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yaratech.yaratube.R;

public class FragmentVerification extends Fragment implements View.OnClickListener, ContractVerification.View {
    private Button btnRecord, btnNumberCorrection;
    private EditText etCode;
    private TextView tvReceive;
    private Interaction interaction;
    private ContractVerification.Presenter iaPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            interaction = (Interaction) context;
        } catch (ClassCastException e){
            throw new ClassCastException("Context Is Not Instance Of Interaction");
        }
    }

    public static FragmentVerification newInstance() {
        Bundle args = new Bundle();
        FragmentVerification fragment = new FragmentVerification();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iaPresenter = new PresenterVerification(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_verification, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnRecord = view.findViewById(R.id.fragment_verification_btn_record);
        btnNumberCorrection = view.findViewById(R.id.fragment_verification_btn_number_correction);
        etCode = view.findViewById(R.id.fragment_verification_et_code);
        tvReceive = view.findViewById(R.id.fragment_verification_tv_receive);
        btnRecord.setOnClickListener(this);
        btnNumberCorrection.setOnClickListener(this);
        tvReceive.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_verification_btn_record:
                iaPresenter.loginInApp();
                break;
            case R.id.fragment_verification_btn_number_correction:
                Log.i("sina", "numberCorrection");
                break;
            case R.id.fragment_verification_tv_receive:
                Log.i("sina", "receive");
                break;
        }
    }

    @Override
    public void saveUser() {

    }

    @Override
    public void dismissDialog() {
        interaction.dissmissDialog();
    }

    public interface Interaction {

        void dissmissDialog();
    }
}