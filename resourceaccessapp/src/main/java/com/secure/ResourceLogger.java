package com.secure;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by matthewdyer on 23/05/15.
 *
 * @class ResourceLogger
 * Logs resource access
 */
public class ResourceLogger {

    private static final String BROADCAST_URI = "DetectionServiceMessage";
    public static Context context;
    private static Queue<ComparableIntent> pendingIntents = null;

    private static void sendMessage(Intent i){
        if (pendingIntents == null){
            pendingIntents = new PriorityQueue<ComparableIntent>();
        }
        pendingIntents.add(new ComparableIntent(i));

        if (context == null) {
           return;
        }
        Log.d("Secure", "Attempting to Send Broadcast");
        if (context != null) {
            while (pendingIntents.peek() != null) {
                Log.d("Secure", "Sending Broadcast");
                Intent intent = pendingIntents.poll().getIntent();
                if (intent.getStringExtra("app_name").equals("null")){
                    intent.removeExtra("app_name");
                    intent.putExtra("app_name",context.getPackageName());
                }
                context.sendBroadcast(intent);
            }
        }
    }

    private static void log(Uri uri, String type){
        if (context == null) {
            return;
        }
        Intent intent = new Intent(ResourceLogger.BROADCAST_URI);
        intent.putExtra("resource_accessed_name", "Contacts " + type);
        if (context == null) {
            intent.putExtra("app_name", "");
        }else{
            intent.putExtra("app_name", context.getPackageName());
        }
        Date date = new Date();
        date.setTime(System.currentTimeMillis());

        SimpleDateFormat time = new SimpleDateFormat("HH : mm : ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("E : d : MMM : y");
        intent.putExtra("date", dateFormat.format(date));
        intent.putExtra("time", time.format(date));
        intent.putExtra("tag_message", uri.toString());

        DetectionHelper detectionHelper = new DetectionHelper(context);
        intent.putExtra("is_machine_access", detectionHelper.isMachineAccess() ? "true" : "false");

        sendMessage(intent);
    }


    public static void logQuery(Uri uri){
        log(uri,"query");
    }

    public static void logUpdate(Uri uri){
        log(uri,"update");
    }

    public static void logInsert(Uri uri){
        log(uri,"insert");
    }

    public static void logDelete(Uri uri){
        log(uri,"delete");
    }


}
