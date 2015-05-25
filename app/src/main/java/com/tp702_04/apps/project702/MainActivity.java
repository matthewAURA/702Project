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
 * The main Viewer App activity. This activity initially displays an info screen on how
 * to use the app. However, once the user swipes the screen a list of all the current recorded
 * resource accesses is displayed and colour coded to represent either human or machine access.
 */
public class MainActivity extends Activity {

    protected ResourceAccessListAdapter resourceAccessListAdapter;
    protected SwipeRefreshLayout swipeRefreshLayout;
    private DatabaseHandler databaseHandler;

    /**
     * An inner class to handle database reading task off the main UI thread.
     */
    private class ReadDatabaseTask extends AsyncTask<DatabaseHandler, Void, List<ResourceAccessItem>> {

        // The database read itself, handled in the background.
        @Override
        protected List<ResourceAccessItem> doInBackground(DatabaseHandler... params) {
            return params[0].getAllResourceAccessItems();
        }

        // Once the read is complete, the expandable list is populated and notified of the data change.
        @Override
        protected void onPostExecute(List<ResourceAccessItem> resourceAccessItems) {
            resourceAccessListAdapter.clear();
            resourceAccessListAdapter.addAll((ArrayList<ResourceAccessItem>) resourceAccessItems);
            resourceAccessListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the expandable list view for displaying the resource accesses.
        final ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandable_list_view);
        expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        // Set up the List Adapter to manage the Resource Access data
        resourceAccessListAdapter = new ResourceAccessListAdapter(this);
        expandableListView.setAdapter(resourceAccessListAdapter);

        databaseHandler = new DatabaseHandler(this);

        // Set up swipe to refresh functionality
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

        databaseHandler.addResourceAccessItem(new ResourceAccessItem("YO", "YO", "YO", "YO", "YO", "true"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
