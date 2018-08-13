package com.yaratech.yaratube.ui.home;

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

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.remote.APIClient;
import com.yaratech.yaratube.data.remote.APIInterface;
import com.yaratech.yaratube.model.Home;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPageFragment extends Fragment {
    private RecyclerView rvTypes;
    private AdapterTypes adapterTypes;

    public static MainPageFragment newInstance() {
        MainPageFragment fragment = new MainPageFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_main_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTypes=view.findViewById(R.id.home_main_page_rv_types);
        adapterTypes = new AdapterTypes();
        rvTypes.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTypes.setItemAnimator(new DefaultItemAnimator());
        rvTypes.setAdapter(adapterTypes);
        typesRequest();
    }

    private void typesRequest() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Home> call = apiInterface.getHome();
        call.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                if (response.isSuccessful()) {
                    adapterTypes.setHeaderItems(response.body().getHeaderItems());
                    adapterTypes.setHomeItems(response.body().getHomeItems());
                }
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {
                Log.i("sina","MainPage NO!");
            }
        });
    }
}