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
        Intent newIntent = new Intent(context, DetectionService.class);
        newIntent.putExtra("resource_accessed_name", intent.getStringExtra("resource_accessed_name"));
        newIntent.putExtra("app_name",intent.getStringExtra("app_name"));
        newIntent.putExtra("date", intent.getStringExtra("date"));
        newIntent.putExtra("time", intent.getStringExtra("time"));
        newIntent.putExtra("tag_message", intent.getStringExtra("tag_message"));
        context.startService(newIntent);
    }
}
