package com.yaratech.yaratube.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.HeaderItem;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.ui.about_us.FragmentAboutUs;
import com.yaratech.yaratube.ui.category_grid.FragmentCategoryGrid;
import com.yaratech.yaratube.ui.contact_us.FragmentContactUs;
import com.yaratech.yaratube.ui.home.category.FragmentCategory;
import com.yaratech.yaratube.ui.home.home.FragmentHome;
import com.yaratech.yaratube.ui.home.main_page.main_page.FragmentMainPage;
import com.yaratech.yaratube.ui.product_detail.FragmentProductDetail;
import com.yaratech.yaratube.ui.profile.FragmentProfile;
import com.yaratech.yaratube.util.Tool;

public class MainActivity extends AppCompatActivity implements FragmentHome.Interaction.goTo,
        FragmentCategory.Interaction, FragmentCategoryGrid.Interaction ,
FragmentMainPage.Interaction{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fl_layout, FragmentHome.newInstance());
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (Tool.isDrawerClose(getSupportFragmentManager())) {
            super.onBackPressed();
        }
    }

    @Override
    public void goToProfile() {
        FragmentProfile fragmentProfile = (FragmentProfile) getSupportFragmentManager().findFragmentByTag("FragmentProfile");
        if (fragmentProfile == null) {
            fragmentProfile = FragmentProfile.newInstance();
            Tool.setFragment(getSupportFragmentManager(), fragmentProfile, R.id.main_fl_layout, "FragmentProfile");
        }
    }

    @Override
    public void goToAboutUs() {
        FragmentAboutUs fragmentAboutUs = (FragmentAboutUs) getSupportFragmentManager().findFragmentByTag("FragmentAboutUs");
        if (fragmentAboutUs == null) {
            fragmentAboutUs = FragmentAboutUs.newInstance();
            Tool.setFragment(getSupportFragmentManager(), fragmentAboutUs, R.id.main_fl_layout, "FragmentAboutUs");
        }
    }

    @Override
    public void goToContactUs() {
        FragmentContactUs fragmentContactUs = (FragmentContactUs) getSupportFragmentManager().findFragmentByTag("FragmentContactUs");
        if (fragmentContactUs == null) {
            fragmentContactUs = FragmentContactUs.newInstance();
            Tool.setFragment(getSupportFragmentManager(), fragmentContactUs, R.id.main_fl_layout, "FragmentContactUs");
        }
    }

    @Override
    public void goToMainPage() {
        FragmentMainPage fragmentMainPage = (FragmentMainPage) getSupportFragmentManager().findFragmentByTag("FragmentMainPage");
        if (fragmentMainPage == null) {
            fragmentMainPage = FragmentMainPage.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.home_fl_layout, fragmentMainPage, "FragmentMainPage").commit();
        } else {
            FragmentCategory fragmentCategory = (FragmentCategory) getSupportFragmentManager().findFragmentByTag("FragmentCategory");
            if (fragmentCategory != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(getSupportFragmentManager().findFragmentByTag("FragmentCategory"))
                        .show(getSupportFragmentManager().findFragmentByTag("FragmentMainPage"))
                        .commit();
            }
        }
    }

    @Override
    public void goToCategories() {
        FragmentCategory fragmentCategory = (FragmentCategory) getSupportFragmentManager().findFragmentByTag("FragmentCategory");
        if (fragmentCategory == null) {
            fragmentCategory = FragmentCategory.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.home_fl_layout, fragmentCategory, "FragmentCategory").commit();
        } else {
            FragmentMainPage fragmentMainPage = (FragmentMainPage) getSupportFragmentManager().findFragmentByTag("FragmentMainPage");
            if (fragmentMainPage != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(getSupportFragmentManager().findFragmentByTag("FragmentMainPage"))
                        .show(getSupportFragmentManager().findFragmentByTag("FragmentCategory"))
                        .commit();
            }
        }
    }

    @Override
    public void goToFragmentCategoryGrid(Category category) {
        FragmentCategoryGrid fragmentCategoryGrid = (FragmentCategoryGrid) getSupportFragmentManager().findFragmentByTag("FragmentCategoryGrid");
        if (fragmentCategoryGrid == null) {
            fragmentCategoryGrid = FragmentCategoryGrid.newInstance(category);
            Tool.setFragment(getSupportFragmentManager(), fragmentCategoryGrid, R.id.main_fl_layout, "FragmentCategoryGrid");
        }
    }

    @Override
    public void goToFragmentProductDetail(Product product) {
        FragmentProductDetail fragmentProductDetail = (FragmentProductDetail) getSupportFragmentManager().findFragmentByTag("FragmentProductDetail");
        if (fragmentProductDetail == null) {
            fragmentProductDetail = FragmentProductDetail.newInstance(product);
            Tool.setFragment(getSupportFragmentManager(), fragmentProductDetail, R.id.main_fl_layout, "FragmentProductDetail");
        }
    }

    @Override
    public void goToFragmentProductDetail(HeaderItem headerItem) {
        FragmentProductDetail fragmentProductDetail = (FragmentProductDetail) getSupportFragmentManager().findFragmentByTag("FragmentProductDetail");
        if (fragmentProductDetail == null) {
            fragmentProductDetail = FragmentProductDetail.newInstance(headerItem);
            Tool.setFragment(getSupportFragmentManager(), fragmentProductDetail, R.id.main_fl_layout, "FragmentProductDetail");
        }
    }
}