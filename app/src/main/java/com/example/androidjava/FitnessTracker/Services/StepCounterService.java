package com.example.androidjava.FitnessTracker.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.preference.PreferenceManager;

import androidx.room.Room;

import com.example.androidjava.FitnessTracker.Models.Room.DayDataDao;
import com.example.androidjava.FitnessTracker.Models.Room.DayDataDatabase;
import com.example.androidjava.FitnessTracker.Models.UserProfile;
import com.example.androidjava.FitnessTracker.Models.Room.DayData;

import java.util.Calendar;
import java.util.Date;

public class StepCounterService extends Service implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    private int stepCount = 0;

    private double distance;

    private int caloriesBurned;
    private DayData dayData;
    private UserProfile userProfile;

    private DayDataDatabase db;

    private SharedPreferences sharedPreferences;


    @Override
    public void onCreate() {
        super.onCreate();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_UI);
        }

        // Get the database instance
        db = DayDataDatabase.getDatabase(getApplicationContext());

        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);


        // Initialize DayData and UserProfile
        int steps = sharedPreferences.getInt("steps", 0);
        double distance = Double.longBitsToDouble(sharedPreferences.getLong("distance", 0L));
        int calories = sharedPreferences.getInt("calories", 0);
        long datum = sharedPreferences.getLong("datum", new Date().getTime());
        dayData = new DayData(datum, steps, distance, calories);

        userProfile = new UserProfile(sharedPreferences);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {

            stepCount = (int) event.values[0];
            dayData.setSteps(stepCount);

            // Calculate distance
            double stepLength = userProfile.getHeight() * 0.414;
            distance = (stepCount * stepLength) / 100000;  // in km
            dayData.setDistance(distance);

            // Calculate calories
            caloriesBurned = (int) Math.round((stepCount / 2000.0) * 0.57 * userProfile.getWeight());
            dayData.setCalories(caloriesBurned);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("steps", stepCount);
            editor.putLong("distance", Double.doubleToRawLongBits(distance));
            editor.putInt("calories", caloriesBurned);
            editor.apply();

            // Get current date
            Date now = new Date();

            // Check if the day has changed
            if (!isSameDay(new Date(dayData.getDatum()), now)) {
                // If the day has changed, save the previous day's data to the database
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db.dayDataDao().insert(dayData);
                    }
                }).start();

                // Create a new DayData for the new day
                dayData = new DayData(now.getTime(), 0, 0.0, 0);
            }

            Intent intent = new Intent();
            intent.setAction("com.example.androidjava.FitnessTracker.STEP_COUNT");
            intent.putExtra("dayData", dayData);
            sendBroadcast(intent);
        }
    }

    private static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not needed for this application
    }
}
