package com.yaratech.yaratube.ui.home.mainpage.mainpage;

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
import com.yaratech.yaratube.ui.home.mainpage.homeitem.HomeItemAdapter;

import java.util.ArrayList;

public class MainPageFragment extends Fragment implements MainPageContract.View
        , HomeItemAdapter.Interaction {
    private MainPageContract.Presenter iaPresenter;
    private RecyclerView rvMain;
    private MainPageAdapter mainPageAdapter;
    private ProgressBar pbProgress;
    private Interaction interaction;

    public static MainPageFragment newInstance() {
        MainPageFragment fragment = new MainPageFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interaction = (MainPageFragment.Interaction) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iaPresenter = new MainPagePresenter(this);
        mainPageAdapter = new MainPageAdapter(getFragmentManager(),MainPageFragment.this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_page_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pbProgress = view.findViewById(R.id.main_page_fragment_pb_progress);
        rvMain = view.findViewById(R.id.main_page_fragment_rv_main);
        rvMain.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMain.setItemAnimator(new DefaultItemAnimator());
        rvMain.setAdapter(mainPageAdapter);
        iaPresenter.loadData();
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
    public void showMainPage(ArrayList<HeaderItem> headerItems, ArrayList<HomeItem> homeItems) {
        mainPageAdapter.setHeaderItems(headerItems);
        mainPageAdapter.setHomeItems(homeItems);
    }

    @Override
    public void showToast() {
        Toast.makeText(getActivity(),"Data Not Available",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setProductToFragmentProductDetail(int productId) {
        interaction.goToFragmentProductDetail(productId);
    }

    public interface Interaction {

        void goToFragmentProductDetail(int productId);
    }
}