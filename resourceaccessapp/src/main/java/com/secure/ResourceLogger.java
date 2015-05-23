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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by matthewdyer on 23/05/15.
 */
public class ResourceLogger {

    private static final String BROADCAST_URI = "DetectionServiceMessage";
    public static Context context;
    private static Queue<Intent> pendingIntents = null;

    private static void sendMessage(Intent i){
        if (pendingIntents == null){
            pendingIntents = new PriorityQueue<Intent>();
        }
        pendingIntents.add(i);
        context = InjectionService.getServiceContext();
        if (context != null) {
            while (pendingIntents.peek() != null) {
                context.sendBroadcast(pendingIntents.poll());
            }
        }
    }

    public static void logQuery(Uri uri){
        Intent testIntent = new Intent(ResourceLogger.BROADCAST_URI);
        testIntent.putExtra("resource_accessed_name", "Contacts");
        testIntent.putExtra("app_name", "App Name");
        Date date = new Date();
        date.setTime(System.currentTimeMillis());

        SimpleDateFormat time = new SimpleDateFormat("HH : mm : ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("E : d : MMM : y");

        testIntent.putExtra("date", dateFormat.format(date));
        testIntent.putExtra("time", time.format(date));
        testIntent.putExtra("tag_message", uri.toString());

        
    }
}
