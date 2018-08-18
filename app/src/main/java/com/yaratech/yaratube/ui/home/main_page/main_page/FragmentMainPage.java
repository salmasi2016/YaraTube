package com.yaratech.yaratube.ui.home.main_page.main_page;

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
import com.yaratech.yaratube.data.model.HeaderItem;
import com.yaratech.yaratube.data.model.HomeItem;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.ui.home.main_page.header_item.AdapterHeaderItem;
import com.yaratech.yaratube.ui.home.main_page.home_item.AdapterHomeItem;

import java.util.ArrayList;

public class FragmentMainPage extends Fragment implements ContractMainPage.View
        , AdapterHomeItem.Interaction,AdapterMainPage.Interaction {
    private ContractMainPage.Presenter iaPresenter;
    private RecyclerView rvType;
    private AdapterMainPage adapterMainPage;
    private ProgressBar pbLoad;
    private Interaction interaction;

    public static FragmentMainPage newInstance() {
        FragmentMainPage fragment = new FragmentMainPage();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interaction = (FragmentMainPage.Interaction) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iaPresenter = new PresenterMainPage(this);
        adapterMainPage = new AdapterMainPage(getFragmentManager(),FragmentMainPage.this);
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

    @Override
    public void showToast() {
        Toast.makeText(getActivity(),"Data Not Available",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setProductToFragmentProductDetail(Product product) {
        interaction.goToFragmentProductDetail(product);
    }

    @Override
    public void setHeaderItemToFragmentProductDetail(HeaderItem headerItem) {
        interaction.goToFragmentProductDetail(headerItem);
    }

    public interface Interaction {

        void goToFragmentProductDetail(Product product);

        void goToFragmentProductDetail(HeaderItem headerItem);
    }
}