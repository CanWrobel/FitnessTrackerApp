package com.example.androidjava.FitnessTracker.Models;


public interface IUserProfileRepository {

    /**
     * Gets a UserProfile object based on attributes
     * from sharedpreferences
     * @return UserProfile
     */
    UserProfile getUserProfile();

    /**
     * Saves a UserProfile to sharedpreferences
     * @param userProfile UserProfile object.
     */
    void saveUserProfile(UserProfile userProfile);

    /**
     * Saves first run condition to shared preferences
     * @param firstRun true if first ever time opening the app (for setup)
     */
    void saveFirstRun(boolean firstRun);

    /**
     * get first run condition from shared preferences
     * @return true if first ever opening the app
     */
    boolean getFirstRun();
}

