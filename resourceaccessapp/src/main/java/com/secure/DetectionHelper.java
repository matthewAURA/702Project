package com.secure;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;

/**
 * @Author: Nazish Khan
 * @Since 25/05/2015
 */

/**
 * This class distinguishes between machine and human access based on accelerometer and light sensors, as well as the display being on or off.
 */

public class DetectionHelper implements SensorEventListener {

    private SensorManager accelManager;
    private Sensor accelerometer;
    private final float NOISE = (float) 0.04;
    private float mLastX, mLastY, mLastZ;
    private boolean mInitialized;
    private static float accelX = (float)0.0, accelY = (float)0.0, accelZ = (float)0.0;
    private static boolean hasAccelData = false;

    private SensorManager lightManager;
    private Sensor light;
    private static float lightValue = 0;

    private PowerManager powerManager;
    private boolean screenInteractive;

    public DetectionHelper(Context context) {

        mInitialized = false;
        accelManager = (SensorManager)context.getSystemService(context.SENSOR_SERVICE);
        accelerometer = accelManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accelManager.registerListener(this, accelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        lightManager = (SensorManager)context.getSystemService(context.SENSOR_SERVICE);
        light = lightManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        lightManager.registerListener(this, light , SensorManager.SENSOR_DELAY_NORMAL);

        powerManager = (PowerManager) context.getSystemService(context.POWER_SERVICE);
        isScreenDisplayOn();
    }

    /** Display method to tell us if the device is interactive or not and checks for the current API version being 20 or above. As the previous method isScreenOn() was deprecated in API versions < 20.
     */
    public void isScreenDisplayOn() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            if (powerManager.isInteractive()) {
                screenInteractive = true;
            } else {
                screenInteractive = false;
            }
        }else{
            screenInteractive = powerManager.isScreenOn();
        }
    }

    // These methods (Resume, Pause, SensorChanged, AccuracyChanged) are listening for sensor events
    public void onResume() {
        accelManager.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL);

        lightManager.registerListener(this, light,
                SensorManager.SENSOR_DELAY_NORMAL);

    }

    protected void onPause() {
        accelManager.unregisterListener(this);
        lightManager.unregisterListener(this);
    }

    /** This method determines which type of sensor event occurs and stores the values in respective sensor variables
     * @param event
     */
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            hasAccelData = true;
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

                accelX = deltaX;
                accelY = deltaY;
                accelZ = deltaZ;
            }

        } else if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            lightValue = event.values[0];
        }
    }

    /** Required method for sensors. Has to exist but can be ignored it terms of its contents.
     * @param sensor
     * @param accuracy
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /** This method checks for different conditions to be true in order to flag an access as machine access
     * @return if it is a machine access
     */
    public boolean isMachineAccess(){

        boolean machineAccess;

        if(screenInteractive == false) { //screen is switched off
            machineAccess = true;
        }else if (hasAccelData && accelX == 0 && accelY == 0 && accelZ ==0) { //phone is completely still
            machineAccess = true;
        }else if (lightValue == 0){ //phone is in complete darkness
            machineAccess = true;
        }else{
            machineAccess = false;
        }

        // Check the stack trace for a string containing a private inner class which is only used when responding
        // to a user-triggered TouchEvent
        boolean foundMethod = false;

        for (StackTraceElement e : Thread.currentThread().getStackTrace()) {
            String stackTrace = e.toString();
            if (stackTrace.contains("android.view.View$PerformClick.run")) {
                machineAccess = false;
                foundMethod = true;
            } else if (stackTrace.contains("android.support.v4.widget.SwipeRefreshLayout$1.onAnimationEnd")) {
                machineAccess = false;
                foundMethod = true;
            }
        }

        if (!foundMethod){
            machineAccess = true;
        }

        return machineAccess;
    }
}
