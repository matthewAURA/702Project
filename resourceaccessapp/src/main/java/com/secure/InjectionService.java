package com.secure;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by matthewdyer on 23/05/15.
 */
public class InjectionService extends IntentService {

    private static Context serviceContext;

    public InjectionService(){
        this("com.secure.InjectionService");
    }


    public InjectionService(String name) {
        super(name);
        Log.d("Secure", "InjectionService Created");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        InjectionService.serviceContext = this.getApplicationContext();
    }

    public static Context getServiceContext(){
        return serviceContext;
    }
}
