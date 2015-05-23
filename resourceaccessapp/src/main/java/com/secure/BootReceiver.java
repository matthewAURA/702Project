package com.secure;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by matthewdyer on 23/05/15.
 */
public class BootReceiver extends Activity {

    @Override
    public void onCreate(Bundle saved) {
        Log.d("Secure", "Tick");
        Context context = this.getApplicationContext();
        Intent serviceLauncher = new Intent(context, InjectionService.class);
        //context.startService(serviceLauncher);
        finish();

    }
}
