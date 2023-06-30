package com.example.androidjava.FitnessTracker.Models;

import static com.mysql.cj.protocol.x.XProtocolDecoder.instance;

import android.content.Context;
import android.content.SharedPreferences;

public class UserProfile {
    private String name = "Reinardus";
    private int age;
    private int height;
    private double weight;
    private int stepsGoal;
    private float distanceGoal;
    private int caloriesGoal;
    private SharedPreferences sharedPreferences;


    public UserProfile(String name, int age, int height, double weight, int stepsGoal) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.stepsGoal = stepsGoal;
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

