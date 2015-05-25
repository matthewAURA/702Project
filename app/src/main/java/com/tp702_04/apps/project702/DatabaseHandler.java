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
    private static final String DATABASE_NAME = "resourceAccessItemsManager";

    // LogItems table name
    private static final String TABLE_RESOURCE_ACCESS_ITEMS = "resource_access_items";

    // LogItems Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "resource_accessed_name";
    private static final String KEY_APP = "app_name";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_TAG_MESSAGE = "tag_message";
    private static final String KEY_IS_MACHINE_ACCESS = "is_machine_access";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOG_ITEMS_TABLE = "CREATE TABLE " + TABLE_RESOURCE_ACCESS_ITEMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_APP + " TEXT,"
                + KEY_DATE + " TEXT," + KEY_TIME + " TEXT," + KEY_TAG_MESSAGE + " TEXT,"
                + KEY_IS_MACHINE_ACCESS + "TEXT)";
        db.execSQL(CREATE_LOG_ITEMS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESOURCE_ACCESS_ITEMS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Delete) Operations
     */

    // Adding new resource access item. This method accepts Contact object as parameter. We need to
    // build ContentValues parameters using ResourceAccessItem object. Once we inserted data in
    // database we need to close the database connection.
    public void addResourceAccessItem(ResourceAccessItem resourceAccessItem) {

        DatabaseManager.initializeInstance(this);
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, resourceAccessItem.getName());
        values.put(KEY_APP, resourceAccessItem.getApp());
        values.put(KEY_DATE, resourceAccessItem.getDate());
        values.put(KEY_TIME, resourceAccessItem.getTime());
        values.put(KEY_TAG_MESSAGE, resourceAccessItem.getTagMessage());
        values.put(KEY_IS_MACHINE_ACCESS, resourceAccessItem.getIsMachineAccess());

        // Inserting Row
        db.insert(TABLE_RESOURCE_ACCESS_ITEMS, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }

    // Getting single resource access item. It accepts id as parameter and will return the matched row from the database.
    public ResourceAccessItem getResourceAccessItem(int id) {

        DatabaseManager.initializeInstance(this);
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_RESOURCE_ACCESS_ITEMS, new String[] { KEY_ID,
                        KEY_NAME, KEY_APP, KEY_DATE, KEY_TIME, KEY_TAG_MESSAGE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ResourceAccessItem resourceAccessItem = new ResourceAccessItem(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6));

        // return resource access item
        DatabaseManager.getInstance().closeDatabase();
        return resourceAccessItem;
    }

    // Getting all resource access Items. This method will return all resource access items from
    // database in array list format of ResourceAccessItem class type. You need to write a for
    // loop to go through each contact.
    public List<ResourceAccessItem> getAllResourceAccessItems() {

        List<ResourceAccessItem> resourceAccessItemList = new ArrayList<ResourceAccessItem>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RESOURCE_ACCESS_ITEMS;

        DatabaseManager.initializeInstance(this);
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ResourceAccessItem resourceAccessItem = new ResourceAccessItem();
                resourceAccessItem.setID(cursor.getInt(0));
                resourceAccessItem.setName(cursor.getString(1));
                resourceAccessItem.setApp(cursor.getString(2));
                resourceAccessItem.setDate(cursor.getString(3));
                resourceAccessItem.setTime(cursor.getString(4));
                resourceAccessItem.setTagMessage(cursor.getString(5));
                resourceAccessItem.setIsMachineAccess(cursor.getString(6));
                resourceAccessItemList.add(resourceAccessItem);
            } while (cursor.moveToNext());
        }

        // return resource access item list
        DatabaseManager.getInstance().closeDatabase();
        return resourceAccessItemList;
    }

    // Getting resource access items Count. This method will return total number of resource access
    // items in SQLite database.
    public int getResourceAccessItemsCount() {

        String countQuery = "SELECT  * FROM " + TABLE_RESOURCE_ACCESS_ITEMS;
        DatabaseManager.initializeInstance(this);
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count=cursor.getCount();
        cursor.close();

        // return count
        DatabaseManager.getInstance().closeDatabase();
        return count;
    }

    // Deleting all resource access items
    public void deleteAllResourceAccessItems() {
        DatabaseManager.initializeInstance(this);
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(TABLE_RESOURCE_ACCESS_ITEMS, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
