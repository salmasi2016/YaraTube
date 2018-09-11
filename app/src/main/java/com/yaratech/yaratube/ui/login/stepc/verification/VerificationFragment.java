package com.yaratech.yaratube.ui.login.stepc.verification;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
import com.yaratech.yaratube.util.Permission;

public class VerificationFragment extends Fragment
        implements View.OnClickListener, VerificationContract.View {

    private Button btnRecord, btnNumberCorrection;
    private EditText etCode;
    private TextView tvReceive;
    private Interaction interaction;
    private VerificationContract.Presenter iaPresenter;
    private SmsReceiver smsReceiver;
    private SmsReceiver.Interaction smsListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            interaction = (Interaction) getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Context Is Not Instance Of Interaction");
        }
    }

    public static VerificationFragment newInstance() {
        Bundle bundle = new Bundle();
        VerificationFragment fragment = new VerificationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) return;
        final AppDataBase database = AppDataBase.newInstance(getActivity());
        iaPresenter = new VerificationPresenter(this, getContext(), database);
        smsListener = getSmsListener();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.verification_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnRecord = view.findViewById(R.id.verification_fragment_btn_record);
        btnNumberCorrection = view.findViewById(R.id.verification_fragment_btn_number_correction);
        etCode = view.findViewById(R.id.verification_fragment_et_code);
        tvReceive = view.findViewById(R.id.verification_fragment_tv_receive);
        btnRecord.setOnClickListener(this);
        btnNumberCorrection.setOnClickListener(this);
        tvReceive.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!Permission.checkSMSPermissions(getContext())) {
            requestReadAndSendSmsPermission();
        } else {
            getMessageFromBroadcast();
        }
    }

    private void getMessageFromBroadcast() {
        smsReceiver = new SmsReceiver();
        getContext().registerReceiver(smsReceiver,
                new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
        smsReceiver.bindListener(smsListener);
    }

    private void requestReadAndSendSmsPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_SMS)) {
        }
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_SMS}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    smsReceiver.bindListener(smsListener);
                    getMessageFromBroadcast();
                } else {
                    Log.i("permission", " denied");
                }
                return;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.verification_fragment_btn_record:
                String code = etCode.getText().toString();
                if (code.length() == 5) {
                    btnRecord.setClickable(false);
                    btnNumberCorrection.setClickable(false);
                    iaPresenter.sendVerificationCode(Integer.parseInt(code));
                } else {
                    etCode.setText(null);
                }
                break;
            case R.id.verification_fragment_btn_number_correction:
                interaction.goToLoginByPhone();
                break;
            case R.id.verification_fragment_tv_receive:

                break;
        }
    }

    public SmsReceiver.Interaction getSmsListener() {
        return new SmsReceiver.Interaction() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onReceive(String message) {
                etCode.setText(message);
            }
        };
    }

    @Override
    public void saveUser() {
        Toast.makeText(this.getContext(), R.string.your_login_did_successfully, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
        btnRecord.setClickable(true);
        btnNumberCorrection.setClickable(true);
    }

    @Override
    public void dismissDialog() {
        interaction.dissmissDialog();
    }

    public interface Interaction {

        void goToLoginByPhone();

        void dissmissDialog();
    }
}