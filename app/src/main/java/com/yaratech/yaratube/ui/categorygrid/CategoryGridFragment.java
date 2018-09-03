package com.yaratech.yaratube.ui.categorygrid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.util.PaginationScrollListener;

import java.util.ArrayList;

public class CategoryGridFragment extends Fragment
        implements CategoryGridContract.View, CategoryGridAdapter.Interaction {

    private CategoryGridContract.Presenter iaPresenter;
    private RecyclerView rvCategoryGrid;
    private CategoryGridAdapter categoryGridAdapter;
    private Category category;
    private Interaction interaction;
    private Toolbar toolbar;
    private ProgressBar pbProgress;
    public static final String KEY_CATEGORY = "category";
    private boolean isLoading = true;

    public static CategoryGridFragment newInstance(Category category) {
        CategoryGridFragment fragment = new CategoryGridFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_CATEGORY, category);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interaction = (CategoryGridFragment.Interaction) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iaPresenter = new CategoryGridPresenter(this);
        categoryGridAdapter = new CategoryGridAdapter(CategoryGridFragment.this);
        Bundle bundle = getArguments();
        if (bundle == null) return;
        setCategory((Category) bundle.getParcelable(KEY_CATEGORY));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.category_grid_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = view.findViewById(R.id.category_grid_fragment_toolbar);
        toolbar.setTitle(getCategory().getTitle());
        rvCategoryGrid = view.findViewById(R.id.category_grid_fragment_rv_product);
        pbProgress = view.findViewById(R.id.category_grid_fragment_pb_progress);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        rvCategoryGrid.setLayoutManager(layoutManager);
        rvCategoryGrid.setItemAnimator(new DefaultItemAnimator());
        rvCategoryGrid.setAdapter(categoryGridAdapter);
        iaPresenter.loadData(getCategory(), categoryGridAdapter.getItemCount());
        rvCategoryGrid.addOnScrollListener(new PaginationScrollListener(layoutManager) {

            @Override
            protected void loadMoreItems() {
                iaPresenter.loadData(getCategory(), categoryGridAdapter.getItemCount());
                isLoading = false;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    @Override
    public void showProgress() {
        pbProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbProgress.setVisibility(View.GONE);
    }

    @Override
    public void showProducts(ArrayList<Product> products) {
        categoryGridAdapter.setProducts(products);
        isLoading = true;
    }

    @Override
    public void showToast() {
        Toast.makeText(getActivity(), "Data Not Available", Toast.LENGTH_SHORT).show();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public void setProductToFragmentProductDetail(Product product) {
        interaction.goToFragmentProductDetail(product.getId());
    }

    public interface Interaction {

        void goToFragmentProductDetail(int productId);
    }
}