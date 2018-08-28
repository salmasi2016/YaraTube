package com.yaratech.yaratube.ui.login.phone;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.source.local.pref.AppPreferences;

public class PhoneFragment extends Fragment implements PhoneContract.View, View.OnClickListener {
    private Button btnRecord;
    private EditText etPhoneNumber;
    private Interaction interaction;
    private PhoneContract.Presenter iaPresenter;
    private AppPreferences pref;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            interaction = (Interaction) getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Context Is Not Instance Of Interaction");
        }
    }

    public static PhoneFragment newInstance() {
        Bundle bundle = new Bundle();
        PhoneFragment fragment = new PhoneFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iaPresenter = new PhonePresenter(this, getContext());
        pref = new AppPreferences(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.phone_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etPhoneNumber = view.findViewById(R.id.phone_fragment_et_phone_number);
        btnRecord = view.findViewById(R.id.phone_fragment_btn_record);
        btnRecord.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        pref.setPhoneNumber(etPhoneNumber.getText().toString());
        iaPresenter.sendPhoneNumber();
    }

    @Override
    public void showDialogVerification() {
        interaction.goToLoginVerification();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public interface Interaction {

        void goToLoginVerification();
    }
}