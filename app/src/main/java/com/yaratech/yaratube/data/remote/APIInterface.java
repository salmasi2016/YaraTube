package com.yaratech.yaratube.data.remote;

import com.yaratech.yaratube.model.Category;
import com.yaratech.yaratube.model.Home;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("store/16")
    Call<Home> getHome();

    @GET("category/16/463")
    Call<ArrayList<Category>> getCategories();

//    @GET("/posts")
//    Call<ArrayList<>> getPost(@Query("userId") int userId);
}