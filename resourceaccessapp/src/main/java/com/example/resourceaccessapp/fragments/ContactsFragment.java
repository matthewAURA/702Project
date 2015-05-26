package com.example.resourceaccessapp.fragments;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Point;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.resourceaccessapp.AccessService;
import com.example.resourceaccessapp.adapters.ContactsListAdapter;
import com.example.resourceaccessapp.R;

import java.util.ArrayList;

/**
 * This fragment loads contacts when you swipe the layout downwards.
 */
public class ContactsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private ContactsListAdapter contactsAdapter;
    private SwipeRefreshLayout swipeLayout;
    private TextView refreshHint;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onRefresh() {
        contactsAdapter.clear();
        ContentResolver cr = getActivity().getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if (cur.getCount() > 0){
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
                contactsAdapter.add(name);
            }
        }
        refreshHint.setVisibility(View.GONE);
        swipeLayout.setRefreshing(false);
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_contacts, container, false);

        contactsAdapter = new ContactsListAdapter(getActivity(), new ArrayList<String>());

        ListView listView = (ListView) v.findViewById(R.id.contact_list_view);
        listView.setAdapter(contactsAdapter);

        swipeLayout = (SwipeRefreshLayout) v.findViewById(R.id.contacts_swipe_refresh_view);
        swipeLayout.setOnRefreshListener(this);

        refreshHint = (TextView) v.findViewById(R.id.contacts_swipe_down_hint);

        return v;
    }
}
