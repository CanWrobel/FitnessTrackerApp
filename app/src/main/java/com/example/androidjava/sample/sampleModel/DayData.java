package com.example.androidjava.sample.sampleModel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "day_data")
public class DayData {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "steps")
    private int steps;

    @ColumnInfo(name = "distance")
    private double distance; // in km

    @ColumnInfo(name = "calories")
    private int calories;

    public DayData(@NonNull String date, int steps, double distance, int calories) {
        this.date = date;
        this.steps = steps;
        this.distance = distance;
        this.calories = calories;
    }


    @NonNull
    public String getDate() {
        return date;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
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

