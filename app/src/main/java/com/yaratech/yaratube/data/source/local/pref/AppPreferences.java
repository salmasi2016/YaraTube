package com.yaratech.yaratube.data.source.local.pref;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    public Context context;
    private SharedPreferences pref;
    final String PREF_NAME = AppPreferences.class.getName();
    final String KEY_VERIFICATION_LOGIN = "verificationLogin";
    final String KEY_PHONE_NUMBER="phoneNumber";

    public AppPreferences(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public boolean isVerificationLogin() {
        return pref.getBoolean(KEY_VERIFICATION_LOGIN, false);
    }

    public void setVerificationLogin(Boolean isVerificationStep) {
        pref.edit().putBoolean(KEY_VERIFICATION_LOGIN, isVerificationStep).apply();
    }

    public String getPhoneNumber(){
        return pref.getString(KEY_PHONE_NUMBER,null);
    }

    public void setPhoneNumber(String phoneNumber){
        pref.edit().putString(KEY_PHONE_NUMBER,phoneNumber).apply();
    }
}