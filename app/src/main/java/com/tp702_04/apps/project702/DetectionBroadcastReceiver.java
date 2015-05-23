package com.tp702_04.apps.project702;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by jamesbutler on 23/05/15.
 */
public class DetectionBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(intent);
    }
}
