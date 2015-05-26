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
 * AudioListAdapter is an adapter to link the data we gather from the AudioFragment to the listview
 * so that we can display the data to a user.
 *
 * Currently all that we get is the name of the artist and that's it. This class could be
 * extended in the future by adding song details, artist etc if we wanted more detailed information.
 * The idea is just to perform a data access so this is not neccesary at the moment.
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
