package com.tp702_04.apps.project702;

import android.app.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the main activity for our ViewerApp.
 *
 * The purpose of the ViewerApp is to display and manage all the currently recorded resource accesses
 * from the database. The MainActivity has a swipe to refresh functionality to repopulate the displayed
 * list with the latest results from the database. There is also a menu option to clear all current recorded
 * resource accesses from the database.
 *
 */
public class MainActivity extends Activity {

    protected ExpandableLogListAdapter expandableLogListAdapter;
    protected SwipeRefreshLayout swipeRefreshLayout;
    private DatabaseHandler databaseHandler;

    /**
     * A private inner class to handle the asynchronous database read tasks in the background
     */
    private class ReadDatabaseTask extends AsyncTask<DatabaseHandler, Void, List<LogItem>> {
        @Override
        protected List<LogItem> doInBackground(DatabaseHandler... params) {
            return params[0].getAllLogItems();
        }

        @Override
        protected void onPostExecute(List<LogItem> logItems) {
            expandableLogListAdapter.clear();
            expandableLogListAdapter.addAll((ArrayList<LogItem>) logItems);
            expandableLogListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // When a resource access is clicked, expand it to show more info
        final ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandable_list_view);
        expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        expandableLogListAdapter = new ExpandableLogListAdapter(this);
        expandableListView.setAdapter(expandableLogListAdapter);

        databaseHandler = new DatabaseHandler(this);

        // Trigger the swipe to refresh task
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                findViewById(R.id.title).setVisibility(View.GONE);
                findViewById(R.id.instr).setVisibility(View.GONE);
                new ReadDatabaseTask().execute(databaseHandler);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            // Handle the Clear All menu option
            case R.id.clear_all:
                databaseHandler.deleteAllLogItems();
                expandableLogListAdapter.clear();
                expandableLogListAdapter.notifyDataSetChanged();
                findViewById(R.id.title).setVisibility(View.VISIBLE);
                findViewById(R.id.instr).setVisibility(View.VISIBLE);
        }

        return super.onOptionsItemSelected(item);
    }

}
