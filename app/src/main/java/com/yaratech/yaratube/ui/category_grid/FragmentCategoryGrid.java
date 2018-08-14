package com.yaratech.yaratube.ui.category_grid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.util.Tool;

import java.util.ArrayList;

public class FragmentCategoryGrid extends Fragment implements ContractCategoryGrid.View {
    private ContractCategoryGrid.Presenter iaPresenter;
    private RecyclerView rvCategoryGrid;
    private AdapterCategoryGrid adapterCategoryGrid;
    private Category category;

    public static FragmentCategoryGrid newInstance(Category category) {
        FragmentCategoryGrid fragment = new FragmentCategoryGrid();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Tool.FRAGMENT_CATEGORY_GRID_CATEGORY, category);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iaPresenter = new PresenterCategoryGrid(this);
        adapterCategoryGrid = new AdapterCategoryGrid();
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
        rvCategoryGrid = view.findViewById(R.id.fragment_category_grid_rv_product);
        rvCategoryGrid.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));
        rvCategoryGrid.setItemAnimator(new DefaultItemAnimator());
        rvCategoryGrid.setAdapter(adapterCategoryGrid);
        iaPresenter.loadData(getCategory());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProducts(ArrayList<Product> products) {
        adapterCategoryGrid.setProducts(products);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}