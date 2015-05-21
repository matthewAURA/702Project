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

public class MainActivity extends Activity {

    protected ExpandableLogListAdapter expandableLogListAdapter;
    protected SwipeRefreshLayout swipeRefreshLayout;
    private DatabaseHandler databaseHandler;

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
            //super.onPostExecute(logItems);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandable_list_view);
        expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        databaseHandler = new DatabaseHandler(this);

        expandableLogListAdapter = new ExpandableLogListAdapter(this, new ArrayList<LogItem>());
        expandableListView.setAdapter(expandableLogListAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
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
