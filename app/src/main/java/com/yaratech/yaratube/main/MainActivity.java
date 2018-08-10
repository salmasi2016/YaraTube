package com.yaratech.yaratube.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.aboutUs.AboutUsFragment;
import com.yaratech.yaratube.contactUs.ContactUsFragment;
import com.yaratech.yaratube.home.CategoriesFragment;
import com.yaratech.yaratube.home.HomeFragment;
import com.yaratech.yaratube.home.MainPageFragment;
import com.yaratech.yaratube.profile.ProfileFragment;
import com.yaratech.yaratube.util.Tool;

public class MainActivity extends AppCompatActivity implements HomeFragment.Interaction.goTo {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tool.setFragment(getSupportFragmentManager(), HomeFragment.newInstance(),R.id.main_fl_layout);
    }

    @Override
    public void goToProfile() {
        Tool.setFragment(getSupportFragmentManager(), ProfileFragment.newInstance(),R.id.main_fl_layout);
    }

    @Override
    public void goToAboutUs() {
        Tool.setFragment(getSupportFragmentManager(), AboutUsFragment.newInstance(),R.id.main_fl_layout);
    }

    @Override
    public void goToContactUs() {
        Tool.setFragment(getSupportFragmentManager(), ContactUsFragment.newInstance(),R.id.main_fl_layout);
    }

    @Override
    public void goToMainPage() {
        Tool.setFragment(getSupportFragmentManager(), MainPageFragment.newInstance(),R.id.home_fl_layout);
    }

    @Override
    public void goToCategories() {
        Tool.setFragment(getSupportFragmentManager(), CategoriesFragment.newInstance(),R.id.home_fl_layout);
    }
}