package com.tp702_04.apps.project702;

import android.app.IntentService;
import android.content.Intent;

/**
 * @author jamesbutler
 *
 * DetectionService is responsible for adding resource access information to the database. It
 * receives an intent which contains the information to construct a logItem which can then be
 * stored in the database.
 *
 * The class is implemented as an intentservice so that a potentially costly database operation
 * will not block the application's main loop (As described in android dev reference). In this way
 * we will not miss a resource access in the event of slow write operations.
 *
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
