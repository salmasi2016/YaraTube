package com.yaratech.yaratube.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.ui.about_us.AboutUsFragment;
import com.yaratech.yaratube.ui.contact_us.ContactUsFragment;
import com.yaratech.yaratube.ui.home.category.FragmentCategory;
import com.yaratech.yaratube.ui.category_grid.FragmentCategoryGrid;
import com.yaratech.yaratube.ui.home.home.HomeFragment;
import com.yaratech.yaratube.ui.home.main_page.main_page.FragmentMainPage;
import com.yaratech.yaratube.ui.profile.ProfileFragment;
import com.yaratech.yaratube.util.Tool;

public class MainActivity extends AppCompatActivity implements HomeFragment.Interaction.goTo
        ,FragmentCategory.Interaction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tool.setFragment(getSupportFragmentManager(), HomeFragment.newInstance(), R.id.main_fl_layout);
    }

    @Override
    public void onBackPressed() {
        if (Tool.isDrawerClose(getSupportFragmentManager())) {
            super.onBackPressed();
        }
    }

    @Override
    public void goToProfile() {
        Tool.setFragment(getSupportFragmentManager(), ProfileFragment.newInstance(), R.id.main_fl_layout);
    }

    @Override
    public void goToAboutUs() {
        Tool.setFragment(getSupportFragmentManager(), AboutUsFragment.newInstance(), R.id.main_fl_layout);
    }

    @Override
    public void goToContactUs() {
        Tool.setFragment(getSupportFragmentManager(), ContactUsFragment.newInstance(), R.id.main_fl_layout);
    }

    @Override
    public void goToMainPage() {
        Tool.setFragment(getSupportFragmentManager(), FragmentMainPage.newInstance(), R.id.home_fl_layout);
    }

    @Override
    public void goToCategories() {
        Tool.setFragment(getSupportFragmentManager(), FragmentCategory.newInstance(), R.id.home_fl_layout);
    }

    @Override
    public void goToFragmentCategoryGrid(Category category) {
        Tool.setFragment(getSupportFragmentManager(), FragmentCategoryGrid.newInstance(category),R.id.main_fl_layout);
    }
}