package com.yaratech.yaratube.ui.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkChangeReceiver extends BroadcastReceiver {
    private Interaction interaction;

    public NetworkChangeReceiver(){}

    public NetworkChangeReceiver(Context context) {
        interaction = (Interaction) context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (isOnline(context)){
            Log.i("sina","yes");
            interaction.startToConnecting();
        }else {
            Log.i("sina","no");
        }
    }

    public boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

    public interface Interaction {

        void startToConnecting();
    }
}