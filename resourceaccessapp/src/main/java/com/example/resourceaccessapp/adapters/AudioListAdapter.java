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
 * Created by Simon on 12/01/2015.
 */
public class AudioListAdapter extends ArrayAdapter<String> {
    public AudioListAdapter(Context context, List<String> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String audioName = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.audio_item, parent, false);
        TextView audioNameView = (TextView) convertView.findViewById(R.id.audio_name);
        audioNameView.setText(audioName);
        return convertView;
    }

}
