package com.example.androidjava.FitnessTracker.Models;

import android.content.SharedPreferences;

public class UserProfileRepository {
    private static UserProfileRepository instance;
    private final SharedPreferences sharedPreferences;

    private UserProfileRepository(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public static synchronized UserProfileRepository getInstance(SharedPreferences sharedPreferences) {
        if (instance == null) {
            instance = new UserProfileRepository(sharedPreferences);
        }
        return instance;
    }

    public UserProfile getUserProfile() {
        String name = sharedPreferences.getString("name", "");
        int age = sharedPreferences.getInt("age", 0);
        int height = sharedPreferences.getInt("height", 0);
        double weight = sharedPreferences.getFloat("weight", 0);
        int stepsGoal = sharedPreferences.getInt("stepsGoal", 0);

        return new UserProfile(name, age, height, weight, stepsGoal);
    }

    public void saveUserProfile(UserProfile userProfile) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", userProfile.getName());
        editor.putInt("age", userProfile.getAge());
        editor.putInt("height", userProfile.getHeight());
        editor.putFloat("weight", (float) userProfile.getWeight());
        editor.putInt("stepsGoal", userProfile.getStepsGoal());
        editor.putFloat("distanceGoal", userProfile.getDistanceGoal());
        editor.putInt("caloriesGoal", userProfile.getCaloriesGoal());
        editor.apply();
    }

    public void saveFirstRun(boolean firstRun) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isFirstRun", firstRun);
        editor.apply();
    }

    public boolean getFirstRun() {
        return sharedPreferences.getBoolean("isFirstRun", true);
    }
}
