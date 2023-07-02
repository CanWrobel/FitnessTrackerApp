package com.example.androidjava.FitnessTracker.Viewmodels;

import android.content.SharedPreferences;

import com.example.androidjava.FitnessTracker.Models.Room.DayData;
import com.example.androidjava.FitnessTracker.Models.UserProfile;

import java.util.Date;

public class InputViewModel {
    UserProfile userProfile;
    SharedPreferences sharedPreferences;

    public void initialize(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        loadUserProfile();
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
