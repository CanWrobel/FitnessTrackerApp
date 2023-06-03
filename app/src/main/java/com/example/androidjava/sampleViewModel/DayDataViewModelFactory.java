package com.example.androidjava.sampleViewModel;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DayDataViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String mDate;

    public DayDataViewModelFactory(Application application, String date) {
        mApplication = application;
        mDate = date;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new DayDataViewModel(mApplication, mDate);
    }
}

