package com.example.resourceaccessapp;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;



/**
 * Created by Simon on 9/04/2015.
 */
public class AccessService extends IntentService {
    public AccessService(){
        super("AccessService");
    }
    private static final String LOG_TAG = "702 RESOURCE ACCESS APP";

    @Override
    protected void onHandleIntent(Intent workIntent){
        while(true){
            synchronized (this) {
                Log.d(LOG_TAG, "Waiting...");
                try {
                    wait(1000);
                }catch (InterruptedException e){}
                Log.d(LOG_TAG, "Accessing Contacts");
                accessContacts();
            }
        }
    }

    public void updateContact (String contactId, String contactName){
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        ops.add(ContentProviderOperation.newUpdate(ContactsContract.Contacts.CONTENT_URI)
                .withSelection(ContactsContract.Contacts._ID + " = ?", new String[]{contactId})
                .withValue(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY, contactName)
                .build());
        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        }catch(RemoteException e){
            e.printStackTrace();
        } catch(OperationApplicationException e){
            e.printStackTrace();
        }
    }

    private void accessContacts(){
        Log.d(LOG_TAG,"Getting Content Resolver");
        Context c = this.getApplicationContext();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        Log.d(LOG_TAG,"Content Provider queried");
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String newName;
                newName = name;
                updateContact(id, newName);
            }
        }
        Log.d(LOG_TAG,"Contacts queried successfully");
    }
}
