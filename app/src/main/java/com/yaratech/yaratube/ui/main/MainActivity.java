package com.yaratech.yaratube.ui.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.ui.categorygrid.CategoryGridFragment;
import com.yaratech.yaratube.ui.home.HomeFragment;
import com.yaratech.yaratube.ui.home.category.CategoryFragment;
import com.yaratech.yaratube.ui.home.mainpage.MainPageFragment;
import com.yaratech.yaratube.ui.home.mainpage.headeritem.HeaderItemFragment;
import com.yaratech.yaratube.ui.home.more.MoreFragment;
import com.yaratech.yaratube.ui.home.more.aboutus.AboutUsFragment;
import com.yaratech.yaratube.ui.home.more.contactus.ContactUsFragment;
import com.yaratech.yaratube.ui.home.more.profile.ProfileFragment;
import com.yaratech.yaratube.ui.login.LoginDialogFragment;
import com.yaratech.yaratube.ui.productdetail.ProductDetailFragment;
import com.yaratech.yaratube.util.Function;

public class MainActivity extends AppCompatActivity
        implements HomeFragment.Interaction, CategoryFragment.Interaction,
        CategoryGridFragment.Interaction, MainPageFragment.Interaction,
        HeaderItemFragment.Interaction, ProductDetailFragment.Interaction,
        MoreFragment.Interaction {

    private FragmentManager fragmentManager;
    private ProfileFragment profileFragment;
    private AboutUsFragment aboutUsFragment;
    private ContactUsFragment contactUsFragment;
    private MainPageFragment mainPageFragment;
    private CategoryFragment categoryFragment;
    private MoreFragment moreFragment;
    private CategoryGridFragment categoryGridFragment;
    private ProductDetailFragment productDetailFragment;
    private InternetDialogFragment dialogFragmentNetwork;
    private Toast toast;
    private boolean networkDialogIsShowing;
    private String fragmentName;
    private Category category;
    private int productId;
    private InternalNetworkChangeReceiver internalNetworkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        internalNetworkChangeReceiver = new InternalNetworkChangeReceiver();

        fragmentManager = getSupportFragmentManager();

        profileFragment = (ProfileFragment) fragmentManager
                .findFragmentByTag(ProfileFragment.class.getName());
        aboutUsFragment = (AboutUsFragment) fragmentManager
                .findFragmentByTag(AboutUsFragment.class.getName());
        contactUsFragment = (ContactUsFragment) fragmentManager
                .findFragmentByTag(ContactUsFragment.class.getName());
        mainPageFragment = (MainPageFragment) fragmentManager
                .findFragmentByTag(MainPageFragment.class.getName());
        categoryFragment = (CategoryFragment) fragmentManager
                .findFragmentByTag(CategoryFragment.class.getName());
        moreFragment = (MoreFragment) fragmentManager
                .findFragmentByTag(MoreFragment.class.getName());
        categoryGridFragment = (CategoryGridFragment) fragmentManager
                .findFragmentByTag(CategoryGridFragment.class.getName());
        productDetailFragment = (ProductDetailFragment) fragmentManager
                .findFragmentByTag(ProductDetailFragment.class.getName());

        fragmentManager.beginTransaction()
                .replace(R.id.main_activity_fl_layout, HomeFragment.newInstance())
                .commit();
        toast = Toast.makeText(this, R.string.toast_click_again_to_exit, Toast.LENGTH_SHORT);

        registerReceiver();
    }

    private void registerReceiver() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(NetworkChangeReceiver.NETWORK_CHANGE_ACTION);
            registerReceiver(internalNetworkChangeReceiver, intentFilter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void goToInternetDialog() {
        dialogFragmentNetwork = InternetDialogFragment.newInstance();
        dialogFragmentNetwork.show(fragmentManager.beginTransaction(), InternetDialogFragment.class.getName());
        dialogFragmentNetwork.setCancelable(false);
        networkDialogIsShowing = true;
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
        profileFragment = ProfileFragment.newInstance();
        fragmentManager.beginTransaction()
                .add(R.id.main_activity_fl_layout, profileFragment, ProfileFragment.class.getName())
                .hide(moreFragment)
                .addToBackStack(ProfileFragment.class.getName())
                .commit();
    }

    @Override
    public void goToAboutUs() {
        aboutUsFragment = AboutUsFragment.newInstance();
        Function.setFragment(fragmentManager,
                aboutUsFragment, R.id.main_activity_fl_layout, AboutUsFragment.class.getName());
    }

    @Override
    public void goToContactUs() {
        contactUsFragment = ContactUsFragment.newInstance();
        Function.setFragment(fragmentManager,
                contactUsFragment, R.id.main_activity_fl_layout, ContactUsFragment.class.getName());
    }

    @Override
    public void goToMainPage() {
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
            if (categoryFragment != null && moreFragment == null) {
                fragmentManager.beginTransaction()
                        .hide(categoryFragment).show(mainPageFragment).commit();
            } else if (categoryFragment == null && moreFragment != null) {
                fragmentManager.beginTransaction()
                        .hide(moreFragment).show(mainPageFragment).commit();
            } else if (categoryFragment != null && moreFragment != null) {
                fragmentManager.beginTransaction()
                        .hide(categoryFragment).hide(moreFragment).show(mainPageFragment).commit();
            }
        }
    }

    @Override
    public void goToCategories() {
        if (categoryFragment == null) {
            if (Function.isNetworkAvailable(this)) {
                categoryFragment = CategoryFragment.newInstance();
                if (moreFragment == null) {
                    fragmentManager.beginTransaction()
                            .add(R.id.home_fl_layout, categoryFragment, CategoryFragment.class.getName())
                            .hide(mainPageFragment)
                            .commit();
                } else {
                    fragmentManager.beginTransaction()
                            .add(R.id.home_fl_layout, categoryFragment, CategoryFragment.class.getName())
                            .hide(mainPageFragment)
                            .hide(moreFragment)
                            .commit();
                }
            } else {
                fragmentName = "Categories";
                goToInternetDialog();
            }
        } else {
            if (mainPageFragment != null && moreFragment == null) {
                fragmentManager.beginTransaction()
                        .hide(mainPageFragment).show(categoryFragment).commit();
            } else if (mainPageFragment == null && moreFragment != null) {
                fragmentManager.beginTransaction()
                        .hide(moreFragment).show(categoryFragment).commit();
            } else if (mainPageFragment != null && moreFragment != null) {
                fragmentManager.beginTransaction()
                        .hide(mainPageFragment).hide(moreFragment).show(categoryFragment).commit();
            }
        }
    }

    @Override
    public void goToMore() {
        if (moreFragment == null) {
            moreFragment = MoreFragment.newInstance();
            if (categoryFragment == null) {
                fragmentManager.beginTransaction()
                        .add(R.id.home_fl_layout, moreFragment, MoreFragment.class.getName())
                        .hide(mainPageFragment)
                        .commit();
            } else {
                fragmentManager.beginTransaction()
                        .add(R.id.home_fl_layout, moreFragment, MoreFragment.class.getName())
                        .hide(mainPageFragment)
                        .hide(categoryFragment)
                        .commit();
            }
        } else {
            if (mainPageFragment != null && categoryFragment == null) {
                fragmentManager.beginTransaction()
                        .hide(mainPageFragment).show(moreFragment).commit();
            } else if (mainPageFragment == null && categoryFragment != null) {
                fragmentManager.beginTransaction()
                        .hide(categoryFragment).show(moreFragment).commit();
            } else if (mainPageFragment != null && categoryFragment != null) {
                fragmentManager.beginTransaction()
                        .hide(mainPageFragment).hide(categoryFragment).show(moreFragment).commit();
            }
        }
    }

    @Override
    public void goToCategoryGrid(Category category) {
        this.category = category;
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
    protected void onDestroy() {
        try {
            unregisterReceiver(internalNetworkChangeReceiver);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        super.onDestroy();
    }

    class InternalNetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getBooleanExtra("status", false) && networkDialogIsShowing) {
                Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(InternetDialogFragment.class.getName());
                if (fragmentByTag != null) {
                    dialogFragmentNetwork.dismiss();
                    networkDialogIsShowing = false;
                    navigateAmongFragments();
                }
            }
        }
    }

    private void navigateAmongFragments() {
        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(InternetDialogFragment.class.getName());
        if (fragmentByTag != null) {
            dialogFragmentNetwork.dismiss();
            networkDialogIsShowing = false;
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