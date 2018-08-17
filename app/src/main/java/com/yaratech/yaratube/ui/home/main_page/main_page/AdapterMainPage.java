package com.yaratech.yaratube.ui.home.main_page.main_page;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.HeaderItem;
import com.yaratech.yaratube.data.model.HomeItem;
import com.yaratech.yaratube.ui.home.main_page.header_item.AdapterHeaderItem;
import com.yaratech.yaratube.ui.home.main_page.home_item.AdapterHomeItem;

import java.util.ArrayList;

class AdapterMainPage extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<HeaderItem> headerItems;
    private ArrayList<HomeItem> homeItems;
    private static final int VIEW_TYPE_HEADER = 1;
    private static final int VIEW_TYPE_HOME = 2;
    private FragmentManager fragmentManager;
    private FragmentMainPage fragmentMainPage;

    public AdapterMainPage(FragmentManager fragmentManager, FragmentMainPage fragmentMainPage) {
        this.fragmentManager=fragmentManager;
        this.fragmentMainPage=fragmentMainPage;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_HEADER;
        } else {
            return VIEW_TYPE_HOME;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                view = inflater.inflate(R.layout.adapter_main_page_header, parent, false);
                holder = new AdapterMainPage.HeaderViewHolder(view);
                break;
            case VIEW_TYPE_HOME:
                view = inflater.inflate(R.layout.adapter_main_page_home, parent, false);
                holder = new AdapterMainPage.HomeViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AdapterMainPage.HeaderViewHolder) {
            AdapterMainPage.HeaderViewHolder viewHolder = (AdapterMainPage.HeaderViewHolder) holder;
            viewHolder.onBindHeaderView(fragmentManager);
        } else if (holder instanceof AdapterMainPage.HomeViewHolder) {
            AdapterMainPage.HomeViewHolder viewHolder = (AdapterMainPage.HomeViewHolder) holder;
            viewHolder.onBindHomeView(homeItems.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        if (homeItems == null) {
            return 0;
        }
        return homeItems.size() + 1;
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        ViewPager vpHeader;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            vpHeader = itemView.findViewById(R.id.adapter_main_page_header_vp_header);
        }

        public void onBindHeaderView(FragmentManager fragmentManager) {
            AdapterHeaderItem adapterHeaderItem=new AdapterHeaderItem(fragmentManager);
            adapterHeaderItem.setHeaderItems(headerItems);
            vpHeader.setAdapter(adapterHeaderItem);
        }
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvHome;
        TextView tvTitle;

        public HomeViewHolder(View itemView) {
            super(itemView);
            rvHome = itemView.findViewById(R.id.adapter_main_page_home_rv_home);
            tvTitle = itemView.findViewById(R.id.adapter_main_page_home_tv_title);
        }

        public void onBindHomeView(HomeItem homeItem) {
            AdapterHomeItem adapterHomeItem = new AdapterHomeItem(fragmentMainPage);
            tvTitle.setText(homeItem.getTitle());
            rvHome.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            rvHome.setItemAnimator(new DefaultItemAnimator());
            rvHome.setAdapter(adapterHomeItem);
            adapterHomeItem.setProducts(homeItem.getProducts());
        }
    }

    public ArrayList<HeaderItem> getHeaderItems() {
        return headerItems;
    }

    public void setHeaderItems(ArrayList<HeaderItem> headerItems) {
        this.headerItems = headerItems;
        notifyDataSetChanged();
    }

    public ArrayList<HomeItem> getHomeItems() {
        return homeItems;
    }

    public void setHomeItems(ArrayList<HomeItem> homeItems) {
        this.homeItems = homeItems;
        notifyDataSetChanged();
    }
}