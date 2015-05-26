package com.example.resourceaccessapp;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.RemoteException;
import java.util.ArrayList;

import android.content.ContentProviderOperation;
import android.provider.ContactsContract;

/**
 * The AccessService creates an service which will access all the contacts on the phone and "modify"
 * them. It runs until the service is stopped and there is a one second delay between loading
 * the contacts again. We don't actually modify the contacts but the update method for the
 * content provider should still be called since we just update with the same information.
 */
public class AccessService extends IntentService {
    public AccessService(){
        super("AccessService");
    }

    @Override
    protected void onHandleIntent(Intent workIntent){
        while(true){
            synchronized (this) {
                try {
                    wait(1000);
                }catch (InterruptedException e){}
                accessContacts();
            }
        }
    }

    /**
     * Update contact method will take a contactId and name and update the contactid with the new
     * contact name
     * @param contactId     Contact to update
     * @param contactName   New contact name.
     */
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

    /**
     * This method is called to query the contacts and retrieve a list of all the contacts.
     */
    private void accessContacts(){
        Context c = this.getApplicationContext();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String newName;
                newName = name;
                updateContact(id, newName);
            }
        }
    }
}
