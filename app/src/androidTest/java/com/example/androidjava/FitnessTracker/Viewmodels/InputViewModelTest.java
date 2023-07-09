package com.example.androidjava.FitnessTracker.Viewmodels;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.androidjava.FitnessTracker.Models.Room.DayData;
import com.example.androidjava.FitnessTracker.Models.Room.DayDataDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InputViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private InputViewModel inputViewModel;
    private DayDataDatabase db;

    @Before
    public void setup() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, DayDataDatabase.class).build();
        SharedPreferences sharedPreferences = context.getSharedPreferences("testData", Context.MODE_PRIVATE);
        DayDataDatabase.setInstance(db);

        inputViewModel = new InputViewModel();
        inputViewModel.initialize(context, sharedPreferences);
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testInsertAndRetrieveDayData() {
        DayData dayData = new DayData(1L, 1000, 1.0, 500);
        inputViewModel.insertDayDataToDB(dayData);
        DayData retrieved = db.dayDataDao().getDayDataByDate(1L);
        assertEquals(dayData.getDatum(), retrieved.getDatum());
        assertEquals(dayData.getSteps(), retrieved.getSteps());
        assertEquals(dayData.getCalories(), retrieved.getCalories());
        assertEquals(dayData.getDistance(), retrieved.getDistance(), 0.1);
    }
}
