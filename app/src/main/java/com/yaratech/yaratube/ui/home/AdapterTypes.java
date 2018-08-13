package com.yaratech.yaratube.ui.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.model.HeaderItem;
import com.yaratech.yaratube.model.HomeItem;

import java.util.ArrayList;

class AdapterTypes extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<HeaderItem> headerItems;
    private ArrayList<HomeItem> homeItems;
    private static final int HEADER_VIEW_TYPE = 1;
    private static final int HOME_VIEW_TYPE = 2;

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_VIEW_TYPE;
        } else {
            return HOME_VIEW_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case HEADER_VIEW_TYPE:
                view = inflater.inflate(R.layout.adapter_types_header, parent, false);
                holder = new AdapterTypes.HeaderViewHolder(view);
                break;
            case HOME_VIEW_TYPE:
                view = inflater.inflate(R.layout.adapter_types_home, parent, false);
                holder = new AdapterTypes.HomeViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AdapterTypes.HeaderViewHolder) {
            AdapterTypes.HeaderViewHolder viewHolder = (AdapterTypes.HeaderViewHolder) holder;
            viewHolder.onBindHeaderView();
        } else if (holder instanceof AdapterTypes.HomeViewHolder) {
            AdapterTypes.HomeViewHolder viewHolder = (AdapterTypes.HomeViewHolder) holder;
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
        RecyclerView rvHeader;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            rvHeader = itemView.findViewById(R.id.adapter_types_header_rv_header);
        }

        public void onBindHeaderView() {
            AdapterHeaderItems adapterHeaderItems = new AdapterHeaderItems();
            rvHeader.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            rvHeader.setItemAnimator(new DefaultItemAnimator());
            rvHeader.setAdapter(adapterHeaderItems);
            adapterHeaderItems.setHeaderItems(headerItems);
        }
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvHome;
        TextView tvTitle;

        public HomeViewHolder(View itemView) {
            super(itemView);
            rvHome = itemView.findViewById(R.id.adapter_types_home_rv_home);
            tvTitle=itemView.findViewById(R.id.adapter_types_home_tv_title);
        }

        public void onBindHomeView(HomeItem homeItem) {
            AdapterHomeItems adapterHomeItems = new AdapterHomeItems();
            rvHome.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            rvHome.setItemAnimator(new DefaultItemAnimator());
            rvHome.setAdapter(adapterHomeItems);
            adapterHomeItems.setProducts(homeItem.getProducts());
            tvTitle.setText(homeItem.getTitle());
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