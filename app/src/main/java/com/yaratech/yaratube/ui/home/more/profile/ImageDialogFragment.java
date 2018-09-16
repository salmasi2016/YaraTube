package com.yaratech.yaratube.ui.home.more.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yaratech.yaratube.R;

public class ImageDialogFragment extends DialogFragment implements View.OnClickListener {
    private Button btnGalery, btnCamera;
    private static Interaction interaction;
    public static final String IMAGE_DIALOG_TAG = "image_dialog";

    public static ImageDialogFragment newInstance(Interaction listener) {
        interaction=listener;
        ImageDialogFragment fragment = new ImageDialogFragment();
        Bundle bundle = new Bundle();
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

        return inflater.inflate(R.layout.image_dialog_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnGalery = view.findViewById(R.id.image_dialog_fragment_btn_galery);
        btnCamera = view.findViewById(R.id.image_dialog_fragment_btn_camera);
        btnGalery.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_dialog_fragment_btn_galery:
                interaction.goToGalery();
                dismiss();
                break;
            case R.id.image_dialog_fragment_btn_camera:
                interaction.goToCamera();
                dismiss();
                break;
        }
    }

    public interface Interaction {

        void goToCamera();

        void goToGalery();
    }
}