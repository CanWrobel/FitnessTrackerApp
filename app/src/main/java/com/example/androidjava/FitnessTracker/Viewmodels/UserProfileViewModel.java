package com.example.androidjava.FitnessTracker.Viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.androidjava.FitnessTracker.Models.UserProfile;

public class UserProfileViewModel extends ViewModel {
    private UserProfile userProfile;

    public UserProfileViewModel() {
        this.userProfile = new UserProfile();
    }

    public void setName(String name) {
        this.userProfile.setName(name);
    }

    public void setAge(int age) {
        this.userProfile.setAge(age);
    }

    public void setHeight(float height) {
        this.userProfile.setHeight(height);
    }

    public void setWeight(float weight) {
        this.userProfile.setWeight(weight);
    }

    public void setStepsGoal(int stepsGoal) {
        this.userProfile.setStepsGoal(stepsGoal);
    }

    public void setDistanceGoal(float distanceGoal) {
        this.userProfile.setDistanceGoal(distanceGoal);
    }

    public void setCaloriesGoal(int caloriesGoal) {
        this.userProfile.setCaloriesGoal(caloriesGoal);
    }

    public void updateUserProfile(String name, int age, float height, float weight, int stepsGoal, float distanceGoal, int caloriesGoal) {
        this.userProfile.setName(name);
        this.userProfile.setAge(age);
        this.userProfile.setHeight(height);
        this.userProfile.setWeight(weight);
        this.userProfile.setStepsGoal(stepsGoal);
        this.userProfile.setDistanceGoal(distanceGoal);
        this.userProfile.setCaloriesGoal(caloriesGoal);
    }

}

