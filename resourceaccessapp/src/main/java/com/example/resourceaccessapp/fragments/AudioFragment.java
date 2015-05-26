package com.example.resourceaccessapp.fragments;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.resourceaccessapp.adapters.AudioListAdapter;
import com.example.resourceaccessapp.R;

import java.util.ArrayList;

/**
 * Audio fragment is responsible for querying the MediaStore contentprovider for information about
 * the music on the phone. Two query methods are called. One is for the internal storage and one
 * is for the external storage.
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

    /**
     * Called when the swipe layout is refreshed.
     */
    @Override
    public void onRefresh() {
        audioAdapter.clear();
        ContentResolver cr = getActivity().getContentResolver();

        //Load from internal and external storage.
        queryMediaHelper(cr, MediaStore.Audio.Artists.INTERNAL_CONTENT_URI);
        queryMediaHelper(cr, MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI);

        refreshHint.setVisibility(View.GONE);
        swipeLayout.setRefreshing(false);
    }

    /**
     * Small helper method to query a uri. Adds audio data to the adapter.
     *
     * @param cr        The content resolver to be used
     * @param uri       Uri to be queried.
     */
    private void queryMediaHelper(ContentResolver cr, Uri uri){
        Cursor cur = cr.query(uri,
                null, null, null, null);
        if (cur.getCount() > 0){
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(MediaStore.Audio.Artists._ID));
                String name = cur.getString(cur.getColumnIndex(MediaStore.Audio.Artists.ARTIST));
                audioAdapter.add(name);
            }
        }
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
