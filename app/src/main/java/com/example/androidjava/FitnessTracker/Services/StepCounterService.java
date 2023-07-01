package com.example.androidjava.FitnessTracker.Services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;

import java.text.DecimalFormat;
import java.util.concurrent.CountDownLatch;
import android.preference.PreferenceManager;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.androidjava.FitnessTracker.Models.Room.DayDataDao;
import com.example.androidjava.FitnessTracker.Models.Room.DayDataDatabase;
import com.example.androidjava.FitnessTracker.Models.UserProfile;
import com.example.androidjava.FitnessTracker.Models.Room.DayData;
import com.example.androidjava.FitnessTracker.Viewmodels.UserProfileViewModel;

import java.util.Calendar;
import java.util.Date;

public class StepCounterService extends Service implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    private int stepCount;

    private double distance;

    private int caloriesBurned;
    private DayData dayData;
    private UserProfile userProfile;
    private UserProfileViewModel userProfileViewModel;

    private DayDataDatabase db;

    private SharedPreferences sharedPreferences;

    private int stepCountOffset;

    private int totalSteps;
    private boolean resetStepCount = false;


    @Override
    public void onCreate() {
        super.onCreate();

        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);;
        stepCountOffset = sharedPreferences.getInt("stepCountOffset", 0);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_UI);
        }

        IntentFilter intentFilter = new IntentFilter("com.example.androidjava.FitnessTracker.RESET_STEP_COUNT");
        registerReceiver(resetReceiver, intentFilter);

        // Get the database instance
        db = DayDataDatabase.getDatabase(getApplicationContext());


        // Initialize DayData and UserProfile
        int steps = sharedPreferences.getInt("steps", 0);
        double distance = Double.longBitsToDouble(sharedPreferences.getLong("distance", 0L));
        int calories = sharedPreferences.getInt("calories", 0);
        long datum = sharedPreferences.getLong("datum", new Date().getTime());
        dayData = new DayData(datum, steps, distance, calories);

        userProfileViewModel = new UserProfileViewModel();
        userProfileViewModel.initialize(sharedPreferences);
        userProfile = userProfileViewModel.getUserProfile();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {

            // Calculate & Set steps
            totalSteps = (int) event.values[0];
            stepCount = totalSteps - stepCountOffset;

            // Calculate & Set distance (to 2 dec places)
            double stepLength = userProfile.getHeight() * 0.414;
            distance = (stepCount * stepLength) / 100000;  // in km
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            distance = Double.parseDouble(decimalFormat.format(distance));

            // Calculate & Set calories
            caloriesBurned = (int) Math.round((stepCount / 2000.0) * 0.57 * userProfile.getWeight());

            setDayData(stepCount, distance, caloriesBurned);
            persistData();

            // Check if the day has changed
            if (resetStepCount || !isSameDay(new Date(dayData.getDatum()), new Date())) {
                stepCountOffset = totalSteps;
                resetStepCount = false;

                saveToDatabase();
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


    private BroadcastReceiver resetReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            resetStepCount = true;
            stepCountOffset = totalSteps;
            SharedPreferences.Editor editor = sharedPreferences.edit();

            saveToDatabase();

            editor.putInt("steps", dayData.getSteps());
            editor.putLong("distance", Double.doubleToRawLongBits(dayData.getDistance()));
            editor.putInt("calories", dayData.getCalories());
            editor.putLong("datum", dayData.getDatum());
            editor.putInt("stepCountOffset", stepCountOffset);
            editor.apply();
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

    private void saveToDatabase(){
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            db.dayDataDao().insert(dayData);
            latch.countDown();
        }).start();

        try {
            latch.await();

            // Create a new DayData for the new day
            dayData = new DayData(new Date().getTime(), 0, 0.0, 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void persistData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("steps", stepCount);
        editor.putLong("distance", Double.doubleToRawLongBits(distance));
        editor.putInt("calories", caloriesBurned);
        editor.putInt("stepCountOffset", stepCountOffset);
        editor.putLong("datum", new Date().getTime());
        editor.apply();
    }

    private void setDayData(int stepCount, double distance, int caloriesBurned) {
        dayData.setSteps(stepCount);
        dayData.setDistance(distance);
        dayData.setCalories(caloriesBurned);
    }
}
