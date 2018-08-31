package com.yaratech.yaratube.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

public class Device {

    private Device() {
        //no instance
    }

    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static String getDeviceOs() {
        return android.os.Build.VERSION.RELEASE;
    }
}