package com.example.androidjava.FitnessTracker.Viewmodels;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.androidjava.FitnessTracker.Models.Room.DayData;
import com.example.androidjava.FitnessTracker.Models.Room.DayDataDao;
import com.example.androidjava.FitnessTracker.Models.Room.DayDataDatabase;
import com.example.androidjava.FitnessTracker.Models.UserProfile;
import com.example.androidjava.FitnessTracker.Viewmodels.UserProfileViewModel;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class StepCounterManager {
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

    public StepCounterManager(Context context, SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;

        // Get the database instance
        db = DayDataDatabase.getDatabase(context);

        // Initialize DayData and UserProfile
        int steps = sharedPreferences.getInt("steps", 0);
        double distance = Double.longBitsToDouble(sharedPreferences.getLong("distance", 0L));
        int calories = sharedPreferences.getInt("calories", 0);
        long datum = sharedPreferences.getLong("datum", new Date().getTime());
        dayData = new DayData(datum, steps, distance, calories);

        stepCountOffset = sharedPreferences.getInt("stepCountOffset", 0);
        userProfileViewModel = new UserProfileViewModel();
        userProfileViewModel.initialize(sharedPreferences);
        userProfile = userProfileViewModel.getUserProfile();
    }

    public void onStepCountChanged(int totalSteps) {
        this.totalSteps = totalSteps;
        // Calculate & Set steps
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
        if (!isSameDay(new Date(dayData.getDatum()), new Date())) {
            stepCountOffset = totalSteps;
            saveToDatabase();
        }
    }

    public void resetStepCount() {
        stepCountOffset = totalSteps;

        saveToDatabase();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("steps", dayData.getSteps());
        editor.putLong("distance", Double.doubleToRawLongBits(dayData.getDistance()));
        editor.putInt("calories", dayData.getCalories());
        editor.putLong("datum", dayData.getDatum());
        editor.putInt("stepCountOffset", stepCountOffset);
        editor.apply();
    }

    private static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
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

    public DayData getDayData() {
        return dayData;
    }
}
