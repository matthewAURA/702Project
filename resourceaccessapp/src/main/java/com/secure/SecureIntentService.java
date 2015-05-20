package com.secure;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

import com.google.dexmaker.BinaryOp;
import com.google.dexmaker.Code;
import com.google.dexmaker.DexMaker;
import com.google.dexmaker.FieldId;
import com.google.dexmaker.Local;
import com.google.dexmaker.MethodId;
import com.google.dexmaker.TypeId;
import com.google.dexmaker.stock.ProxyBuilder;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Created by matthewdyer on 14/05/15.
 */
public abstract class SecureIntentService extends android.app.IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SecureIntentService(String name) {
        super(name);
    }


    protected abstract void onHandleIntent(Intent intent);

    public android.content.ContentResolver getContentResolver(){
        Log.d("SecureActivity", "getContentResolver");
        generateProxies();
        return super.getContentResolver();
    }

    private void generateProxies(){

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Log.d("702 reflection","bar");
                Object result = ProxyBuilder.callSuper(proxy, method, args);
                return result;
            }
        };
        try {
            MyClass debugRandom = ProxyBuilder.forClass(MyClass.class)
                    .dexCache(getApplicationContext().getDir("dx", Context.MODE_PRIVATE))
                    .handler(handler)
                    .build();

            debugRandom.foo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class MyClass {
        public MyClass(){
            super();
        }

        public void foo() {
            Log.d("702 reflection", "foo");
        }
    }



}
