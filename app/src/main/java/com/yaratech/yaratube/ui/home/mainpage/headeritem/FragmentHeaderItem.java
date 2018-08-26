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
import com.yaratech.yaratube.util.Tool;

public class FragmentHeaderItem extends Fragment {
    private ImageView ivHeader;
    private HeaderItem headerItem;
    private Interaction interaction;

    public static FragmentHeaderItem newInstance(HeaderItem headerItem) {
        FragmentHeaderItem fragment = new FragmentHeaderItem();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Tool.FRAGMENT_HEADER_ITEM_HEADER_ITEM, headerItem);
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
        setHeaderItem((HeaderItem) bundle.getParcelable(Tool.FRAGMENT_HEADER_ITEM_HEADER_ITEM));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_header_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivHeader = view.findViewById(R.id.fragment_header_item_iv_header);
        Glide.with(this).load(Tool.BASE_URL+getHeaderItem().getFeatureAvatar().getXxxdpi()).into(ivHeader);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interaction.goToFragmentProductDetail(getHeaderItem().getId());
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

        void goToFragmentProductDetail(int productId);
    }
}