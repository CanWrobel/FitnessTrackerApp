package com.example.androidjava.FitnessTracker.Viewmodels;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;

import com.example.androidjava.FitnessTracker.Models.UserProfile;

/**
 * Interface for UserProfileViewModel
 */
public interface IUserProfileViewModel {

    /**
     * Initialize the view model,
     * the given shared preferences will initialize a UserProfileRepository in the view mdoel
     * @param sharedPreferences
     */
    void initialize(SharedPreferences sharedPreferences);

    /**
     * Update user profile data
     * Saves all attributes of UserProfile to the current class, as well as saving to
     * shared preferences
     * @param name
     * @param age
     * @param height
     * @param weight
     * @param stepsGoal
     */
    void updateUserProfile(String name, int age, int height, double weight, int stepsGoal);

    /**
     * Return user profile from instantiated UserProfileRepository
     * @return UserProfile object
     */
    UserProfile getUserProfile();

    /**
     * Validate the provided name string from user input
     * Name cannot be empty
     * @param name
     * @return true if the name is valid, false otherwise
     */
    boolean validateName(String name);

    /**
     * Validate the provided age string from user input
     * Invalid when: Age empty, 0 or > 105
     * @param age
     * @return true if the age is valid, false otherwise
     */
    boolean validateAge(String age);

    /**
     * Validate the provided height string from user input
     * Invalid when: height empty, 0, > 230, < 100
     * @param height
     * @return true if the height is valid, false otherwise
     */
    boolean validateHeight(String height);

    /**
     * Validate the provided weight string from user input
     * Invalid when: weight empty, 0, > 250, <30
     * @param weight
     * @return true if the weight is valid, false otherwise
     */
    boolean validateWeight(String weight);

    /**
     * Validate the provided steps goal string from user input
     * Invalid when: steps goal empty, 0, > 50000, <100
     * @param stepsGoal The steps goal to validate.
     * @return true if the steps goal is valid, false otherwise
     */
    boolean validateStepsGoal(String stepsGoal);

    /**
     * Save whether it is the first run or not to the repository
     * repository will save to sharedpreferences
     * @param firstRun
     */
    void saveFirstRunToRepo(boolean firstRun);

    /**
     * Get the first run status from the repository
     * @return true if it is the first ever time opening the app
     */
    boolean getFirstRunFromRepo();


    /**
     * Get current observable validation message (if any) after validating user profile values
     * @return Validation message
     */
    LiveData<String> getValidationMessage();



}


