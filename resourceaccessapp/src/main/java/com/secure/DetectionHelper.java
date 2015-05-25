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

    //accelerometer sensor variables
    private SensorManager accelManager;
    private Sensor accelerometer;
    private final float NOISE = (float) 0.25;
    private float mLastX, mLastY, mLastZ;
    private boolean mInitialized;
    private float accelX, accelY, accelZ;

    //light sensor variables
    private SensorManager lightManager;
    private Sensor light;
    private float lightValue;

    //screen display variable
    private PowerManager powerManager;
    private boolean screenInteractive;

    public DetectionHelper(Context context) {

        lightValue = 0;
//        proximityValue = "";

        //initializing the accelerometer variables
        mInitialized = false;
        accelManager = (SensorManager)context.getSystemService(context.SENSOR_SERVICE);
        accelerometer = accelManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accelManager.registerListener(this, accelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        //initiliazing the light variables
        lightManager = (SensorManager)context.getSystemService(context.SENSOR_SERVICE);
        light = lightManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        lightManager.registerListener(this, light , SensorManager.SENSOR_DELAY_NORMAL);

        //initializing the power manager to check screen status
        powerManager = (PowerManager) context.getSystemService(context.POWER_SERVICE);
        isScreenDisplayOn();
    }

    //display method to tell us if the device is interactive or not and checks for the current API version being 20 or above. As the previous method isScreenOn() was deprecated in API versions < 20.
    public void isScreenDisplayOn() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            if (powerManager.isInteractive() == true) {
                screenInteractive = true;

            } else {
                screenInteractive = false;
            }
        }
    }

    // these methods (Resume, Pause, SensorChanged, AccuracyChanged) are listening for sensor events
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

    //this method determines which type of sensor event occurs and stores the values in respective sensor variables
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

                accelX = deltaX;
                accelY = deltaY;
                accelZ = deltaZ;
            }

        } else if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            lightValue = event.values[0];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // required method for sensors. Has to exist but can be ignored it terms of its contents.
    }

    //this method checks for different conditions to be true in order to flag an access as machine access
    public boolean isMachineAccess(){

        boolean machineAccess;

        if(screenInteractive == false) { //screen is switched off
            machineAccess = true;
        }else if (accelX == 0 && accelY == 0 && accelZ ==0) { //phone is completely still
            machineAccess = true;
        }else if (lightValue == 0){ //phone is in complete darkness
            machineAccess = true;
        }else{
            machineAccess = false;
        }

        return machineAccess;
    }
}
