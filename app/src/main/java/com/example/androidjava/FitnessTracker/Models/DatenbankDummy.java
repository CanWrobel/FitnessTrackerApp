package com.example.androidjava.FitnessTracker.Models;

import com.example.androidjava.sample.sampleModel.DayDataDao;

public class DatenbankDummy implements Datenbank{

    @Override
    public void sendFitnessData(DayDataMessage message) {

    }

    @Override
    public DayDataMessage fetchFitnessData() {
//        DayDataMessage message1 = new DayDataMessage(88, "2021-3-20", 88,88);
//        DayDataMessage message2 = new DayDataMessage(88, "2021-3-20", 88,88);
//        DayDataMessage message3 = new DayDataMessage(88, "2021-3-20", 88,88);



        return null;
    }

    @Override
    public int testConnection() {
        return 0;
    }
}
