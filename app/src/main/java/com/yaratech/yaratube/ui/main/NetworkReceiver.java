package com.yaratech.yaratube.ui.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import static android.support.constraint.Constraints.TAG;

public class NetworkReceiver extends BroadcastReceiver {
    public Interaction interaction;
    private Context context;

    public NetworkReceiver(){}

    public NetworkReceiver(Context context) {
        Log.d(TAG, "NetworkReceiver() called with: context = [" + context + "]");
        this.context = context;
        interaction= (Interaction) context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (isOnline(context)) {
            interaction.startToConnecting();
        } else {
            // No Internet
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