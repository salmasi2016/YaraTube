package com.yaratech.yaratube.ui.home.more;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yaratech.yaratube.R;

public class MoreFragment extends Fragment implements View.OnClickListener {
    private Button btnProfile, btnAboutUs, btnContactUs;
    private Interaction interaction;

    public static MoreFragment newInstance() {
        MoreFragment fragment = new MoreFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            interaction = (Interaction) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Context Is Not Instance Of Interaction");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.more_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnProfile = view.findViewById(R.id.more_fragment_btn_profile);
        btnAboutUs = view.findViewById(R.id.more_fragment_btn_about_us);
        btnContactUs = view.findViewById(R.id.more_fragment_btn_contact_us);
        btnProfile.setOnClickListener(this);
        btnAboutUs.setOnClickListener(this);
        btnContactUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more_fragment_btn_profile:
                interaction.goToProfile();
                break;
            case R.id.more_fragment_btn_about_us:
                interaction.goToAboutUs();
                break;
            case R.id.more_fragment_btn_contact_us:
                interaction.goToContactUs();
                break;
        }
    }

    public interface Interaction {

        void goToProfile();

        void goToAboutUs();

        void goToContactUs();
    }
}