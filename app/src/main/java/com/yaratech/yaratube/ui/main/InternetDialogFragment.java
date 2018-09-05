package com.yaratech.yaratube.ui.main;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yaratech.yaratube.R;

public class InternetDialogFragment extends DialogFragment implements View.OnClickListener {
    private Button btnMobile, btnWiFi;

    public static InternetDialogFragment newInstance() {
        Bundle bundle = new Bundle();
        InternetDialogFragment fragment = new InternetDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.internet_dialog_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnMobile = view.findViewById(R.id.internet_dialog_fragment_btn_mobile);
        btnWiFi = view.findViewById(R.id.internet_dialog_fragment_btn_wifi);
        btnMobile.setOnClickListener(this);
        btnWiFi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.internet_dialog_fragment_btn_mobile:
                startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
                break;
            case R.id.internet_dialog_fragment_btn_wifi:
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                break;
        }
    }
}