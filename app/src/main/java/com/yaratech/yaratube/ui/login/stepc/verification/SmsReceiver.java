package com.yaratech.yaratube.ui.login.stepc.verification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {
    private final String SERVICE_PROVIDER_NUMBER = "+98200049103";
    private static Interaction interaction;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            String smsSender = "";
            String smsBody = "";

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                    smsSender = smsMessage.getDisplayOriginatingAddress();
                    smsBody += smsMessage.getMessageBody();
                }
            }

            if (smsSender.equals(SERVICE_PROVIDER_NUMBER)) {
                if (interaction != null) {
                    smsBody = smsBody.replaceAll("\\D+", "");
                    interaction.onReceive(smsBody);
                }
            }
        }
    }


    public static void bindListener(Interaction smsListener) {
        interaction = smsListener;
    }

    public static void unbindListener() {
        interaction = null;
    }

    public interface Interaction {

        void onReceive(String message);
    }
}