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
        calendar.set(2023, Calendar.JUNE, 12);
        Date date = calendar.getTime();
        DayDataMessage message1 = new DayDataMessage(60, date, 88,88);

        calendar.set(2023, Calendar.JUNE, 13);
        date = calendar.getTime();
        DayDataMessage message2 = new DayDataMessage(60, date, 88,88);

        calendar.set(2023, Calendar.JUNE, 14);
        date = calendar.getTime();
        DayDataMessage message3 = new DayDataMessage(60, date, 88,88);

        calendar.set(2023, Calendar.JUNE, 15);
        date = calendar.getTime();
        DayDataMessage message4 = new DayDataMessage(10, date, 88,88);

        calendar.set(2023, Calendar.JUNE, 16);
        date = calendar.getTime();
        DayDataMessage message5 = new DayDataMessage(200, date, 88,88);

        calendar.set(2023, Calendar.JUNE, 17);
        date = calendar.getTime();
        DayDataMessage message6 = new DayDataMessage(100, date, 88,88);

        calendar.set(2023, Calendar.JUNE, 18);
        date = calendar.getTime();
        DayDataMessage message7 = new DayDataMessage(100, date, 0.5, 50);

        messages.add(message1);
        messages.add(message2);
        messages.add(message3);
        messages.add(message4);
        messages.add(message5);
        messages.add(message6);
        messages.add(message7);
        return messages;
    }

    @Override
    public int testConnection() {
        return 0;
    }
}
