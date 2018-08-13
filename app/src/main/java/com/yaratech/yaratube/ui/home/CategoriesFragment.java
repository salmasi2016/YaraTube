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
import com.yaratech.yaratube.model.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesFragment extends Fragment {
    private RecyclerView rvCategory;
    private AdapterCategories adapterCategories;

    public static CategoriesFragment newInstance() {
        CategoriesFragment fragment = new CategoriesFragment();
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
        return inflater.inflate(R.layout.home_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCategory = view.findViewById(R.id.home_category_rv_categories);
        adapterCategories = new AdapterCategories();
        rvCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCategory.setItemAnimator(new DefaultItemAnimator());
        rvCategory.setAdapter(adapterCategories);
        categoryRequest();
    }

    private void categoryRequest() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ArrayList<Category>> call = apiInterface.getCategories();
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                if (response.isSuccessful()) {
                    adapterCategories.setCategories(response.body());
                    Log.i("sina","category : "+adapterCategories.getCategories().size());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                Log.i("sina","NO !");
            }
        });
    }
}