package com.yaratech.yaratube.data.source.remote;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Home;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.util.Tool;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("store/"+ Tool.STORE_ID)
    Call<Home> getHome();

    @GET("category/"+Tool.STORE_ID+"/463")
    Call<ArrayList<Category>> getCategories();

    @GET("listproducts/{category_id}")
    Call<ArrayList<Product>> getCategoryGrid(@Query("category_id") int categoryId);
}