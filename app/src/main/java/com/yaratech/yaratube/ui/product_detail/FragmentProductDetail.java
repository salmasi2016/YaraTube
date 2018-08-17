package com.yaratech.yaratube.ui.product_detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.util.Tool;

public class FragmentProductDetail extends Fragment {
    private Product product;
    private ImageView ivVideo;
    private TextView tvTitle,tvDescription;

    public static FragmentProductDetail newInstance(Product product) {
        FragmentProductDetail fragment = new FragmentProductDetail();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Tool.FRAGMENT_PRODUCT_DETAIL_PRODUCT_DETAIL, product);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) return;
        setProduct((Product) bundle.getParcelable(Tool.FRAGMENT_PRODUCT_DETAIL_PRODUCT_DETAIL));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivVideo=view.findViewById(R.id.fragment_product_detail_iv_video);
        tvTitle=view.findViewById(R.id.fragment_product_detail_tv_title);
        tvDescription=view.findViewById(R.id.fragment_product_detail_tv_description);
        Glide.with(view.getContext()).load(Tool.BASE_URL+getProduct().getAvatar().getXxxdpi()).into(ivVideo);
        tvTitle.setText(getProduct().getName());
        tvDescription.setText(getProduct().getShortDescription());
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}