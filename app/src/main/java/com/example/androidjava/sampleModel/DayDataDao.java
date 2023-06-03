package com.example.androidjava.sampleModel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface DayDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DayData dayData);

    @Query("SELECT * FROM day_data WHERE date = :date")
    LiveData<DayData> loadDayDataByDate(String date);
}

