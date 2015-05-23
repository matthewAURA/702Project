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

import com.secure.InjectionService;
import com.secure.ResourceLogger;


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
        //Uri[] paths = getPhotoPaths();
        //while(true){
            synchronized (this) {
                accessContacts();
            }
        //}
    }

    public void updateContact (String contactId, String contactName){
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        ops.add(ContentProviderOperation.newUpdate(ContactsContract.RawContacts.CONTENT_URI)
                .withSelection(ContactsContract.RawContacts._ID + " = ?", new String[]{contactId})
                .withValue(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY, contactName)
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

        Cursor cur = cr.query(ContactsContract.RawContacts.CONTENT_URI, null, null, null, null);
        if (cur.getCount() > 0 && false) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.RawContacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY));
                String newName;
                if (!name.contains("*702*")) {
                    newName = name + (" *702*");
                } else {
                    newName = name;
                }
                updateContact(id, newName);
                Log.d(LOG_TAG, "ID: " + id + " Name: " + name);
                try {
                    wait(100);
                }catch (InterruptedException e){}
            }
        }
    }

    private void accessImages(Uri[] paths){
        int count = 1;
        int numPhotos = paths.length;
        for (Uri path: paths){
            try {
                Log.d(LOG_TAG, "Photo " + count + " / " + numPhotos + " : "
                        + path.toString());

                Bitmap bitmap = BitmapFactory.decodeFile(path.toString());
                Matrix mat = new Matrix();
                mat.postRotate(90);
                Bitmap bMapRotate = Bitmap.createBitmap(bitmap, 0, 0,
                        bitmap.getWidth(), bitmap.getHeight(), mat, true);

                FileOutputStream out = null;
                bitmap.recycle();
                try {
                    out = new FileOutputStream(path.toString());
                    bMapRotate.compress(Bitmap.CompressFormat.PNG, 100, out);
                    bMapRotate.recycle();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            count++;
        }
    }
    private Uri[] getPhotoPaths(){
        Cursor cc = null;
        Uri[] mUrls = null;
        String[] strUrls = null;
        String[] mNames = null;

        cc = this.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, null, null,null);
        if (cc != null) {
            try {
                cc.moveToFirst();
                mUrls = new Uri[cc.getCount()];
                strUrls = new String[cc.getCount()];
                mNames = new String[cc.getCount()];
                for (int i = 0; i < cc.getCount(); i++) {
                    cc.moveToPosition(i);
                    mUrls[i] = Uri.parse(cc.getString(1));
                    strUrls[i] = cc.getString(1);
                    mNames[i] = cc.getString(3);
                    Log.d("mNames[i]",mNames[i]+":"+cc.getColumnCount()+ " : " +cc.getString(3));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mUrls;
    }
}
