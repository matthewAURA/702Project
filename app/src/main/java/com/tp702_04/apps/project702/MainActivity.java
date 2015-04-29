package com.tp702_04.apps.project702;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.List;
import android.util.Log;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Process process = Runtime.getRuntime().exec("logcat -d -v long");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            StringBuilder log = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line);
            }
            TextView tv = (TextView) findViewById(R.id.textView1);
            tv.setText(log.toString());
        } catch (IOException e) {
        }

        DatabaseHandler db = new DatabaseHandler(this);

        /**
         * CRUD Operations
         * */
        // Inserting log items
        Log.d("Insert: ", "Inserting ..");
        db.addLogItem(new LogItem(12, "my photo","maliciousapp", "15-04-2015", "9.15am", "High priority"));
        db.addLogItem(new LogItem(34, "my song", "badapp", "16-04-2015", "7am", "High priority"));
        Log.d("Update: ", "I got here ..");

        // Reading all log items
        Log.d("Reading: ", "Reading all log items..");
        List<LogItem> log_items = db.getAllLogItems();

        for (LogItem cn : log_items) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,App: " + cn.getApp() + " ,Date: " + cn.getDate() + " ,Time: " + cn.getTime() + " ,Tag Message: " + cn.getTagMessage();
            // Writing Log Items to log
            Log.d("Name: ", log);
        }
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
