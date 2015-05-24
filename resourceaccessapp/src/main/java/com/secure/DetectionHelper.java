package com.secure;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.PowerManager;

/**
 * Created by jamesbutler on 25/05/15.
 */
public class DetectionHelper implements SensorEventListener {

    //accelerometer variables
    private SensorManager accelManager;
    private Sensor accelerometer;
    private final float NOISE = (float) 0.25;
    private float mLastX, mLastY, mLastZ;
    private boolean mInitialized;

    private SensorManager lightManager;
    private Sensor light;

    private SensorManager proximityManager;
    private Sensor proximity;
    private PowerManager powerManager;

    public DetectionHelper(Context context) {

        //initializing the accelerometer variables
        mInitialized = false;
        accelManager = (SensorManager)context.getSystemService(context.SENSOR_SERVICE);
        accelerometer = accelManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accelManager.registerListener(this, accelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        //initiliazing the light variables
        lightManager = (SensorManager)context.getSystemService(context.SENSOR_SERVICE);
        light = lightManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        lightManager.registerListener(this, light , SensorManager.SENSOR_DELAY_NORMAL);

        //initializing the proximity variables
        proximityManager = (SensorManager)context.getSystemService(context.SENSOR_SERVICE);
        proximity = proximityManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        proximityManager.registerListener(this, proximity , SensorManager.SENSOR_DELAY_NORMAL);

        //initializing the power manager to check screen status
        powerManager = (PowerManager) context.getSystemService(context.POWER_SERVICE);
        isScreenDisplayOn();
    }

    //display method to tell us if the device is interactive or not
    public void isScreenDisplayOn() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            Boolean interactive;
            if (powerManager.isInteractive() == true) {
                interactive = true;

            } else {
                interactive = false;
            }
        }
    }

    // these methods (Resume, Pause, SensorChanged, AccuracyChanged) are listening for sensor events
    public void onResume() {
        accelManager.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL);

        lightManager.registerListener(this, light,
                SensorManager.SENSOR_DELAY_NORMAL);

        proximityManager.registerListener(this, proximity,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        accelManager.unregisterListener(this);
        lightManager.unregisterListener(this);
        proximityManager.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            if (!mInitialized) {
                mLastX = x;
                mLastY = y;
                mLastZ = z;
                mInitialized = true;
            } else {
                float deltaX = Math.abs(mLastX - x);
                float deltaY = Math.abs(mLastY - y);
                float deltaZ = Math.abs(mLastZ - z);
                if (deltaX < NOISE) deltaX = (float) 0.0;
                if (deltaY < NOISE) deltaY = (float) 0.0;
                if (deltaZ < NOISE) deltaZ = (float) 0.0;
                mLastX = x;
                mLastY = y;
                mLastZ = z;
            }
        } else if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float lightvalue = event.values[0];
        } else if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            String proximityValue = "";
            float p = event.values[0];

            if (p == 0.0) {
                proximityValue = "near";
            } else {
                proximityValue = "far";
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // required method for accelerometer. Has to exist but can be ignored it terms of its contents.
    }
}
