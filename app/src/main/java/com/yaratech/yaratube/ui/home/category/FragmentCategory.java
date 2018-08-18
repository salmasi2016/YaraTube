package com.yaratech.yaratube.ui.home.category;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Category;

import java.util.ArrayList;

public class FragmentCategory extends Fragment implements ContractCategory.View, AdapterCategory.Interaction {
    private ContractCategory.Presenter iaPresenter;
    private RecyclerView rvCategory;
    private AdapterCategory adapterCategory;
    private Interaction interaction;
    private ProgressBar pbLoad;

    public static FragmentCategory newInstance() {
        FragmentCategory fragment = new FragmentCategory();
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
        iaPresenter = new PresenterCategory(this);
        adapterCategory = new AdapterCategory(FragmentCategory.this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pbLoad = view.findViewById(R.id.fragment_category_pb_load);
        rvCategory = view.findViewById(R.id.fragment_category_rv_category);
        rvCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCategory.setItemAnimator(new DefaultItemAnimator());
        rvCategory.setAdapter(adapterCategory);
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
        adapterCategory.setCategories(categories);
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