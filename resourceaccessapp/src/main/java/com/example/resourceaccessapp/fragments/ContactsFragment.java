package com.example.resourceaccessapp.fragments;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.resourceaccessapp.CustomListAdapter;
import com.example.resourceaccessapp.R;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.ArrayList;

/**
 * Created by Simon on 25/05/2015.
 */
public class ContactsFragment extends Fragment {
    private CustomListAdapter contactsAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_contacts, container, false);


        ButtonRectangle loadContactsButton = (ButtonRectangle) v.findViewById(R.id.loadContactsButton);

        loadContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactsAdapter.clear();
                ContentResolver cr = getActivity().getContentResolver();
                Cursor cur = cr.query(ContactsContract.RawContacts.CONTENT_URI,
                        null, null, null, null);
                if (cur.getCount() > 0) {
                    while (cur.moveToNext()) {
                        String id = cur.getString(cur.getColumnIndex(ContactsContract.RawContacts._ID));
                        String name = cur.getString(cur.getColumnIndex(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY));
                        contactsAdapter.add(name);
                    }
                }
            }
        });

        contactsAdapter = new CustomListAdapter(getActivity(), new ArrayList<String>());

        ListView listView = (ListView) v.findViewById(R.id.contact_list_view);
        listView.setAdapter(contactsAdapter);

        return v;
    }
}
