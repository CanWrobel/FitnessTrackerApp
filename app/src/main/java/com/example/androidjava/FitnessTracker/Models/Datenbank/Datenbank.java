package com.example.androidjava.FitnessTracker.Models.Datenbank;

import java.util.Date;
import java.util.List;

public interface Datenbank {
    /**
     * sendet eine volle Zeile der fitnesstrackernachricht.
     * message enthaelt datum, steps, distanz und kalorien
     * @param message wird bei submit bzw senden uebermittelt
     */
    void sendFitnessData(DayDataMessage message);

    /**
     * fetches the fitnessdata from the databse
     * @param datum In welchem Datum
     * @return message that contains date, steps, distance,
     */
    DayDataMessage fetchFitnessData(Date datum);

    /**
     * fetches all inputs of DayData from the database
     *
     * @return list of all day data inputs
     */
    static List<DayDataMessage> fetchAllFitnessData() {
        return null;
    }


    /*
    schaut bei einer bestimmten Zelle nach, was da drin steht als connection test
    nur in der implementierungsphase
     */
    int testConnection();
}
