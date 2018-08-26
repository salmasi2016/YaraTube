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
import com.yaratech.yaratube.util.Tool;

import java.util.ArrayList;

public class FragmentCategoryGrid extends Fragment implements ContractCategoryGrid.View
        , AdapterCategoryGrid.Interaction {
    private ContractCategoryGrid.Presenter iaPresenter;
    private RecyclerView rvCategoryGrid;
    private AdapterCategoryGrid adapterCategoryGrid;
    private Category category;
    private Interaction interaction;
    private Toolbar toolbar;
    private ProgressBar pbLoad;

    public static FragmentCategoryGrid newInstance(Category category) {
        FragmentCategoryGrid fragment = new FragmentCategoryGrid();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Tool.FRAGMENT_CATEGORY_GRID_CATEGORY, category);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interaction = (FragmentCategoryGrid.Interaction) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iaPresenter = new PresenterCategoryGrid(this);
        adapterCategoryGrid = new AdapterCategoryGrid(FragmentCategoryGrid.this);
        Bundle bundle = getArguments();
        if (bundle == null) return;
        setCategory((Category) bundle.getParcelable(Tool.FRAGMENT_CATEGORY_GRID_CATEGORY));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_grid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = view.findViewById(R.id.fragment_category_grid_toolbar);
        toolbar.setTitle(getCategory().getTitle());
        rvCategoryGrid = view.findViewById(R.id.fragment_category_grid_rv_product);
        pbLoad = view.findViewById(R.id.fragment_category_grid_pb_load);
        rvCategoryGrid.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));
        rvCategoryGrid.setItemAnimator(new DefaultItemAnimator());
        rvCategoryGrid.setAdapter(adapterCategoryGrid);
        iaPresenter.loadData(getCategory());
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
    public void showProducts(ArrayList<Product> products) {
        adapterCategoryGrid.setProducts(products);
    }

    @Override
    public void showToast() {
        Toast.makeText(getActivity(),"Data Not Available",Toast.LENGTH_SHORT).show();
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