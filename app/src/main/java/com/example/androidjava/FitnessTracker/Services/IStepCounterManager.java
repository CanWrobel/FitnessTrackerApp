package com.example.androidjava.FitnessTracker.Services;

public interface IStepCounterManager {
    /**
     * Will be called everytime sensor event updated
     * Calculates new current step count, and the corresponding distance and calories
     * saves the data persistent to shared preferences
     * If day changed, save previous to DB and reset values
     * @param totalSteps Total steps retrieved from Sensor Event
     */
    public void onStepCountChanged(int totalSteps);

    /**
     * Saves data of current day to data base and reset values
     */
    public void saveDataAndResetStepCount();
}
