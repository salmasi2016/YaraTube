package com.yaratech.yaratube.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.yaratech.yaratube.ui.home.home.FragmentHome;

import java.util.List;

public class Tool {
    public static final String BASE_URL = "https://api.vasapi.click/";
    public static final String FRAGMENT_CATEGORY_GRID_CATEGORY = "category";
    public static final String FRAGMENT_PRODUCT_DETAIL_PRODUCT_DETAIL = "productDetail";
    public static final String FRAGMENT_HEADER_ITEM_HEADER_ITEM = "headerItem";

    public static void setFragment(final FragmentManager fragmentManager, final Fragment fragment, final int idLayout, String tag) {
        final android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(idLayout, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    public static boolean isDrawerClose(FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment f : fragments) {
            if (f != null && f instanceof FragmentHome) {
                return ((FragmentHome) f).onBackPressed();
            }
        }
        return true;
    }
}