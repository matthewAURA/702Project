package com.example.resourceaccessapp.fragments;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.resourceaccessapp.adapters.AudioListAdapter;
import com.example.resourceaccessapp.adapters.ContactsListAdapter;
import com.example.resourceaccessapp.R;

import java.util.ArrayList;

/**
 * Created by Simon on 25/05/2015.
 */
public class AudioFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private AudioListAdapter audioAdapter;
    private SwipeRefreshLayout swipeLayout;

    private TextView refreshHint;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onRefresh() {
        audioAdapter.clear();
        ContentResolver cr = getActivity().getContentResolver();

        // First load the internal audio. E.g. Media that is stored on the internal storage.
        Cursor cur = cr.query(MediaStore.Audio.Artists.INTERNAL_CONTENT_URI,
                null, null, null, null);
        if (cur.getCount() > 0){
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(MediaStore.Audio.Artists._ID));
                String name = cur.getString(cur.getColumnIndex(MediaStore.Audio.Artists.ARTIST));
                audioAdapter.add(name);
            }
        }

        //Second load the external audio. E.g. Media that is stored on the SD card.
        cur = cr.query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
                null, null, null, null);
        if (cur.getCount() > 0){
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(MediaStore.Audio.Artists._ID));
                String name = cur.getString(cur.getColumnIndex(MediaStore.Audio.Artists.ARTIST));
                audioAdapter.add(name);
            }
        }

        refreshHint.setVisibility(View.GONE);
        swipeLayout.setRefreshing(false);
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_audio, container, false);

        audioAdapter = new AudioListAdapter(getActivity(), new ArrayList<String>());

        ListView listView = (ListView) v.findViewById(R.id.audio_list_view);
        listView.setAdapter(audioAdapter);

        swipeLayout = (SwipeRefreshLayout) v.findViewById(R.id.audio_swipe_refresh_view);
        swipeLayout.setOnRefreshListener(this);

        refreshHint = (TextView) v.findViewById(R.id.audio_swipe_down_hint);

        return v;
    }
}
