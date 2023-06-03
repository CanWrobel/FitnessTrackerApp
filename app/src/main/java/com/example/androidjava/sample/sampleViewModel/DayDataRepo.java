package com.example.androidjava.sample.sampleViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.androidjava.sample.sampleModel.DayData;
import com.example.androidjava.sample.sampleModel.AppDatabase;
import com.example.androidjava.sample.sampleModel.DayDataDao;

public class DayDataRepo {
    private final DayDataDao dayDataDao;
    private final LiveData<DayData> dayData;

    DayDataRepo(Application application, String date) {
        AppDatabase db = Room.databaseBuilder(application,
                AppDatabase.class, "activity-database").allowMainThreadQueries().build();
        dayDataDao = db.dayDataDao();
        dayData = dayDataDao.loadDayDataByDate(date);
    }

    LiveData<DayData> getDayData(String date) {
        return dayData;
    }

    void insert(DayData dayData) {
        new Thread(() -> {
            dayDataDao.insert(dayData);
        }).start();
    }
}
