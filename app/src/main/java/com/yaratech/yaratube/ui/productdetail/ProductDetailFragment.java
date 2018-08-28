package com.yaratech.yaratube.ui.productdetail;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.util.Constant;

import java.util.ArrayList;

public class ProductDetailFragment extends Fragment implements ProductDetailContract.View {
    private Product product;
    private int productId;
    private ImageView ivVideo;
    private TextView tvName, tvDescription;
    private RecyclerView rvComment;
    private ProgressBar pbLoad;
    private ProductDetailContract.Presenter iaPresenter;
    private ProductDetailAdapter productDetailAdapter;
    private boolean isProductLoaded,isCommentLoaded;

    public static ProductDetailFragment newInstance(int productId) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.PRODUCT_DETAIL_FRAGMENT_PRODUCT_ID, productId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iaPresenter = new ProductDetailPresenter(this);
        productDetailAdapter = new ProductDetailAdapter();
        Bundle bundle = getArguments();
        if (bundle == null) return;
        setProductId(bundle.getInt(Constant.PRODUCT_DETAIL_FRAGMENT_PRODUCT_ID));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.product_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivVideo = view.findViewById(R.id.product_detail_fragment_iv_video);
        tvName = view.findViewById(R.id.product_detail_fragment_tv_name);
        tvDescription = view.findViewById(R.id.product_detail_fragment_tv_description);
        rvComment = view.findViewById(R.id.product_detail_fragment_rv_comment);
        pbLoad = view.findViewById(R.id.product_detail_fragment_pb_load);
        rvComment = view.findViewById(R.id.product_detail_fragment_rv_comment);
        rvComment.setLayoutManager(new LinearLayoutManager(getContext()));
        rvComment.setItemAnimator(new DefaultItemAnimator());
        rvComment.setAdapter(productDetailAdapter);
        iaPresenter.loadProduct(getProductId());
        iaPresenter.loadComment(getProductId());
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public void showProgress() {
        pbLoad.setVisibility(View.VISIBLE);
        isProductLoaded=false;
        isCommentLoaded=false;
    }

    @Override
    public void isProductLoaded() {
        isProductLoaded=true;
        if (isCommentLoaded){
            pbLoad.setVisibility(View.GONE);
        }
    }

    @Override
    public void isCommentLoaded() {
        isCommentLoaded=true;
        if (isProductLoaded){
            pbLoad.setVisibility(View.GONE);
        }
    }

    @Override
    public void showComments(ArrayList<Comment> comments) {
        productDetailAdapter.setComments(comments);
    }

    @Override
    public void showProduct(Product product) {
        setProduct(product);
        Glide.with(getContext()).load(Constant.BASE_URL + getProduct().getFeatureAvatar().getXxxdpi()).into(ivVideo);
        tvName.setText(getProduct().getName());
        tvDescription.setText(getProduct().getDescription());
    }

    @Override
    public void showToast() {
        Toast.makeText(getActivity(), "Data Not Available", Toast.LENGTH_SHORT).show();
    }
}