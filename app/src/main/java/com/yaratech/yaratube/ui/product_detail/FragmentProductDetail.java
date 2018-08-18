package com.yaratech.yaratube.ui.product_detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.HeaderItem;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.util.Tool;

import java.util.ArrayList;

public class FragmentProductDetail extends Fragment implements ContractProductDetail.View {
    private Product product;
    private HeaderItem headerItem;
    private ImageView ivVideo;
    private TextView tvName, tvShortDescription;
    private RecyclerView rvComment;
    private ProgressBar pbLoad;
    private ContractProductDetail.Presenter iaPresenter;
    private AdapterProductDetail adapterProductDetail;

    public static FragmentProductDetail newInstance(Product product) {
        FragmentProductDetail fragment = new FragmentProductDetail();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Tool.FRAGMENT_PRODUCT_DETAIL_PRODUCT_DETAIL_PRODUCT, product);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static FragmentProductDetail newInstance(HeaderItem headerItem) {
        FragmentProductDetail fragment = new FragmentProductDetail();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Tool.FRAGMENT_PRODUCT_DETAIL_PRODUCT_DETAIL_HEADERITEM, headerItem);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iaPresenter = new PresenterProductDetail(this);
        adapterProductDetail = new AdapterProductDetail();
        Bundle bundle = getArguments();
        if (bundle == null) return;
        setProduct((Product) bundle.getParcelable(Tool.FRAGMENT_PRODUCT_DETAIL_PRODUCT_DETAIL_PRODUCT));
        setHeaderItem((HeaderItem) bundle.getParcelable(Tool.FRAGMENT_PRODUCT_DETAIL_PRODUCT_DETAIL_HEADERITEM));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivVideo = view.findViewById(R.id.fragment_product_detail_iv_video);
        tvName = view.findViewById(R.id.fragment_product_detail_tv_name);
        tvShortDescription = view.findViewById(R.id.fragment_product_detail_tv_shortDescription);
        rvComment = view.findViewById(R.id.fragment_product_detail_rv_comment);
        pbLoad = view.findViewById(R.id.fragment_product_detail_pb_load);
        Glide.with(view.getContext()).load(Tool.BASE_URL + getProduct().getAvatar().getXxxdpi()).into(ivVideo);
        tvName.setText(getProduct().getName());
        tvShortDescription.setText(getProduct().getShortDescription());
        rvComment = view.findViewById(R.id.fragment_product_detail_rv_comment);
        rvComment.setLayoutManager(new LinearLayoutManager(getContext()));
        rvComment.setItemAnimator(new DefaultItemAnimator());
        rvComment.setAdapter(adapterProductDetail);
        if (getHeaderItem() == null) {
            iaPresenter.loadDataByProduct(getProduct());
        } else {
            iaPresenter.loadDataByHeaderItem(getHeaderItem());
        }
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public HeaderItem getHeaderItem() {
        return headerItem;
    }

    public void setHeaderItem(HeaderItem headerItem) {
        this.headerItem = headerItem;
    }

    @Override
    public void showProgress() {
        pbLoad.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoad.setVisibility(View.GONE);
    }

    @Override
    public void showComments(ArrayList<Comment> comments) {
        adapterProductDetail.setComments(comments);
    }

    @Override
    public void showToast() {
        Toast.makeText(getActivity(), "Data Not Available", Toast.LENGTH_SHORT).show();
    }
}