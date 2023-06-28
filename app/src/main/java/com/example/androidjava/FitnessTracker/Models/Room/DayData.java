package com.example.androidjava.FitnessTracker.Models.Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;

@Entity
public class DayData implements Serializable {
    @PrimaryKey
    private Long datum; // replaced Date with Long
    private int Steps;
    private double distance;
    private int calories;

    public DayData(Long datum, int steps, double distance, int calories) {
        setDatum(datum);
        setSteps(steps);
        setDistance(distance);
        setCalories(calories);
    }

    public DayData() {
        setDatum(0L);
        setSteps(0);
        setDistance(0);
        setCalories(0);
    }

    public Long getDatum() {
        return datum;
    }

    public void setDatum(Long datum) {
        this.datum = datum;
    }

    public int getSteps() {
        return Steps;
    }

    public void setSteps(int steps) {
        Steps = steps;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}

