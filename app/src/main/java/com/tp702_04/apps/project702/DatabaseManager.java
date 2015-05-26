package com.tp702_04.apps.project702;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Nazish Khan
 * @Since 16/04/2015
 */

/**
 * This class aims to make the database thread safe by implementing concurrency. To use the database with multiple threads we need to make sure we are using one database connection.
 */

public class DatabaseManager {

    private AtomicInteger counter = new AtomicInteger();

    private static DatabaseManager instance;
    private static SQLiteOpenHelper myDatabaseHandler;
    private SQLiteDatabase myDatabase;

    /**This method initializes an instance of the database in order to be used my the DatabaseHandler class.
     * @param helper
     */
    public static synchronized void initializeInstance(SQLiteOpenHelper helper) {
        if (instance == null) {
            instance = new DatabaseManager();
            myDatabaseHandler = helper;
        }
    }

    /**This method returns an instance of the database in order open and close the database connection.
     * @return
     */
    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException(DatabaseManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return instance;
    }

    /**Opens a new database connection when counter reaches one
     * @return
     */
    public synchronized SQLiteDatabase openDatabase() {
        if(counter.incrementAndGet() == 1) {
            myDatabase = myDatabaseHandler.getWritableDatabase();
        }
        return myDatabase;
    }

    /** Closes existing database connection when counter reaches zero
     */
    public synchronized void closeDatabase() {
        if(counter.decrementAndGet() == 0) {
            myDatabase.close();
        }
    }
}
