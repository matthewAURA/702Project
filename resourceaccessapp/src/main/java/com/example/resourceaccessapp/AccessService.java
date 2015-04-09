package com.example.resourceaccessapp;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.FileNotFoundException;

/**
 * Created by Simon on 9/04/2015.
 */
public class AccessService extends IntentService {

    private Cursor cc = null;
    private static Uri[] mUrls = null;
    private static String[] strUrls = null;
    private String[] mNames = null;

    private final long runningTime = 30000;

    public AccessService(){
        super("AccessService");
    }

    @Override
    protected void onHandleIntent(Intent workIntent){
        long endTime = System.currentTimeMillis() + runningTime;
        try {
            Uri[] paths = getPhotoPaths();
            for(;;){
                //Acccess private files and data here
                synchronized (this) {
                    for (Uri path: paths){
                        Log.d("RESOURCE ACCESS APP", path.toString());
                        Bitmap bitmap = BitmapFactory.decodeFile(path.toString());
                        wait(1000);
                    }
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    Uri[] getPhotoPaths(){
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
            }
        }

        return mUrls;
    }
}
