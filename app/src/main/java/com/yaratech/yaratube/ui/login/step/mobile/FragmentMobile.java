package com.yaratech.yaratube.ui.login.step.mobile;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.yaratech.yaratube.R;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class FragmentMobile extends Fragment implements ContractMobile.View {
    private Button btnRecord;
    private EditText etMobile;
    private Interaction interaction;
    private ContractMobile.Presenter iaPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            interaction = (Interaction) getParentFragment();
        } catch (ClassCastException e){
            throw new ClassCastException("Context Is Not Instance Of Interaction");
        }
    }

    public static FragmentMobile newInstance() {
        Bundle args = new Bundle();
        FragmentMobile fragment = new FragmentMobile();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iaPresenter = new PresenterMobile(this,getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mobile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etMobile = view.findViewById(R.id.fragment_mobile_et_mobile);
        btnRecord = view.findViewById(R.id.fragment_mobile_btn_record);
        final String deviceId = Settings.Secure.getString(view.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        final String deviceModel = Build.MODEL;
        final String deviceOs = "Android " + Build.VERSION.SDK_INT;
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iaPresenter.sendPhoneNumber(etMobile.getText().toString(), deviceId, deviceModel, deviceOs);
            }
        });
    }

    @Override
    public void showDialogVerification(String strPhoneNumber) {
        interaction.goToLoginVerification(strPhoneNumber);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public interface Interaction {

        void goToLoginVerification(String strPhoneNumber);
    }
}