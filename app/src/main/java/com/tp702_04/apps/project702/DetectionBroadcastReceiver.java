package com.tp702_04.apps.project702;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author jamesbutler
 *
 * DetectionBroadcastReceiver is responsible for receiving intents from a repackaged apk. The intent
 * is received from the resource logger which contains information regarding the resource that has
 * been accessed.
 *
 * Once it receives an intent this class immediately creates a new intent to pass this information
 * along to the detection service class to prevent any blocking that may occur.
 */
public class DetectionBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent newIntent = new Intent(context, DetectionService.class);
        newIntent.putExtra("resource_accessed_name", intent.getStringExtra("resource_accessed_name"));
        newIntent.putExtra("app_name",intent.getStringExtra("app_name"));
        newIntent.putExtra("date", intent.getStringExtra("date"));
        newIntent.putExtra("time", intent.getStringExtra("time"));
        newIntent.putExtra("tag_message", intent.getStringExtra("tag_message"));
        newIntent.putExtra("is_machine_access", intent.getStringExtra("is_machine_access"));
        context.startService(newIntent);
    }
}
