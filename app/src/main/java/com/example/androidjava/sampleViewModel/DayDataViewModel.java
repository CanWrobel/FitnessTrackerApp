package com.example.androidjava.sampleViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidjava.sampleModel.DayData;

public class DayDataViewModel extends AndroidViewModel {
    private final DayDataRepo repository;
    private final LiveData<DayData> dayData;


    public DayDataViewModel(@NonNull Application application, String date) {
        super(application);
        repository = new DayDataRepo(application, date);
        dayData = repository.getDayData(date);
    }

    public LiveData<DayData> getDayData() {
        return dayData;
    }

    public void insert(DayData dayData) {
        repository.insert(dayData);
    }
}

