package com.secure;

import android.app.Notification;
import android.content.ContentResolver;
import android.os.Bundle;
import android.util.Log;
import java.lang.reflect.Method;


/**
 * Created by matthewdyer on 2/05/15.
 */
public class ActionBarActivity extends android.support.v7.app.ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.i("SecureActivity", "onCreate");
        super.onCreate(savedInstanceState);

    }

    @Override
    public ContentResolver getContentResolver(){
        Log.i("SecureActivity", "getContentResolver");
        return super.getContentResolver();
    }



}
