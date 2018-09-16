package com.yaratech.yaratube.ui.home.more.profile;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.soundcloud.android.crop.Crop;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.source.local.db.LocalRepository;
import com.yaratech.yaratube.data.source.local.db.database.AppDataBase;
import com.yaratech.yaratube.data.source.local.db.entity.User;
import com.yaratech.yaratube.util.Permission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

import static android.app.Activity.RESULT_OK;
import static android.media.MediaRecorder.VideoSource.CAMERA;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static com.yaratech.yaratube.ui.home.more.profile.ImageDialogFragment.IMAGE_DIALOG_TAG;

public class ProfileFragment extends Fragment
        implements View.OnClickListener, ProfileContract.View
        , ImageDialogFragment.Interaction {

    private RadioGroup rgSex;
    private EditText etFullName;
    private TextView tvBirthDate;
    private Button btnRecord, btnCancel;
    private ImageView ivFullName, ivBirthDate;
    private CircleImageView civProfile;
    private LocalRepository localRepository;
    private User user;
    private ProfileContract.Presenter iaPresenter;
    private ProgressBar pbProgress;
    private Uri uri;
    private File file;

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
        civProfile = view.findViewById(R.id.profile_fragment_civ_profile);
        rgSex = view.findViewById(R.id.profile_fragment_rg_sex);
        etFullName = view.findViewById(R.id.profile_fragment_et_full_name);
        tvBirthDate = view.findViewById(R.id.profile_fragment_tv_birth_date);
        btnRecord = view.findViewById(R.id.profile_fragment_btn_record);
        btnCancel = view.findViewById(R.id.profile_fragment_btn_cancel);
        ivFullName = view.findViewById(R.id.profile_fragment_iv_full_name);
        ivBirthDate = view.findViewById(R.id.profile_fragment_iv_birth_date);
        pbProgress = view.findViewById(R.id.profile_fragment_pb_progress);
        btnRecord.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        ivFullName.setOnClickListener(this);
        ivBirthDate.setOnClickListener(this);
        civProfile.setOnClickListener(this);
        if (user.getFullName() != null) {
            Glide.with(getContext()).load(user.getPhotoUrl()).into(civProfile);
            etFullName.setText(user.getFullName());
            tvBirthDate.setText(user.getBirthDate());
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
                String date = tvBirthDate.getText().toString();
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
            case R.id.profile_fragment_iv_full_name:
                etFullName.setEnabled(true);
                break;
            case R.id.profile_fragment_iv_birth_date:
                PersianDatePickerDialog picker = new PersianDatePickerDialog(getContext())
                        .setPositiveButtonString("باشه")
                        .setNegativeButton("بیخیال")
                        .setTodayButton("امروز")
                        .setTodayButtonVisible(true)
                        .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                        .setMinYear(1300)
                        .setActionTextColor(Color.GRAY)
                        .setListener(new Listener() {
                            @Override
                            public void onDateSelected(PersianCalendar persianCalendar) {
                                String date = persianCalendar.getPersianYear() + "-";
                                if (persianCalendar.getPersianMonth() < 10) {
                                    date += "0";
                                }
                                date += persianCalendar.getPersianMonth() + "-";
                                if (persianCalendar.getPersianDay() < 10) {
                                    date += "0";
                                }
                                date += persianCalendar.getPersianDay();
                                tvBirthDate.setText(date);
                            }

                            @Override
                            public void onDismissed() {

                            }
                        });

                picker.show();
                break;
            case R.id.profile_fragment_civ_profile:
                ImageDialogFragment imageDialogFragment = ImageDialogFragment.newInstance(this);
                imageDialogFragment.show(getFragmentManager(), IMAGE_DIALOG_TAG);
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

    @Override
    public void goToCamera() {
        if (!Permission.checkCameraPermissions(getContext())) {
            requestCameraPermission(MEDIA_TYPE_IMAGE);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            this.startActivityForResult(intent, CAMERA);
        }
    }

    @Override
    public void goToGalery() {
        Crop.pickImage(getContext(), this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == CAMERA) {

                beginCrop(data.getData());

            } else if (requestCode == Crop.REQUEST_PICK) {

                beginCrop(data.getData());

            } else if (requestCode == Crop.REQUEST_CROP) {
                uri = Crop.getOutput(data);
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                file = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                FileOutputStream fileOutputStream = null;

                try {
                    file.createNewFile();
                    fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(byteArrayOutputStream.toByteArray());
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Glide.with(getContext()).load(file).into(civProfile);
                user.setPhotoUrl(file.getPath());
            }
        }
    }

    private void beginCrop(Uri source) {
        Uri uri = Uri.fromFile(new File(getContext().getCacheDir(), "cropped"));
        Crop.of(source, uri).asSquare().start(getContext(), this);
    }

    private void requestCameraPermission(final int type) {
        Dexter.withActivity(getActivity())
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {

                            if (type == MEDIA_TYPE_IMAGE) {
                                // capture picture
                                goToCamera();
                            }

                        } else if (report.isAnyPermissionPermanentlyDenied()) {
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }
}