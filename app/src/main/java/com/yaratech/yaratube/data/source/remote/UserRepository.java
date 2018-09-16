package com.yaratech.yaratube.data.source.remote;

import android.content.Context;
import android.util.Log;

import com.yaratech.yaratube.data.model.Activation;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.CommentResponse;
import com.yaratech.yaratube.data.model.GoogleResponse;
import com.yaratech.yaratube.data.model.SmsResponse;
import com.yaratech.yaratube.data.model.UserResponse;
import com.yaratech.yaratube.data.source.local.db.entity.User;
import com.yaratech.yaratube.data.source.local.pref.AppPreferences;
import com.yaratech.yaratube.util.Device;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static final String TAG = "UserRepository";
    private ApiInterface apiInterface;
    private Context context;
    private AppPreferences pref;

    public UserRepository(Context context) {
        this.context = context;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        pref = new AppPreferences(context);
    }

    public void sendPhoneNumber(final ApiResult<SmsResponse> callback) {

        Call<SmsResponse> call = apiInterface.activateStep1(pref.getPhoneNumber(),
                Device.getDeviceId(context), Device.getDeviceModel(), Device.getDeviceOs());

        call.enqueue(new Callback<SmsResponse>() {
            @Override
            public void onResponse(Call<SmsResponse> call, Response<SmsResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<SmsResponse> call, Throwable t) {

                callback.onFail(t.getMessage());
            }
        });
    }

    public void sendVerificationCode(int verificationCode, final ApiResult<Activation> callback) {

        Call<Activation> call = apiInterface.activateStep2(pref.getPhoneNumber(),
                Device.getDeviceId(context), verificationCode);

        call.enqueue(new Callback<Activation>() {

            @Override
            public void onResponse(Call<Activation> call, Response<Activation> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<Activation> call, Throwable t) {
                callback.onFail(t.getMessage());
            }
        });
    }

    public void sendComment(int productId, Comment comment, String token,
                            final ApiResult<CommentResponse> callback) {

        Call<CommentResponse> call = apiInterface.setComment(productId,
                comment.getScore(), comment.getCommentText(), comment.getTitle(), token);

        call.enqueue(new Callback<CommentResponse>() {

            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    Log.i("sina", "code: " + response.code());
                    callback.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                callback.onFail(t.getMessage());
            }
        });
    }

    public void sendGoogleLogin(String tokenIdGoogle, final ApiResult<GoogleResponse> callback) {

        Call<GoogleResponse> call = apiInterface.setTokenGoogle(tokenIdGoogle,
                Device.getDeviceId(context), Device.getDeviceModel(), Device.getDeviceOs());

        call.enqueue(new Callback<GoogleResponse>() {

            @Override
            public void onResponse(Call<GoogleResponse> call, Response<GoogleResponse> response) {
                Log.d(TAG, "<<<<onResponse() called with: call = [" + call + "], response = [" + response.isSuccessful() + "]");
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<GoogleResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                callback.onFail(t.getMessage());
            }
        });
    }

    public void sendUser(User user, final ApiResult<UserResponse> callback) {

        Call<UserResponse> call = apiInterface.sendUser(
                user.getFullName(), user.getBirthDate(), user.getToken(),
                Device.getDeviceId(context), Device.getDeviceModel(), Device.getDeviceOs());

        call.enqueue(new Callback<UserResponse>() {

            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                callback.onFail(t.getMessage());
            }
        });
    }
}