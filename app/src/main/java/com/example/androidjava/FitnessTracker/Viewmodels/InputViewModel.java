package com.example.androidjava.FitnessTracker.Viewmodels;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;

import com.example.androidjava.FitnessTracker.Models.Room.DayData;
import com.example.androidjava.FitnessTracker.Models.Room.DayDataDao;
import com.example.androidjava.FitnessTracker.Models.Room.DayDataDatabase;
import com.example.androidjava.FitnessTracker.Models.UserProfile;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class InputViewModel extends ViewModel {
    UserProfile userProfile;
    SharedPreferences sharedPreferences;
    private DayDataDao dayDataDao;

    public void initialize(Context context, SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        loadUserProfile();
        this.dayDataDao = DayDataDatabase.getDatabase(context).dayDataDao();
    }

    public void insertDayDataToDB(DayData dayData){
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            dayDataDao.insert(dayData);
            latch.countDown();
        }).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loadUserProfile() {
        String name = sharedPreferences.getString("name", "");
        int age = sharedPreferences.getInt("age", 0);
        int height = sharedPreferences.getInt("height", 0);
        double weight = sharedPreferences.getFloat("weight", 0);
        int stepsGoal = sharedPreferences.getInt("stepsGoal", 0);

        this.userProfile = new UserProfile(name, age, height, weight, stepsGoal);
    }

    public DayData createDayBasedOnSteps(Long date, int stepsCount) {
        int calories = calculateCaloriesFromSteps(stepsCount);
        double distance = calculateDistanceFromSteps(stepsCount);
        DayData dayData = new DayData(date, stepsCount, distance, calories);

        return dayData;
    }

    public int calculateCaloriesFromSteps(int stepCount) {
       int calories = (int) Math.round((stepCount / 2000.0) * 0.57 * userProfile.getWeight());

       return calories;
    }

    public double calculateDistanceFromSteps(int stepCount) {
        double stepLength = userProfile.getHeight() * 0.414;
        double distance =  ((stepCount * stepLength) / 100000);
        return distance;
    }

    public static double calculateDistanceFromStepsStatic(int stepCount, double height) {
        double stepLength = height * 0.414;
        double distance =  ((stepCount * stepLength) / 100000);


        return distance;
    }
    public static int calculateCaloriesFromStepsStatic(int stepCount, double weight) {
        int calories = (int) Math.round((stepCount / 2000.0) * 0.57 * weight);

        return calories;
    }
}
