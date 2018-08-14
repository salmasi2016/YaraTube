package com.yaratech.yaratube.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.yaratech.yaratube.ui.about_us.AboutUsFragment;
import com.yaratech.yaratube.ui.contact_us.ContactUsFragment;
import com.yaratech.yaratube.ui.home.home.HomeFragment;
import com.yaratech.yaratube.ui.profile.ProfileFragment;

import java.util.List;

public class Tool {
    public static final String BASE_URL = "https://api.vasapi.click/";
    public static final String STORE_ID = "16";
    public static final String FRAGMENT_CATEGORY_GRID_CATEGORY = "category";

    public static void setFragment(FragmentManager fragmentManager, Fragment fragment, int idLayout) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(idLayout, fragment);
        if (fragment instanceof ProfileFragment ||
                fragment instanceof AboutUsFragment ||
                fragment instanceof ContactUsFragment) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    public static boolean isDrawerClose(FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment f : fragments) {
            if (f != null && f instanceof HomeFragment) {
                return ((HomeFragment) f).onBackPressed();
            }
        }
        return true;
    }
}