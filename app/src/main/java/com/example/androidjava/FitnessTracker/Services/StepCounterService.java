package com.example.androidjava.FitnessTracker.Services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;

import com.example.androidjava.FitnessTracker.Viewmodels.StepCounterManager;

public class StepCounterService extends Service implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    private StepCounterManager stepCounterManager;

    @Override
    public void onCreate() {
        super.onCreate();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_UI);
        }

        IntentFilter intentFilter = new IntentFilter("com.example.androidjava.FitnessTracker.RESET_STEP_COUNT");
        registerReceiver(resetReceiver, intentFilter);

        stepCounterManager = new StepCounterManager(getApplicationContext(), getSharedPreferences("data", Context.MODE_PRIVATE));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            int totalSteps = (int) event.values[0];
            stepCounterManager.onStepCountChanged(totalSteps);

            Intent intent = new Intent();
            intent.setAction("com.example.androidjava.FitnessTracker.STEP_COUNT");
            intent.putExtra("dayData", stepCounterManager.getDayData());
            sendBroadcast(intent);
        }
    }

    private BroadcastReceiver resetReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            stepCounterManager.resetStepCount();
        }
    };

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not needed for this application
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(resetReceiver);
    }
}
