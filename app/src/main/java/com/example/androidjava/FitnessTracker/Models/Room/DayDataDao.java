package com.example.androidjava.FitnessTracker.Models.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DayDataDao {
    @Insert
    void insert(DayData dayData);

    @Query("SELECT * FROM daydata")
    LiveData<List<DayData>> getAllDayData();

    @Query("SELECT * FROM daydata WHERE datum = :datum")
    DayData getDayDataByDate(Long datum);
}
