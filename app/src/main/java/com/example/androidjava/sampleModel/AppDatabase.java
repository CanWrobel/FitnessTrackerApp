package com.example.androidjava.sampleModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {DayData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DayDataDao dayDataDao();
}
