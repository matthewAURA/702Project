package com.tp702_04.apps.project702;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nazish Khan on 10/04/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper{

    // All Static variables

    private final Context myContext;

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "logItemsManager";

    // LogItems table name
    private static final String TABLE_LOG_ITEMS = "log_items";

    // LogItems Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "resource_accessed_name";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_TAG_MESSAGE = "tag_message";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOG_ITEMS_TABLE = "CREATE TABLE " + TABLE_LOG_ITEMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DATE + " TEXT," + KEY_TIME + " TEXT," + KEY_TAG_MESSAGE + " TEXT" + ")";
        db.execSQL(CREATE_LOG_ITEMS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOG_ITEMS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Delete) Operations
     */

    // Adding new log item. This method accepts Contact object as parameter. We need to build ContentValues parameters using LogItem object. Once we inserted data in database we need to close the database connection.
    public void addLogItem(LogItem logitem) {

        DatabaseManager.initializeInstance(this);
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, logitem.getID()); // Log Item ID
        values.put(KEY_NAME, logitem.getName()); // Log Item Resource Accessed Name
        values.put(KEY_DATE, logitem.getDate()); // Log Item Date
        values.put(KEY_TIME, logitem.getTime()); // Log Item Time
        values.put(KEY_TAG_MESSAGE, logitem.getTagMessage()); // Log Item Tag Message

        // Inserting Row
        db.insert(TABLE_LOG_ITEMS, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }

    // Getting single log item. It accepts id as parameter and will return the matched row from the database.
    public LogItem getLogItem(int id) {

        DatabaseManager.initializeInstance(this);
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_LOG_ITEMS, new String[] { KEY_ID,
                        KEY_NAME, KEY_DATE, KEY_TIME, KEY_TAG_MESSAGE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        LogItem logitem = new LogItem(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        // return log item
        DatabaseManager.getInstance().closeDatabase();
        return logitem;
    }

    // Getting All Log Items. This method will return all log items from database in array list format of LogItem class type. You need to write a for loop to go through each contact.
    public List<LogItem> getAllLogItems() {

        List<LogItem> logItemList = new ArrayList<LogItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_LOG_ITEMS;

        DatabaseManager.initializeInstance(this);
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LogItem logitem = new LogItem();
                logitem.setID(cursor.getInt(0));
                logitem.setName(cursor.getString(1));
                logitem.setDate(cursor.getString(2));
                logitem.setTime(cursor.getString(3));
                logitem.setTagMessage(cursor.getString(4));
                // Adding log item to list
                logItemList.add(logitem);
            } while (cursor.moveToNext());
        }

        // return log item list
        DatabaseManager.getInstance().closeDatabase();
        return logItemList;
    }

    // Getting log items Count. This method will return total number of log items in SQLite database.
    public int getLogItemsCount() {

        String countQuery = "SELECT  * FROM " + TABLE_LOG_ITEMS;
        DatabaseManager.initializeInstance(this);
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count=cursor.getCount();
        cursor.close();

        // return count
        DatabaseManager.getInstance().closeDatabase();
        return count;
    }

    // Deleting all log items
    public void deleteLogItem(LogItem logitem) {

        DatabaseManager.initializeInstance(this);
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(TABLE_LOG_ITEMS, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
