package com.example.androidjava.FitnessTracker.Models;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DatenbankDummy implements Datenbank{

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

        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JUNE, 21);
        Date date = calendar.getTime();
        DayDataMessage message1 = new DayDataMessage(10000, date, 88,88);

        calendar.set(2023, Calendar.JUNE, 22);
        date = calendar.getTime();
        DayDataMessage message2 = new DayDataMessage(10000, date, 88,88);

        calendar.set(2023, Calendar.JUNE, 23);
        date = calendar.getTime();
        DayDataMessage message3 = new DayDataMessage(10000, date, 88,88);

        calendar.set(2023, Calendar.JUNE, 24);
        date = calendar.getTime();
        DayDataMessage message4 = new DayDataMessage(10, date, 88,88);

        calendar.set(2023, Calendar.JUNE, 25);
        date = calendar.getTime();
        DayDataMessage message5 = new DayDataMessage(20000, date, 88,88);

        calendar.set(2023, Calendar.JUNE, 26);
        date = calendar.getTime();
        DayDataMessage message6 = new DayDataMessage(100000, date, 88,88);

        calendar.set(2023, Calendar.JUNE, 27);
        date = calendar.getTime();
        DayDataMessage message7 = new DayDataMessage(10000, date, 0.5, 50);

        calendar.set(2023, Calendar.JUNE, 28);
        date = calendar.getTime();
        DayDataMessage message8 = new DayDataMessage(10000, date, 0.5, 50);

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
