package com.tp702_04.apps.project702;

import android.app.ActionBar;
import android.app.Activity;

import android.content.Context;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

import android.os.PowerManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements SensorEventListener {

    protected ExpandableLogListAdapter expandableLogListAdapter;
    protected SwipeRefreshLayout swipeRefreshLayout;
    private DatabaseHandler databaseHandler;

    //accelerometer variables
//    private SensorManager accelManager;
//    private Sensor accelerometer;
//    private final float NOISE = (float) 0.25;
//    private float mLastX, mLastY, mLastZ;
//    private boolean mInitialized;

//    private SensorManager lightManager;
//    private Sensor light;
//    TextView textLight;

//    private SensorManager proximityManager;
//    private Sensor proximity;

    private PowerManager powerManager;

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


        final ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandable_list_view);
        expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        expandableLogListAdapter = new ExpandableLogListAdapter(this);
        expandableListView.setAdapter(expandableLogListAdapter);

        Intent testIntent = new Intent(this, DetectionService.class);
        testIntent.putExtra("resource_accessed_name", "Resource Accessed");
        testIntent.putExtra("app_name", "App Name");
        testIntent.putExtra("date", "Date");
        testIntent.putExtra("time", "Time");
        testIntent.putExtra("tag_message", "Message");
        startService(testIntent);

        databaseHandler = new DatabaseHandler(this);


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

        //initializing the accelerometer variables
//        mInitialized = false;
//        accelManager = (SensorManager)getSystemService(SENSOR_SERVICE);
//        accelerometer = accelManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        accelManager.registerListener(this, accelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        //initiliazing the light variables
//        lightManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        light = lightManager.getDefaultSensor(Sensor.TYPE_LIGHT);
//        lightManager.registerListener(this, light , SensorManager.SENSOR_DELAY_NORMAL);
//        textLight = (TextView) findViewById(R.id.textLight);

        //initializing the proximity variables
//        proximityManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        proximity = proximityManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
//        proximityManager.registerListener(this, proximity , SensorManager.SENSOR_DELAY_NORMAL);

        //initializing the power manager to check screen status
        powerManager = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        isScreenDisplayOn();
    }

    //display method to tell us if the device is interactive or not
    public void isScreenDisplayOn() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            Boolean interactive;
            if (powerManager.isInteractive() == true) {
                interactive = true;
                TextView screenStatus = (TextView) findViewById(R.id.screen);
                screenStatus.setText("Device is interactive");

            } else {
                interactive = false;
                TextView screenStatus = (TextView) findViewById(R.id.screen);
                screenStatus.setText("Device is not interactive");
            }
        }
    }

    // these methods (Resume, Pause, SensorChanged, AccuracyChanged) are listening for sensor events
    @Override
    public void onResume() {
        super.onResume();
//        accelManager.registerListener(this, accelerometer,
//                SensorManager.SENSOR_DELAY_NORMAL);

//        lightManager.registerListener(this, light,
//                SensorManager.SENSOR_DELAY_NORMAL);

//        proximityManager.registerListener(this, proximity,
//                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        accelManager.unregisterListener(this);
//        lightManager.unregisterListener(this);
//        proximityManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

//        if (accelerometer.getType() == Sensor.TYPE_ACCELEROMETER) {
//            TextView tvX = (TextView) findViewById(R.id.x_axis);
//            TextView tvY = (TextView) findViewById(R.id.y_axis);
//            TextView tvZ = (TextView) findViewById(R.id.z_axis);
//
//            float x = event.values[0];
//            float y = event.values[1];
//            float z = event.values[2];
//            if (!mInitialized) {
//                mLastX = x;
//                mLastY = y;
//                mLastZ = z;
//                tvX.setText("0.0");
//                tvY.setText("0.0");
//                tvZ.setText("0.0");
//                mInitialized = true;
//            } else {
//                float deltaX = Math.abs(mLastX - x);
//                float deltaY = Math.abs(mLastY - y);
//                float deltaZ = Math.abs(mLastZ - z);
//                if (deltaX < NOISE) deltaX = (float) 0.0;
//                if (deltaY < NOISE) deltaY = (float) 0.0;
//                if (deltaZ < NOISE) deltaZ = (float) 0.0;
//                mLastX = x;
//                mLastY = y;
//                mLastZ = z;
//                tvX.setText(Float.toString(deltaX));
//                tvY.setText(Float.toString(deltaY));
//                tvZ.setText(Float.toString(deltaZ));
//            }
//            }else if (light.getType() == Sensor.TYPE_LIGHT) {
//            float lightvalue = event.values[0];
//            textLight.setText((int) lightvalue + " lux");
//           }else if (proximity.getType() == Sensor.TYPE_PROXIMITY) {
//            TextView textP = (TextView) findViewById(R.id.textProximity);
//            String proximityValue = "";
//            float p = event.values[0];
//
//            if (p == 0.0) {
//                proximityValue = "near";
//            } else {
//                proximityValue = "far";
//            }
//            textP.setText(proximityValue);
//        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // required method for accelerometer. Has to exist but can be ignored it terms of its contents.
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
