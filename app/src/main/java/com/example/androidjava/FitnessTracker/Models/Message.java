package com.example.androidjava.FitnessTracker.Models;

import java.util.Date;

public class Message {
    int Steps;
    Date datum;
    double distance;
    double calories;

    public Message(int steps, Date datum, double distance, double calories) {
        Steps = steps;
        this.datum = datum;
        this.distance = distance;
        this.calories = calories;
    }

    public void setSteps(int steps) {
        Steps = steps;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public int getSteps() {
        return Steps;
    }

    public Date getDatum() {
        return datum;
    }

    public double getDistance() {
        return distance;
    }

    public double getCalories() {
        return calories;
    }
}
