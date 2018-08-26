package com.yaratech.yaratube.ui.login.step.verification;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.source.local.db.database.AppDataBase;
import com.yaratech.yaratube.util.Tool;

public class FragmentVerification extends Fragment implements View.OnClickListener, ContractVerification.View {
    private Button btnRecord, btnNumberCorrection;
    private EditText etCode;
    private TextView tvReceive;
    private Interaction interaction;
    private ContractVerification.Presenter iaPresenter;
    private String phoneNumber;
    private static String PHONE_NUMBER = "phoneNumber";
    String deviceId;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            interaction = (Interaction) getParentFragment();
        } catch (ClassCastException e){
            throw new ClassCastException("Context Is Not Instance Of Interaction");
        }
    }

    public static FragmentVerification newInstance(String phoneNumber) {
        Bundle args = new Bundle();
        FragmentVerification fragment = new FragmentVerification();
        args.putString(PHONE_NUMBER,phoneNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) return;
            phoneNumber = bundle.getString(PHONE_NUMBER);
        final AppDataBase database = AppDataBase.newInstance(getActivity());
        iaPresenter = new PresenterVerification(this,getContext(),database);
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
        deviceId = Settings.Secure.getString(view.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_verification_btn_record:
                iaPresenter.sendVerificationCode(phoneNumber, deviceId, Integer.parseInt(etCode.getText().toString()));
                //Tool.hideKeyboardFrom(getContext(), view);
                break;
            case R.id.fragment_verification_btn_number_correction:
                interaction.goToLoginByMobile();
                break;
            case R.id.fragment_verification_tv_receive:
                Log.i("sina", "receive");
                break;
        }
    }

    @Override
    public void saveUser() {
        Toast.makeText(this.getContext(), "ورود شما با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String message) {
    Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissDialog() {
        interaction.dissmissDialog();
    }

    public interface Interaction {

        void goToLoginByMobile();

        void dissmissDialog();
    }
}