package com.yaratech.yaratube.data.source.remote;

import android.content.Context;
import android.widget.Toast;

import com.yaratech.yaratube.data.model.Activation;
import com.yaratech.yaratube.data.model.SmsResponse;
import com.yaratech.yaratube.util.Tool;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private APIInterface apiInterface;
    private Context context;

    public UserRepository(Context context) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        this.context = context;
    }

    public void sendPhoneNumber(final ApiResult<SmsResponse> callback, String phoneNumber, String deviceId, String deviceModel, String deviceOs) {

        Call<SmsResponse> call = apiInterface.activateStep1(phoneNumber, deviceId, deviceModel, deviceOs);

        if(Tool.isNetworkAvailable(context)) {
            call.enqueue(new Callback<SmsResponse>() {
                @Override
                public void onResponse(Call<SmsResponse> call, Response<SmsResponse> response) {
                    if(response.isSuccessful()) {
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
        } else {
            toastNetworkNotAvailable(context);
        }
    }

    public void sendVerificationCode(final ApiResult<Activation> callback, String phoneNumber, String deviceId, int verificationCode) {

        Call<Activation> call = apiInterface.activateStep2(phoneNumber, deviceId, verificationCode);

        if(Tool.isNetworkAvailable(context)) {
            call.enqueue(new Callback<Activation>() {
                @Override
                public void onResponse(Call<Activation> call, Response<Activation> response) {
                    if(response.isSuccessful()) {
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
    }

    private void toastNetworkNotAvailable(Context context) {

        Toast.makeText(context, Tool.INTERNET_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
    }
}