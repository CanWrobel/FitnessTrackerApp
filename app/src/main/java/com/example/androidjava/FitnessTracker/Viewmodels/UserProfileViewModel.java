package com.example.androidjava.FitnessTracker.Viewmodels;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidjava.FitnessTracker.Models.UserProfile;
import com.example.androidjava.FitnessTracker.Models.UserProfileRepository;

public class UserProfileViewModel extends ViewModel {
    private UserProfile userProfile;
    private UserProfileRepository userProfileRepository;
    private MutableLiveData<String> validationMessage = new MutableLiveData<>();

    public LiveData<String> getValidationMessage() {
        return validationMessage;
    }

    public UserProfileViewModel() {
    }

    public void initialize(SharedPreferences sharedPreferences) {
        this.userProfileRepository = UserProfileRepository.getInstance(sharedPreferences);
        loadUserProfile();
    }

    private void loadUserProfile() {
        this.userProfile = userProfileRepository.getUserProfile();
    }

    public void updateUserProfile(String name, int age, int height, double weight, int stepsGoal) {
        userProfile.setName(name);
        userProfile.setAge(age);
        userProfile.setHeight(height);
        userProfile.setWeight(weight);
        userProfile.setStepsGoal(stepsGoal);
        userProfile.setDistanceGoal((float) calculateDistanceGoal(stepsGoal));
        userProfile.setCaloriesGoal(calculateCaloriesGoal(stepsGoal));

        userProfileRepository.saveUserProfile(userProfile);
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public boolean validateName(String name) {
        if (name.isEmpty()) {
            validationMessage.setValue("Name field cannot be empty");
            return false;
        }
        return true;
    }

    public boolean validateAge(String age) {
        if (age.isEmpty()) {
            validationMessage.setValue("Age field cannot be empty");
            return false;
        }

        if (Integer.parseInt(age) == 0 || Integer.parseInt(age) > 101) {
            validationMessage.setValue("Please enter a valid age.");
            return false;
        }
        return true;
    }

    public boolean validateHeight(String height) {
        if (height.isEmpty()) {
            validationMessage.setValue("Height field cannot be empty or less than 140 cm");
            return false;
        }
        if (Integer.parseInt(height) < 100 || Integer.parseInt(height) > 230) {
            validationMessage.setValue("Please enter a valid height.");
            return false;
        }
        return true;
    }

    public boolean validateWeight(String weight) {
        if (weight.isEmpty()) {
            validationMessage.setValue("Weight field cannot be empty or less than 30 kg");
            return false;
        }

        if (Double.parseDouble(weight) < 30 || Double.parseDouble(weight) > 250) {
            validationMessage.setValue("Please enter a valid weight.");
            return false;
        }
        return true;
    }

    public boolean validateStepsGoal(String stepsGoal) {
        if (stepsGoal.isEmpty()) {
            validationMessage.setValue("Goal Steps field cannot be empty or less than 100");
            return false;
        }

        if (Integer.parseInt(stepsGoal) < 100 || Integer.parseInt(stepsGoal) > 50000) {
            validationMessage.setValue("Please enter a valid steps goal.");
            return false;
        }
        return true;
    }

    public void saveFirstRunToRepo(boolean firstRun) {
        this.userProfileRepository.saveFirstRun(firstRun);
    }

    public boolean getFirstRunFromRepo() {
        return this.userProfileRepository.getFirstRun();
    }

    private double calculateDistanceGoal(int stepsGoal) {
        // Calculate & Set distance (to 2 dec places)
        double stepLength = userProfile.getHeight() * 0.414;
        return (stepsGoal * stepLength) / 100000;
    }

    private int calculateCaloriesGoal(int stepsGoal) {
        // Calculate & Set distance (to 2 dec places)
        return (int) Math.round((stepsGoal / 2000.0) * 0.57 * userProfile.getWeight());
    }


}
    /*
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
    }*/


