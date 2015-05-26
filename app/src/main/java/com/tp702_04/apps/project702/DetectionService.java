package com.tp702_04.apps.project702;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jamesbutler on 10/04/15.
 */
public class DetectionService extends IntentService {

    private DatabaseHandler databaseHandler;

    public DetectionService() {
        super("DetectionService");
        databaseHandler = new DatabaseHandler(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        LogItem logItem = new LogItem(intent.getStringExtra("resource_accessed_name"),
                intent.getStringExtra("app_name"),
                intent.getStringExtra("date"),
                intent.getStringExtra("time"),
                intent.getStringExtra("tag_message"),
                intent.getStringExtra("is_machine_access"));
        databaseHandler.addLogItem(logItem);
    }
}
