package com.yaratech.yaratube.data.source.remote;

import android.content.Context;
import android.widget.Toast;

import com.yaratech.yaratube.data.model.Activation;
import com.yaratech.yaratube.data.model.SmsResponse;
import com.yaratech.yaratube.data.source.local.pref.AppPreferences;
import com.yaratech.yaratube.util.Constant;
import com.yaratech.yaratube.util.Device;
import com.yaratech.yaratube.util.Function;
import com.yaratech.yaratube.util.Tool;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private APIInterface apiInterface;
    private Context context;
    private AppPreferences pref;

    public UserRepository(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
        pref=new AppPreferences(context);
    }

    public void sendPhoneNumber(final ApiResult<SmsResponse> callback) {

        Call<SmsResponse> call = apiInterface.activateStep1(pref.getPhoneNumber(),
                Device.getDeviceId(context), Device.getDeviceModel(), Device.getDeviceOs());

        if(Function.isNetworkAvailable(context)) {
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

    public void sendVerificationCode(final ApiResult<Activation> callback, int verificationCode) {

        Call<Activation> call = apiInterface.activateStep2(pref.getPhoneNumber(),
                Device.getDeviceId(context), verificationCode);

        if(Function.isNetworkAvailable(context)) {
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
        Toast.makeText(context, Constant.INTERNET_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
    }
}