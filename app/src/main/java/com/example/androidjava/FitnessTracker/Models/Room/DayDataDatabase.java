package com.example.androidjava.FitnessTracker.Models.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.Date;

@Database(entities = {DayData.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class DayDataDatabase extends RoomDatabase {
    public abstract DayDataDao dayDataDao();

    private static volatile DayDataDatabase INSTANCE;

    public static DayDataDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DayDataDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DayDataDatabase.class, "day_data_database")
                            .build();
                    //TODO: hack
                    INSTANCE.insertTestData();
                }
            }
        }

        return INSTANCE;
    }

    public static void setInstance(DayDataDatabase instance) {
        INSTANCE = instance;
    }

    public void insertTestData() {
        new Thread(() -> {
            DayData dayData1 = new DayData(System.currentTimeMillis() - 24*60*60*1000, 1000, 5.0, 500);
            DayData dayData2 = new DayData(System.currentTimeMillis() - 48*60*60*1000, 2000, 7.0, 700);
            dayDataDao().insert(dayData1);
            dayDataDao().insert(dayData2);
        }).start();
    }


}

