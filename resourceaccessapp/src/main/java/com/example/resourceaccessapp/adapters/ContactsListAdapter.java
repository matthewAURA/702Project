package com.example.resourceaccessapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.resourceaccessapp.R;

import java.util.List;

/**
 * ContactsListAdapter simply populates the listview with the data we gather from the
 * ContactsFragment.
 *
 * All that we get at the moment is the name of the contact. This class could show further information
 * and link with the ContactsFragment to allow the app to modify contacts to simulate updates.
 */
public class ContactsListAdapter extends ArrayAdapter<String> {
    public ContactsListAdapter(Context context, List<String> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String contactName = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_item, parent, false);
        TextView contactNameView = (TextView) convertView.findViewById(R.id.contact_name);
        contactNameView.setText(contactName);
        return convertView;
    }

}
