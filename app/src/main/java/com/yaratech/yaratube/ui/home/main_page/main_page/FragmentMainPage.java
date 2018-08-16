package com.yaratech.yaratube.ui.home.main_page.main_page;

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

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.HeaderItem;
import com.yaratech.yaratube.data.model.HomeItem;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class FragmentMainPage extends Fragment implements ContractMainPage.View {
    private ContractMainPage.Presenter iaPresenter;
    private RecyclerView rvType;
    private AdapterMainPage adapterMainPage;
    private ProgressBar pbLoad;

    public static FragmentMainPage newInstance() {
        FragmentMainPage fragment = new FragmentMainPage();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iaPresenter = new PresenterMainPage(this);
        adapterMainPage = new AdapterMainPage(getFragmentManager());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pbLoad = view.findViewById(R.id.fragment_main_page_pb_load);
        rvType = view.findViewById(R.id.fragment_main_page_rv_type);
        rvType.setLayoutManager(new LinearLayoutManager(getContext()));
        rvType.setItemAnimator(new DefaultItemAnimator());
        rvType.setAdapter(adapterMainPage);
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
    public void showMainPage(ArrayList<HeaderItem> headerItems, ArrayList<HomeItem> homeItems) {
        adapterMainPage.setHeaderItems(headerItems);
        adapterMainPage.setHomeItems(homeItems);
    }
}