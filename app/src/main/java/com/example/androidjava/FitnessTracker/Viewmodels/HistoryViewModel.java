package com.example.androidjava.FitnessTracker.Viewmodels;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidjava.FitnessTracker.Models.Room.DayData;
import com.example.androidjava.FitnessTracker.Models.Room.DayDataDatabase;

import java.util.List;

public class HistoryViewModel extends ViewModel {

    private LiveData<List<DayData>> allDayData;
    private DayDataDatabase database;

    public HistoryViewModel(Context context) {
        database = DayDataDatabase.getDatabase(context);
        //database.insertTestData();
        //database.dayDataDao().deleteAllDayData();
        allDayData = database.dayDataDao().getAllDayData();
    }

    public LiveData<List<DayData>> getAllDayData() {
        LiveData<List<DayData>> nog = allDayData;

        return allDayData;
    }
}

