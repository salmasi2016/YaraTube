package com.yaratech.yaratube.ui.main;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.ui.aboutus.AboutUsFragment;
import com.yaratech.yaratube.ui.categorygrid.CategoryGridFragment;
import com.yaratech.yaratube.ui.contactus.ContactUsFragment;
import com.yaratech.yaratube.ui.home.HomeFragment;
import com.yaratech.yaratube.ui.home.category.CategoryFragment;
import com.yaratech.yaratube.ui.home.mainpage.headeritem.HeaderItemFragment;
import com.yaratech.yaratube.ui.home.mainpage.mainpage.MainPageFragment;
import com.yaratech.yaratube.ui.login.LoginDialogFragment;
import com.yaratech.yaratube.ui.productdetail.ProductDetailFragment;
import com.yaratech.yaratube.ui.profile.ProfileFragment;
import com.yaratech.yaratube.util.Function;

public class MainActivity extends AppCompatActivity
        implements HomeFragment.Interaction,
        CategoryFragment.Interaction, CategoryGridFragment.Interaction,
        MainPageFragment.Interaction, HeaderItemFragment.Interaction {

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_activity_fl_layout, HomeFragment.newInstance());
        fragmentTransaction.commit();
        toast = Toast.makeText(this, R.string.toast_click_again_to_exit, Toast.LENGTH_SHORT);
    }

    @Override
    public void onBackPressed() {
        if (Function.isDrawerClose(getSupportFragmentManager())) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                if (toast.getView().isShown()) {
                    toast.cancel();
                    super.onBackPressed();
                    return;
                } else {
                    toast.show();
                }
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public void goToLogin() {
        DialogFragment dialogFragment = LoginDialogFragment.newInstance();
        dialogFragment.show(getSupportFragmentManager().beginTransaction(), LoginDialogFragment.class.getName());
    }

    @Override
    public void goToProfile() {
        ProfileFragment profileFragment = (ProfileFragment) getSupportFragmentManager()
                .findFragmentByTag(ProfileFragment.class.getName());
        if (profileFragment == null) {
            profileFragment = ProfileFragment.newInstance();
            Function.setFragment(getSupportFragmentManager(),
                    profileFragment, R.id.main_activity_fl_layout, ProfileFragment.class.getName());
        }
    }

    @Override
    public void goToAboutUs() {
        AboutUsFragment aboutUsFragment = (AboutUsFragment) getSupportFragmentManager()
                .findFragmentByTag(AboutUsFragment.class.getName());
        if (aboutUsFragment == null) {
            aboutUsFragment = AboutUsFragment.newInstance();
            Function.setFragment(getSupportFragmentManager(),
                    aboutUsFragment, R.id.main_activity_fl_layout, AboutUsFragment.class.getName());
        }
    }

    @Override
    public void goToContactUs() {
        ContactUsFragment contactUsFragment = (ContactUsFragment) getSupportFragmentManager()
                .findFragmentByTag(ContactUsFragment.class.getName());
        if (contactUsFragment == null) {
            contactUsFragment = ContactUsFragment.newInstance();
            Function.setFragment(getSupportFragmentManager(),
                    contactUsFragment, R.id.main_activity_fl_layout, ContactUsFragment.class.getName());
        }
    }

    @Override
    public void goToMainPage() {
        MainPageFragment mainPageFragment = (MainPageFragment) getSupportFragmentManager()
                .findFragmentByTag(MainPageFragment.class.getName());
        if (mainPageFragment == null) {
            mainPageFragment = MainPageFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.home_fl_layout, mainPageFragment, MainPageFragment.class.getName())
                    .commit();
        } else {
            CategoryFragment categoryFragment = (CategoryFragment) getSupportFragmentManager()
                    .findFragmentByTag(CategoryFragment.class.getName());
            if (categoryFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .hide(getSupportFragmentManager().findFragmentByTag(CategoryFragment.class.getName()))
                        .show(getSupportFragmentManager().findFragmentByTag(MainPageFragment.class.getName()))
                        .commit();
            }
        }
    }

    @Override
    public void goToCategories() {
        CategoryFragment categoryFragment = (CategoryFragment) getSupportFragmentManager()
                .findFragmentByTag(CategoryFragment.class.getName());
        if (categoryFragment == null) {
            categoryFragment = CategoryFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.home_fl_layout, categoryFragment, CategoryFragment.class.getName())
                    .commit();
        } else {
            MainPageFragment mainPageFragment = (MainPageFragment) getSupportFragmentManager()
                    .findFragmentByTag(MainPageFragment.class.getName());
            if (mainPageFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .hide(getSupportFragmentManager().findFragmentByTag(MainPageFragment.class.getName()))
                        .show(getSupportFragmentManager().findFragmentByTag(CategoryFragment.class.getName()))
                        .commit();
            }
        }
    }

    @Override
    public void goToFragmentCategoryGrid(Category category) {
        CategoryGridFragment categoryGridFragment = (CategoryGridFragment) getSupportFragmentManager()
                .findFragmentByTag(CategoryGridFragment.class.getName());
        if (categoryGridFragment == null) {
            categoryGridFragment = CategoryGridFragment.newInstance(category);
            Function.setFragment(getSupportFragmentManager(),
                    categoryGridFragment, R.id.main_activity_fl_layout, CategoryGridFragment.class.getName());
        }
    }

    @Override
    public void goToFragmentProductDetail(int productId) {
        ProductDetailFragment productDetailFragment = (ProductDetailFragment) getSupportFragmentManager()
                .findFragmentByTag(ProductDetailFragment.class.getName());
        if (productDetailFragment == null) {
            productDetailFragment = ProductDetailFragment.newInstance(productId);
            Function.setFragment(getSupportFragmentManager(),
                    productDetailFragment, R.id.main_activity_fl_layout, ProductDetailFragment.class.getName());
        }
    }
}