package com.yaratech.yaratube.ui.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.yaratech.yaratube.R;

public class ProfileFragment extends Fragment {
    private RadioGroup rgSex;
    private EditText etFullName,etDateBirth;

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        etFullName=view.findViewById(R.id.profile_fragment_et_full_name);
        etDateBirth=view.findViewById(R.id.profile_fragment_et_date_birth);
        setRadioGroupValues();
    }

    private void setRadioGroupValues() {
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.profile_fragment_rb_man:

                        break;
                    case R.id.profile_fragment_rb_woman:

                        break;
                }
            }
        });
    }
}