package com.yaratech.yaratube.ui.login.step.mobile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.yaratech.yaratube.R;

public class FragmentMobile extends Fragment implements ContractMobile.View, View.OnClickListener {
    private Button btnRecord;
    private EditText etMobile;
    private Interaction interaction;
    private ContractMobile.Presenter iaPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            interaction = (Interaction) context;
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
        iaPresenter = new PresenterMobile(this);
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
        btnRecord.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        iaPresenter.dialogVerification();
    }

    @Override
    public void showDialogVerification() {
        interaction.goToLoginVerification();
    }

    public interface Interaction {

        void goToLoginVerification();
    }
}