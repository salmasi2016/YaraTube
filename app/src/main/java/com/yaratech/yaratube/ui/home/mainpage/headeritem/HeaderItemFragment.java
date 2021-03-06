package com.yaratech.yaratube.ui.home.mainpage.headeritem;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.HeaderItem;
import com.yaratech.yaratube.util.Constant;

public class HeaderItemFragment extends Fragment {
    private ImageView ivHeader;
    private HeaderItem headerItem;
    private Interaction interaction;
    public static final String KEY_HEADER_ITEM = "headerItem";

    public static HeaderItemFragment newInstance(HeaderItem headerItem) {
        HeaderItemFragment fragment = new HeaderItemFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_HEADER_ITEM, headerItem);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interaction = (Interaction) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) return;
        setHeaderItem((HeaderItem) bundle.getParcelable(KEY_HEADER_ITEM));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.header_item_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivHeader = view.findViewById(R.id.header_item_fragment_iv_header);
        Glide.with(this).load(Constant.BASE_URL+getHeaderItem().getFeatureAvatar().getXxxdpi()).into(ivHeader);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interaction.goToProductDetail(getHeaderItem().getId());
            }
        });
    }

    public HeaderItem getHeaderItem() {
        return headerItem;
    }

    public void setHeaderItem(HeaderItem headerItem) {
        this.headerItem = headerItem;
    }

    public interface Interaction {

        void goToProductDetail(int productId);
    }
}