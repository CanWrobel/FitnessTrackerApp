package com.example.androidjava.FitnessTracker.Models;

public interface Datenbank {
    /**
     * sendet eine volle Zeile der fitnesstrackernachricht.
     * message enthaelt datum, steps, distanz und kalorien
     * @param message wird bei submit bzw senden uebermittelt
     */
    void sendFitnessData(Message message);

    /**
     * fetches the fitnessdata from the databse
     * @return message that contains date, steps, distance,
     */
    Message fetchFitnessData();
    /*
    schaut bei einer bestimmten Zelle nach, was da drin steht als connection test
    nur in der implementierungsphase
     */
    int testConnection();
}
