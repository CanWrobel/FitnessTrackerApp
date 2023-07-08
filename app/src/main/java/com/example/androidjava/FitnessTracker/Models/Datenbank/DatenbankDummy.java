package com.example.androidjava.FitnessTracker.Models.Datenbank;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.androidjava.FitnessTracker.Viewmodels.InputViewModel;

public class DatenbankDummy implements Datenbank {

    List<DayDataMessage> database = new ArrayList<>();

    @Override
    public void sendFitnessData(DayDataMessage message) {

    }

    @Override
    public DayDataMessage fetchFitnessData(Date datum) {
        /**
//        DayDataMessage message1 = new DayDataMessage(88, "2021-3-20", 88,88);
//        DayDataMessage message2 = new DayDataMessage(88, "2021-3-20", 88,88);
//        DayDataMessage message3 = new DayDataMessage(88, "2021-3-20", 88,88);
         **/


        return null;
    }

    public static List<DayDataMessage> fetchAllFitnessData() {
        List<DayDataMessage> messages = new ArrayList<>();

        int steps = 1000;
        double height = 169;
        double weight = 75;
        int calories = InputViewModel.calculateCaloriesFromStepsStatic(steps,weight);
        double distance = InputViewModel.calculateDistanceFromStepsStatic(steps,height);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JUNE, 30);
        Date date = calendar.getTime();
        DayDataMessage message1 = new DayDataMessage(steps, date, distance,calories);
        steps = 5000;
        calories = InputViewModel.calculateCaloriesFromStepsStatic(steps,weight);
        distance = InputViewModel.calculateDistanceFromStepsStatic(steps,height);

        calendar.set(2023, Calendar.JUNE, 29);
        date = calendar.getTime();
        DayDataMessage message2 = new DayDataMessage(steps, date, distance,calories);
        steps = 5500;
        calories = InputViewModel.calculateCaloriesFromStepsStatic(steps,weight);
        distance = InputViewModel.calculateDistanceFromStepsStatic(steps,height);
        calendar.set(2023, Calendar.JUNE, 28);
        date = calendar.getTime();
        DayDataMessage message3 = new DayDataMessage(steps, date, distance,calories);
        steps = 3000;
        calories = InputViewModel.calculateCaloriesFromStepsStatic(steps,weight);
        distance = InputViewModel.calculateDistanceFromStepsStatic(steps,height);
        calendar.set(2023, Calendar.JUNE, 27);
        date = calendar.getTime();
        DayDataMessage message4 = new DayDataMessage(steps, date, distance,calories);
        steps = 8900;
        calories = InputViewModel.calculateCaloriesFromStepsStatic(steps,weight);
        distance = InputViewModel.calculateDistanceFromStepsStatic(steps,height);
        calendar.set(2023, Calendar.JUNE, 26);
        date = calendar.getTime();
        DayDataMessage message5 = new DayDataMessage(steps, date, distance,calories);
        steps = 8000;
        calories = InputViewModel.calculateCaloriesFromStepsStatic(steps,weight);
        distance = InputViewModel.calculateDistanceFromStepsStatic(steps,height);
        calendar.set(2023, Calendar.JUNE, 25);
        date = calendar.getTime();
        DayDataMessage message6 = new DayDataMessage(steps, date, distance,calories);
        steps = 8800;
        calories = InputViewModel.calculateCaloriesFromStepsStatic(steps,weight);
        distance = InputViewModel.calculateDistanceFromStepsStatic(steps,height);
        calendar.set(2023, Calendar.JUNE, 24);
        date = calendar.getTime();
        DayDataMessage message7 = new DayDataMessage(steps, date, distance,calories);
        steps = 8700;
        calories = InputViewModel.calculateCaloriesFromStepsStatic(steps,weight);
        distance = InputViewModel.calculateDistanceFromStepsStatic(steps,height);
        calendar.set(2023, Calendar.JUNE, 23);
        date = calendar.getTime();
        DayDataMessage message8 = new DayDataMessage(steps, date, distance,calories);




        messages.add(message1);
        messages.add(message2);
        messages.add(message3);
        messages.add(message4);
        messages.add(message5);
        messages.add(message6);
        messages.add(message7);
        messages.add(message8);

        return messages;
    }

    @Override
    public int testConnection() {
        return 0;
    }
}
