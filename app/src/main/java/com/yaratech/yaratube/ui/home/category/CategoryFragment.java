package com.yaratech.yaratube.ui.home.category;

import android.content.Context;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Category;

import java.util.ArrayList;

public class CategoryFragment extends Fragment implements CategoryContract.View, CategoryAdapter.Interaction {
    private CategoryContract.Presenter iaPresenter;
    private RecyclerView rvCategory;
    private CategoryAdapter categoryAdapter;
    private Interaction interaction;
    private ProgressBar pbLoad;

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        Bundle bundle = new Bundle();
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
        iaPresenter = new CategoryPresenter(this);
        categoryAdapter = new CategoryAdapter(CategoryFragment.this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.category_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pbLoad = view.findViewById(R.id.category_fragment_pb_load);
        rvCategory = view.findViewById(R.id.category_fragment_rv_category);
        rvCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCategory.setItemAnimator(new DefaultItemAnimator());
        rvCategory.setAdapter(categoryAdapter);
        iaPresenter.loadData();
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
    public void showCategories(ArrayList<Category> categories) {
        categoryAdapter.setCategories(categories);
    }

    @Override
    public void showToast() {
        Toast.makeText(getActivity(), "Data Not Available", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCategoryToFragmentCategory(Category category) {
        interaction.goToFragmentCategoryGrid(category);
    }

    public interface Interaction {

        void goToFragmentCategoryGrid(Category category);
    }
}