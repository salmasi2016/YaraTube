package com.yaratech.yaratube.ui.login.verification;

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
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.source.local.db.database.AppDataBase;
import com.yaratech.yaratube.data.source.local.pref.AppPreferences;

public class VerificationFragment extends Fragment implements View.OnClickListener, VerificationContract.View {
    private Button btnRecord, btnNumberCorrection;
    private EditText etCode;
    private TextView tvReceive;
    private Interaction interaction;
    private VerificationContract.Presenter iaPresenter;
    AppPreferences pref;

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
        pref=new AppPreferences(getContext());
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.verification_fragment_btn_record:
                iaPresenter.sendVerificationCode(Integer.parseInt(etCode.getText().toString()));
                break;
            case R.id.verification_fragment_btn_number_correction:
                interaction.goToLoginByPhone();
                break;
            case R.id.verification_fragment_tv_receive:
                Log.i("sina", "receive");
                break;
        }
    }

    @Override
    public void saveUser() {
        Toast.makeText(this.getContext(), R.string.your_login_is_successfully, Toast.LENGTH_SHORT).show();
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

        void goToLoginByPhone();

        void dissmissDialog();
    }
}