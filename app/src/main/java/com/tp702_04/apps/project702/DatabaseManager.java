package com.tp702_04.apps.project702;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

//import android.util.Log;

/**
 * Created by Nazish Khan on 16/04/2015.
 */
public class DatabaseManager {

    private AtomicInteger counter = new AtomicInteger();
    private static DatabaseManager instance;
    private static SQLiteOpenHelper myDatabaseHandler;
    private SQLiteDatabase myDatabase;

    public static synchronized void initializeInstance(SQLiteOpenHelper helper) {
        if (instance == null) {
            instance = new DatabaseManager();
            myDatabaseHandler = helper;
        }
        //Log.d("update", "i came here to initialise an instance");
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException(DatabaseManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        //Log.d("update1", "i came here to get an instance");
        return instance;
    }

    public synchronized SQLiteDatabase openDatabase() {
        if(counter.incrementAndGet() == 1) {
            // Opening new database
            myDatabase = myDatabaseHandler.getWritableDatabase();
        }
        //Log.d("update2", "i came here to open the database");
        return myDatabase;
    }

    public synchronized void closeDatabase() {
        if(counter.decrementAndGet() == 0) {
            // Closing database
            myDatabase.close();
        }
        //Log.d("update3", "i came here to close the database");
    }
}
