package com.tp702_04.apps.project702;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by jamesbutler on 10/04/15.
 */
public class DetectionService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("702", "Detection Service Started");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("702", "Detection Service Stopped");
    }
}
