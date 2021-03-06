package com.yaratech.yaratube.data.source.remote;

import android.content.Context;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.Home;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.util.Function;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private ApiInterface apiInterface;

    public Repository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void loadMainPage(final ApiResult<Home> callback) {
        Call<Home> call = apiInterface.getHome();
            call.enqueue(new Callback<Home>() {
                @Override
                public void onResponse(Call<Home> call, Response<Home> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<Home> call, Throwable t) {
                    callback.onFail(t.getMessage());
                }
            });
    }

    public void loadCategory(final ApiResult<ArrayList<Category>> callback) {
        Call<ArrayList<Category>> call = apiInterface.getCategories();
            call.enqueue(new Callback<ArrayList<Category>>() {
                @Override
                public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                    callback.onFail(t.getMessage());
                }
            });
    }

    public void loadCategoryGrid(Category category, int offset, final ApiResult<ArrayList<Product>> callback) {
        Call<ArrayList<Product>> call = apiInterface.getCategoryGrid(category.getId(), offset);
            call.enqueue(new Callback<ArrayList<Product>>() {
                @Override
                public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                    callback.onFail(t.getMessage());
                }
            });
    }

    public void loadComment(int productId, final ApiResult<ArrayList<Comment>> callback) {
        Call<ArrayList<Comment>> call = apiInterface.getComment(productId);
            call.enqueue(new Callback<ArrayList<Comment>>() {
                @Override
                public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {
                    callback.onFail(t.getMessage());
                }
            });
    }

    public void loadProduct(int productId, final ApiResult<Product> callback) {
        Call<Product> call = apiInterface.getProduct(productId);
            call.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                    callback.onFail(t.getMessage());
                }
            });
    }
}