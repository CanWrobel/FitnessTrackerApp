package com.example.androidjava.FitnessTracker.Models.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Data Access Object (DAO) for the DayData entity.
 * This interface represents the API for accessing DayData objects from the database.
 */
@Dao
public interface DayDataDao {

    /**
     * Insert a new DayData into the database.
     *
     * @param dayData The DayData object to be inserted.
     */
    @Insert
    void insert(DayData dayData);

    /**
     * Retrieve all DayData objects from the database.
     *
     * @return LiveData object containing a list of all DayData objects.
     */
    @Query("SELECT * FROM daydata")
    LiveData<List<DayData>> getAllDayData();

    /**
     * Retrieve all DayData objects from the database in form of a List.
     *
     * @return List of all DayData objects.
     */
    @Query("SELECT * FROM daydata")
    List<DayData> getAllDayDataList();

    /**
     * Retrieve a specific DayData object by its date from the database.
     * @param datum Date
     * @return DayData of the date
     */
    @Query("SELECT * FROM daydata WHERE datum = :datum")
    DayData getDayDataByDate(Long datum);

    /**
     * Delete all DayData from database
     */
    @Query("DELETE FROM daydata")
    void deleteAllDayData();
}
