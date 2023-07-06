package com.example.androidjava.FitnessTracker.Viewmodels;

import android.content.SharedPreferences;
import com.example.androidjava.FitnessTracker.Models.UserProfile;

/**
 * Interface for User Profile ViewModel.
 */
public interface IUserProfileViewModel {

    /**
     * Load user profile from shared preferences.
     */
    void loadUserProfile();


    /**
     * Update user profile with the provided values.
     *
     * @param name the name of the user.
     * @param age the age of the user.
     * @param height the height of the user.
     * @param weight the weight of the user.
     * @param stepsGoal the step goal of the user.
     */
    void updateUserProfile(String name, int age, int height, double weight, int stepsGoal);

    /**
     * Initialize the ViewModel with shared preferences.
     *
     * @param sharedPreferences the shared preferences.
     */
    void initialize(SharedPreferences sharedPreferences);

    /**
     * Get the user profile.
     *
     * @return the user profile.
     */
    UserProfile getUserProfile();
}

