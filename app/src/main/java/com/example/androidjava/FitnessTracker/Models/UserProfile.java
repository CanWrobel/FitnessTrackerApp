package com.example.androidjava.FitnessTracker.Models;

import static com.mysql.cj.protocol.x.XProtocolDecoder.instance;

import android.content.Context;
import android.content.SharedPreferences;

public class UserProfile {
    private String name = "Reinardus";
    private int age = 20;
    private int height = 182;
    private double weight = 88;
    private int stepsGoal = 8000;
    private float distanceGoal = 5;
    private int caloriesGoal = 500;
    private SharedPreferences sharedPreferences;


    public UserProfile(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putInt("age", age);
        editor.putInt("height", height);
        editor.putFloat("weight", (float) weight);
        editor.putInt("stepsGoal", stepsGoal);
        editor.putFloat("distanceGoal", distanceGoal);
        editor.putInt("caloriesGoal", caloriesGoal);
        editor.apply();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getStepsGoal() {
        return stepsGoal;
    }

    public void setStepsGoal(int stepsGoal) {
        this.stepsGoal = stepsGoal;
    }

    public float getDistanceGoal() {
        return distanceGoal;
    }

    public void setDistanceGoal(float distanceGoal) {
        this.distanceGoal = distanceGoal;
    }

    public int getCaloriesGoal() {
        return caloriesGoal;
    }

    public void setCaloriesGoal(int caloriesGoal) {
        this.caloriesGoal = caloriesGoal;
    }
}

