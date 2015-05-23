package com.secure;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by matthewdyer on 23/05/15.
 */
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Secure", "Tick");
        Intent serviceLauncher = new Intent(context, InjectionService.class);
        context.startService(serviceLauncher);

    }
}
