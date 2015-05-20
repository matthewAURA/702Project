package com.secure;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;

/**
 * Created by matthewdyer on 2/05/15.
 */
public class ContentResolver extends android.content.ContentResolver {
    public ContentResolver(Context context){
        super(context);
        Log.d("SecureActivity", "ContentResolver Constructor");
    }

    public Cursor query2 (Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        Log.d("SecureActivity", "query");
        return super.query(uri,projection,selection,selectionArgs,sortOrder);
    }



}
