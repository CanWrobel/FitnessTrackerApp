package com.example.androidjava.FitnessTracker.Viewmodels;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidjava.FitnessTracker.Models.Room.DayData;
import com.example.androidjava.FitnessTracker.Models.Room.DayDataDatabase;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class HistoryViewModel extends ViewModel {

    private LiveData<List<DayData>> allDayData;
    private DayDataDatabase database;

    public HistoryViewModel(Context context) {
        database = DayDataDatabase.getDatabase(context);
        /*database.insertTestData();
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            database.dayDataDao().deleteAllDayData();
            latch.countDown();
        }).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public LiveData<List<DayData>> getAllDayData() {
        allDayData = database.dayDataDao().getAllDayData();
        LiveData<List<DayData>> nog = allDayData;
        return allDayData;
    }
}

