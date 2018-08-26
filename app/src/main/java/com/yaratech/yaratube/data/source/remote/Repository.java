package com.yaratech.yaratube.data.source.remote;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.Home;
import com.yaratech.yaratube.data.model.Product;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class Repository {

    public void loadMainPage(final LoadCallback callback) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Home> call = apiInterface.getHome();
        call.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                if (response.isSuccessful()) {
                    callback.onLoadedData(response.body());
                }else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    public void loadCategory(final LoadCallback callback) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ArrayList<Category>> call = apiInterface.getCategories();
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                if (response.isSuccessful()) {
                    callback.onLoadedData(response.body());
                }else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    public void loadCategoryGrid(final LoadCallback callback,Category category) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ArrayList<Product>> call = apiInterface.getCategoryGrid(category.getId());
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if (response.isSuccessful()) {
                    callback.onLoadedData(response.body());
                }else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    public void loadComment(final LoadCallback callback,int productId) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ArrayList<Comment>> call = apiInterface.getComment(productId);
        call.enqueue(new Callback<ArrayList<Comment>>() {
            @Override
            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                if (response.isSuccessful()) {
                    callback.onLoadedData(response.body());
                }else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    public void loadProduct(final LoadCallback callback,int productId) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Product> call = apiInterface.getProduct(productId);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    callback.onLoadedData(response.body());
                }else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }
}