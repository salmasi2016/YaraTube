package com.yaratech.yaratube.ui.home.main_page.header_item;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.yaratech.yaratube.data.model.HeaderItem;

import java.util.ArrayList;

public class AdapterHeaderItem extends FragmentPagerAdapter {
    private ArrayList<HeaderItem> headerItems;

    public AdapterHeaderItem(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentHeaderItem.newInstance(getHeaderItems().get(position));
    }

    @Override
    public int getCount() {
        if (headerItems == null) {
            return 0;
        }
        return headerItems.size();
    }

    public ArrayList<HeaderItem> getHeaderItems() {
        return headerItems;
    }

    public void setHeaderItems(ArrayList<HeaderItem> headerItems) {
        this.headerItems = headerItems;
        notifyDataSetChanged();
    }
}