package com.yaratech.yaratube.ui.home.mainpage.headeritem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yaratech.yaratube.data.model.HeaderItem;

import java.util.ArrayList;

public class HeaderItemAdapter extends FragmentPagerAdapter {
    private ArrayList<HeaderItem> headerItems;

    public HeaderItemAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return HeaderItemFragment.newInstance(getHeaderItems().get(position));
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