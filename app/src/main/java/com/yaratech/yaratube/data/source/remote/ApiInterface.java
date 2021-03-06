package com.yaratech.yaratube.data.source.remote;

import com.yaratech.yaratube.data.model.Activation;
import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.CommentResponse;
import com.yaratech.yaratube.data.model.GoogleResponse;
import com.yaratech.yaratube.data.model.Home;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.SmsResponse;
import com.yaratech.yaratube.data.model.UserResponse;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("store/16")
    Call<Home> getHome();

    @GET("category/16/463")
    Call<ArrayList<Category>> getCategories();

    @GET("listproducts/{category_id}?limit=10")
    Call<ArrayList<Product>> getCategoryGrid(@Path("category_id") int categoryId,
                                             @Query("offset") int offset);

    @GET("comment/{product_id}")
    Call<ArrayList<Comment>> getComment(@Path("product_id") int productId);

    @GET("product/{product_id}?device_os=ios")
    Call<Product> getProduct(@Path("product_id") int productId);

    @POST("mobile_login_step1/16")
    @FormUrlEncoded
    Call<SmsResponse> activateStep1(@Field("mobile") String phoneNumber,
                                    @Field("device_id") String deviceId,
                                    @Field("device_model") String deviceModel,
                                    @Field("device_os") String deviceOs);

    @POST("mobile_login_step2/16")
    @FormUrlEncoded
    Call<Activation> activateStep2(@Field("mobile") String phoneNumber,
                                   @Field("device_id") String deviceId,
                                   @Field("verification_code") int verificationCode);

    @POST("comment/{product_id}")
    @FormUrlEncoded
    Call<CommentResponse> setComment(@Path("product_id") int productId,
                                     @Field("score") int score,
                                     @Field("comment_text") String commentText,
                                     @Field("title") String title,
                                     @Header("Authorization") String token);

    @POST("login_google/16")
    @FormUrlEncoded
    Call<GoogleResponse> setTokenGoogle(
            @Field("token_id") String googleToken,
            @Field("device_id") String deviceId,
            @Field("device_model") String deviceModel,
            @Field("device_os") String deviceOs);

    @POST("profile")
    @FormUrlEncoded
    Call<UserResponse> sendUser(
            @Field("nickname") String nickName,
            @Field("date_of_birth") String dateOfBirth,
            @Header("Authorization") String token,
            @Field("device_id") String deviceId,
            @Field("device_model") String deviceModel,
            @Field("device_os") String deviceOs
    );
}