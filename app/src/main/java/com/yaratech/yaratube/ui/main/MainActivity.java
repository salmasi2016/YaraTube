package com.yaratech.yaratube.ui.main;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
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
import com.yaratech.yaratube.ui.home.mainpage.MainPageFragment;
import com.yaratech.yaratube.ui.home.mainpage.headeritem.HeaderItemFragment;
import com.yaratech.yaratube.ui.login.LoginDialogFragment;
import com.yaratech.yaratube.ui.productdetail.ProductDetailFragment;
import com.yaratech.yaratube.ui.profile.ProfileFragment;
import com.yaratech.yaratube.util.Function;

public class MainActivity extends AppCompatActivity
        implements HomeFragment.Interaction, CategoryFragment.Interaction,
        CategoryGridFragment.Interaction, MainPageFragment.Interaction,
        HeaderItemFragment.Interaction, ProductDetailFragment.Interaction,
        NetworkChangeReceiver.Interaction {

    private FragmentManager fragmentManager;
    private DialogFragment dfInternet;
    private Toast toast;
    private boolean internetDialogIsShowing;
    private String fragmentName;
    private Category category;
    private int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_activity_fl_layout, HomeFragment.newInstance());
        fragmentTransaction.commit();
        toast = Toast.makeText(this, R.string.toast_click_again_to_exit, Toast.LENGTH_SHORT);
        new NetworkChangeReceiver(this);
    }

    public void goToInternetDialog() {
        dfInternet = InternetDialogFragment.newInstance();
        dfInternet.show(fragmentManager.beginTransaction(), InternetDialogFragment.class.getName());
        dfInternet.setCancelable(false);
        internetDialogIsShowing = true;
    }

    @Override
    public void onBackPressed() {
        if (Function.isDrawerClose(fragmentManager)) {
            if (fragmentManager.getBackStackEntryCount() == 0) {
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
        DialogFragment dfLogin = LoginDialogFragment.newInstance();
        dfLogin.show(fragmentManager.beginTransaction(), LoginDialogFragment.class.getName());
    }

    @Override
    public void goToProfile() {
        ProfileFragment profileFragment = (ProfileFragment) fragmentManager
                .findFragmentByTag(ProfileFragment.class.getName());
        if (profileFragment == null) {
            profileFragment = ProfileFragment.newInstance();
            Function.setFragment(fragmentManager,
                    profileFragment, R.id.main_activity_fl_layout, ProfileFragment.class.getName());
        }
    }

    @Override
    public void goToAboutUs() {
        AboutUsFragment aboutUsFragment = (AboutUsFragment) fragmentManager
                .findFragmentByTag(AboutUsFragment.class.getName());
        if (aboutUsFragment == null) {
            aboutUsFragment = AboutUsFragment.newInstance();
            Function.setFragment(fragmentManager,
                    aboutUsFragment, R.id.main_activity_fl_layout, AboutUsFragment.class.getName());
        }
    }

    @Override
    public void goToContactUs() {
        ContactUsFragment contactUsFragment = (ContactUsFragment) fragmentManager
                .findFragmentByTag(ContactUsFragment.class.getName());
        if (contactUsFragment == null) {
            contactUsFragment = ContactUsFragment.newInstance();
            Function.setFragment(fragmentManager,
                    contactUsFragment, R.id.main_activity_fl_layout, ContactUsFragment.class.getName());
        }
    }

    @Override
    public void goToMainPage() {
        MainPageFragment mainPageFragment = (MainPageFragment) fragmentManager
                .findFragmentByTag(MainPageFragment.class.getName());
        if (mainPageFragment == null) {
            if (Function.isNetworkAvailable(this)) {
                mainPageFragment = MainPageFragment.newInstance();
                fragmentManager.beginTransaction()
                        .add(R.id.home_fl_layout, mainPageFragment, MainPageFragment.class.getName())
                        .commit();
            } else {
                fragmentName = "MainPage";
                goToInternetDialog();
            }
        } else {
            CategoryFragment categoryFragment = (CategoryFragment) fragmentManager
                    .findFragmentByTag(CategoryFragment.class.getName());
            if (categoryFragment != null) {
                fragmentManager.beginTransaction()
                        .hide(fragmentManager.findFragmentByTag(CategoryFragment.class.getName()))
                        .show(fragmentManager.findFragmentByTag(MainPageFragment.class.getName()))
                        .commit();
            }
        }
    }

    @Override
    public void goToCategories() {
        CategoryFragment categoryFragment = (CategoryFragment) fragmentManager
                .findFragmentByTag(CategoryFragment.class.getName());
        if (categoryFragment == null) {
            if (Function.isNetworkAvailable(this)) {
                categoryFragment = CategoryFragment.newInstance();
                fragmentManager.beginTransaction()
                        .add(R.id.home_fl_layout, categoryFragment, CategoryFragment.class.getName())
                        .commit();
            } else {
                fragmentName = "Categories";
                goToInternetDialog();
            }
        } else {
            MainPageFragment mainPageFragment = (MainPageFragment) fragmentManager
                    .findFragmentByTag(MainPageFragment.class.getName());
            if (mainPageFragment != null) {
                fragmentManager.beginTransaction()
                        .hide(fragmentManager.findFragmentByTag(MainPageFragment.class.getName()))
                        .show(fragmentManager.findFragmentByTag(CategoryFragment.class.getName()))
                        .commit();
            }
        }
    }

    @Override
    public void goToCategoryGrid(Category category) {
        this.category = category;
        CategoryGridFragment categoryGridFragment = (CategoryGridFragment) fragmentManager
                .findFragmentByTag(CategoryGridFragment.class.getName());
        if (categoryGridFragment == null) {
            if (Function.isNetworkAvailable(this)) {
                categoryGridFragment = CategoryGridFragment.newInstance(category);
                Function.setFragment(fragmentManager,
                        categoryGridFragment, R.id.main_activity_fl_layout, CategoryGridFragment.class.getName());
            } else {
                fragmentName = "CategoryGrid";
                goToInternetDialog();
            }
        }
    }

    @Override
    public void goToProductDetail(int productId) {
        this.productId = productId;
        ProductDetailFragment productDetailFragment = (ProductDetailFragment) fragmentManager
                .findFragmentByTag(ProductDetailFragment.class.getName());
        if (productDetailFragment == null) {
            if (Function.isNetworkAvailable(this)) {
                productDetailFragment = ProductDetailFragment.newInstance(productId);
                Function.setFragment(fragmentManager,
                        productDetailFragment, R.id.main_activity_fl_layout, ProductDetailFragment.class.getName());
            } else {
                fragmentName = "ProductDetail";
                goToInternetDialog();
            }
        }
    }

    @Override
    public void startToConnecting() {
        if (internetDialogIsShowing) {
            dfInternet.dismiss();
            internetDialogIsShowing = false;
            switch (fragmentName) {
                case "MainPage":
                    goToMainPage();
                    break;
                case "Categories":
                    goToCategories();
                    break;
                case "CategoryGrid":
                    goToCategoryGrid(category);
                    break;
                case "ProductDetail":
                    goToProductDetail(productId);
                    break;
            }
        }
    }
}