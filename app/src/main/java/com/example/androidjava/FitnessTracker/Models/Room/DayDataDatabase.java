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

                }
            }
        }

        //INSTANCE.insertTestData();
        //DayDataDatabase_Impl dayDataDatabase_= new DayDataDatabase_Impl();
        //dayDataDatabase_.clearAllTables();
        return INSTANCE;
    }

    public static void setInstance(DayDataDatabase instance) {
        INSTANCE = instance;
    }

    public void insertTestData() {
        new Thread(() -> {
            DayData dayData1 = new DayData(System.currentTimeMillis() - 24*60*60*1000, 1000, 1.0, 500);
            DayData dayData2 = new DayData(System.currentTimeMillis() - 2*24*60*60*1000, 2000, 2.0, 700);
            DayData dayData3 = new DayData(System.currentTimeMillis() - 3*24*60*60*1000, 3000, 3.0, 700);
            DayData dayData4 = new DayData(System.currentTimeMillis() - 4*24*60*60*1000, 4000, 4.0, 700);
            DayData dayData5 = new DayData(System.currentTimeMillis() - 5*24*60*60*1000, 15000, 5.0, 700);
//
//            dayDataDao().insert(dayData1);
//            dayDataDao().insert(dayData2);
//            dayDataDao().insert(dayData3);
//            dayDataDao().insert(dayData4);
            dayDataDao().insert(dayData5);

        }).start();
    }


}

