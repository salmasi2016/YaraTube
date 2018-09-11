package com.yaratech.yaratube.ui.productdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.source.local.db.database.AppDataBase;
import com.yaratech.yaratube.ui.player.PlayerActivity;
import com.yaratech.yaratube.ui.productdetail.comment.CommentDialogFragment;
import com.yaratech.yaratube.util.Constant;

import java.util.ArrayList;

public class ProductDetailFragment extends Fragment
        implements ProductDetailContract.View, View.OnClickListener {

    private Product product;
    private int productId;
    private ImageView ivVideo;
    private TextView tvName, tvDescription;
    private RecyclerView rvComment;
    private Button btnComment;
    private ImageButton ibPlay;
    private ProgressBar pbProgress;
    private ProductDetailContract.Presenter iaPresenter;
    private ProductDetailAdapter productDetailAdapter;
    private boolean isProductLoaded, isCommentLoaded;
    private static final String KEY_PRODUCT_ID = "productId";
    private Interaction interaction;
    private AppDataBase appDataBase;

    public static ProductDetailFragment newInstance(int productId) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PRODUCT_ID, productId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            interaction = (Interaction) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Context Is Not Instance Of Interaction");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iaPresenter = new ProductDetailPresenter(this);
        productDetailAdapter = new ProductDetailAdapter();
        appDataBase = AppDataBase.newInstance(getContext());
        Bundle bundle = getArguments();
        if (bundle == null) return;
        setProductId(bundle.getInt(KEY_PRODUCT_ID));
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
        pbProgress = view.findViewById(R.id.product_detail_fragment_pb_progress);
        btnComment = view.findViewById(R.id.product_detail_fragment_btn_comment);
        ibPlay = view.findViewById(R.id.product_detail_fragment_ib_play);
        rvComment = view.findViewById(R.id.product_detail_fragment_rv_comment);
        rvComment.setLayoutManager(new LinearLayoutManager(getContext()));
        rvComment.setItemAnimator(new DefaultItemAnimator());
        rvComment.setAdapter(productDetailAdapter);
        btnComment.setOnClickListener(this);
        ibPlay.setOnClickListener(this);
        ivVideo.setOnClickListener(this);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.product_detail_fragment_btn_comment:
                if (appDataBase.userDao().getToken() == null) {
                    interaction.goToLogin();
                } else {
                    DialogFragment dialogFragment = CommentDialogFragment.newInstance(productId);
                    dialogFragment.show(getFragmentManager().beginTransaction(),
                            CommentDialogFragment.class.getName());
                }
                break;
            case R.id.product_detail_fragment_iv_video:
            case R.id.product_detail_fragment_ib_play:
                Intent intent = new Intent(getContext(), PlayerActivity.class);
                intent.putExtra(Constant.KEY_INTENT_MAIN_TO_PLAYER, product.getFiles().get(0).getFile());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void showProgress() {
        pbProgress.setVisibility(View.VISIBLE);
        isProductLoaded = false;
        isCommentLoaded = false;
    }

    @Override
    public void isProductLoaded() {
        isProductLoaded = true;
        if (isCommentLoaded) {
            pbProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public void isCommentLoaded() {
        isCommentLoaded = true;
        if (isProductLoaded) {
            pbProgress.setVisibility(View.GONE);
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
    public void showErrorMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public interface Interaction {

        void goToLogin();
    }
}