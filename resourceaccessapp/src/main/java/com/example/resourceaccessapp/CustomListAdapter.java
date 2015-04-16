package com.example.resourceaccessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Simon on 12/01/2015.
 */
public class CustomListAdapter extends ArrayAdapter<String> {
    public CustomListAdapter(Context context, List<String> contacts) {
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
