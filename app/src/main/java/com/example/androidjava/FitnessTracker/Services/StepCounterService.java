package com.example.androidjava.FitnessTracker.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;

public class StepCounterService extends Service implements SensorEventListener {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        //TODO
        // Setup Step Counter
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        return START_STICKY;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //TODO
        // Record Step Count
        //Preferences.setStepCount(this, (int) event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void updateNotification(boolean firstTime) {

        //TODO
        // Update Step Count
        //mBuilder.setContentTitle(Preferences.getStepCount(this) + " steps taken");

    }


}
