package com.yaratech.yaratube.ui.home.main_page.header_item;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.HeaderItem;
import com.yaratech.yaratube.util.Tool;

import java.util.ArrayList;

public class AdapterHeaderItem extends RecyclerView.Adapter<AdapterHeaderItem.viewHolder> {
    private ArrayList<HeaderItem> headerItems;

    @Override
    public AdapterHeaderItem.viewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_header_item, parent, false);
        return new AdapterHeaderItem.viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHeaderItem.viewHolder holder, int position) {
        HeaderItem headerItem = headerItems.get(position);
        Glide.with(holder.itemView.getContext()).load(Tool.BASE_URL+headerItem.getAvatar().getXxxdpi()).into(holder.ivVideo);
    }

    @Override
    public int getItemCount() {
        if (headerItems == null) {
            return 0;
        }
        return headerItems.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView ivVideo;

        viewHolder(View itemView) {
            super(itemView);
            ivVideo=itemView.findViewById(R.id.list_item_header_item_iv_video);
        }
    }

    public ArrayList<HeaderItem> getHeaderItems() {
        return headerItems;
    }

    public void setHeaderItems(ArrayList<HeaderItem> headerItems) {
        this.headerItems = headerItems;
        notifyDataSetChanged();
    }
}