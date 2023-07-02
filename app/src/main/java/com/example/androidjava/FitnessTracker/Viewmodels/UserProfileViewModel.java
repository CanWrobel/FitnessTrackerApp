package com.example.androidjava.FitnessTracker.Viewmodels;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;

import com.example.androidjava.FitnessTracker.Models.UserProfile;

public class UserProfileViewModel extends ViewModel {
    private UserProfile userProfile;
    private SharedPreferences sharedPreferences;

    public UserProfileViewModel() {
    }

    private void loadUserProfile() {
        String name = sharedPreferences.getString("name", "");
        int age = sharedPreferences.getInt("age", 0);
        int height = sharedPreferences.getInt("height", 0);
        double weight = sharedPreferences.getFloat("weight", 0);
        int stepsGoal = sharedPreferences.getInt("stepsGoal", 0);

        this.userProfile = new UserProfile(name, age, height, weight, stepsGoal);
    }

    private void saveUserProfile() {
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

    public void updateUserProfile(String name, int age, int height, double weight, int stepsGoal) {
        setName(name);
        setAge(age);
        setHeight(height);
        setWeight(weight);
        setStepsGoal(stepsGoal);
        setCaloriesGoal(stepsGoal);
        setDistanceGoal(stepsGoal);

        saveUserProfile();
    }

    public void initialize(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        loadUserProfile();
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setName(String name) {
        this.userProfile.setName(name);
    }

    public void setAge(int age) {
        this.userProfile.setAge(age);
    }

    public void setHeight(int height) {
        this.userProfile.setHeight(height);
    }

    public void setWeight(double weight) {
        this.userProfile.setWeight(weight);
    }

    public void setStepsGoal(int stepsGoal) {
        this.userProfile.setStepsGoal(stepsGoal);
    }

    public void setDistanceGoal(int stepsGoal) {
        // Calculate & Set distance (to 2 dec places)
        double stepLength = userProfile.getHeight() * 0.414;
        float distanceGoal = (float) ((userProfile.getStepsGoal() * stepLength) / 100000);

        this.userProfile.setDistanceGoal(distanceGoal);
    }

    public void setCaloriesGoal(int stepsGoal) {
        int calorieGoal = (int) Math.round((userProfile.getStepsGoal() / 2000.0) * 0.57
                * userProfile.getWeight());// Calculate & Set distance (to 2 dec places)
        this.userProfile.setCaloriesGoal(calorieGoal);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
