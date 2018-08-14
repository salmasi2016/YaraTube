package com.yaratech.yaratube.data.source.remote;

import android.util.Log;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Home;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    public void loadCategory(final LoadCallback.Categories callback) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ArrayList<Category>> call = apiInterface.getCategories();
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                if (response.isSuccessful()) {
                    callback.onLoadedData(response.body());
                    Log.i("sina","category loaded.");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                Log.i("sina","category failured.");
                Log.i("sina","*"+t.getLocalizedMessage());
            }
        });
    }

    public void loadMainPage(final LoadCallback.MainPage callback) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Home> call = apiInterface.getHome();
        call.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                if (response.isSuccessful()) {
                    callback.onLoadedData(response.body().getHeaderItems(),response.body().getHomeItems());
                    Log.i("sina","mainPage loaded.");
                }
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {
                Log.i("sina","mainPage failured.");
                Log.i("sina","*"+t.getLocalizedMessage());
            }
        });
    }
}