package com.example.androidjava.FitnessTracker.Viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidjava.FitnessTracker.Models.Room.DayData;
import com.example.androidjava.FitnessTracker.Models.Room.DayDataDatabase;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {

    private LiveData<List<DayData>> allDayData;
    private DayDataDatabase database;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        database = DayDataDatabase.getDatabase(application);
        //database.insertTestData();
        //database.dayDataDao().deleteAllDayData();
        allDayData = database.dayDataDao().getAllDayData();
    }

    public LiveData<List<DayData>> getAllDayData() {
        LiveData<List<DayData>> nog = allDayData;

        return allDayData;
    }
}

