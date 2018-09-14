package com.yaratech.yaratube.ui.home.more.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.source.local.db.LocalRepository;
import com.yaratech.yaratube.data.source.local.db.database.AppDataBase;
import com.yaratech.yaratube.data.source.local.db.entity.User;
import com.yaratech.yaratube.ui.login.stepa.login.LoginPresenter;

public class ProfileFragment extends Fragment implements View.OnClickListener, ProfileContract.View {
    private RadioGroup rgSex;
    private EditText etFullName, etDateBirth;
    private Button btnRecord, btnCancel;
    private LocalRepository localRepository;
    private User user;
    private ProfileContract.Presenter iaPresenter;
    private ProgressBar pbProgress;

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AppDataBase database = AppDataBase.newInstance(getActivity());
        localRepository = new LocalRepository(database);
        iaPresenter = new ProfilePresenter(this, getContext());
        user = localRepository.getUser();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rgSex = view.findViewById(R.id.profile_fragment_rg_sex);
        etFullName = view.findViewById(R.id.profile_fragment_et_full_name);
        etDateBirth = view.findViewById(R.id.profile_fragment_et_birth_date);
        btnRecord = view.findViewById(R.id.profile_fragment_btn_record);
        btnCancel = view.findViewById(R.id.profile_fragment_btn_cancel);
        pbProgress=view.findViewById(R.id.profile_fragment_pb_progress);
        btnRecord.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        if (user.getFullName() != null) {
            etFullName.setText(user.getFullName());
            etDateBirth.setText(user.getBirthDate());
            if (user.getSex().equals("woman")) {
                rgSex.check(R.id.profile_fragment_rb_woman);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_fragment_btn_record:
                String name = etFullName.getText().toString();
                String date = etDateBirth.getText().toString();
                if (name.isEmpty() || date.isEmpty()) {
                    Toast.makeText(getContext(), "لطفا اطلاعات را کامل وارد نمایید.", Toast.LENGTH_SHORT).show();
                } else {
                    btnRecord.setEnabled(false);
                    user.setFullName(name);
                    user.setBirthDate(date);
                    if (rgSex.getCheckedRadioButtonId() == R.id.profile_fragment_rb_man) {
                        user.setSex("man");
                    } else {
                        user.setSex("woman");
                    }
                    iaPresenter.postUser(user);
                }
                break;
            case R.id.profile_fragment_btn_cancel:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }

    @Override
    public void showProgress() {
        pbProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbProgress.setVisibility(View.GONE);
    }

    @Override
    public void saveUser() {
        localRepository.updateUser(user);
        Toast.makeText(this.getContext(), "اطلاعات با موفقیت آپدیت شد.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}