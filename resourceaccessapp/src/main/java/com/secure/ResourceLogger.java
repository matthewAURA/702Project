package com.secure;

import android.app.Activity;
import android.app.Application;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by matthewdyer on 23/05/15.
 */
public class ResourceLogger {

    private static final String BROADCAST_URI = "DetectionServiceMessage";
    public static Context context;



    public static void logQuery(Uri uri){
        Intent testIntent = new Intent(ResourceLogger.BROADCAST_URI);
        testIntent.putExtra("resource_accessed_name", "Resource Accessed");
        testIntent.putExtra("app_name", "App Name");
        testIntent.putExtra("date", "Date");
        testIntent.putExtra("time", "Time");
        testIntent.putExtra("tag_message", uri.toString());
        context = InjectionService.getServiceContext();
        if (context != null) {
            context.sendBroadcast(testIntent);
        }

    }
}
