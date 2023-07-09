package com.example.androidjava.FitnessTracker.Viewmodels;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.androidjava.FitnessTracker.Models.Room.DayData;
import com.example.androidjava.FitnessTracker.Models.Room.DayDataDatabase;
import com.example.androidjava.FitnessTracker.Models.UserProfile;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class StreaksViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private IStreaksViewModel viewModel;

    private DayDataDatabase db;

    @Mock
    private SharedPreferences sharedPreferences;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Context testContext = ApplicationProvider.getApplicationContext();

        db = Room.inMemoryDatabaseBuilder(testContext, DayDataDatabase.class)
                .allowMainThreadQueries().build();
    }

    @After
    public void tearDown() {
        db.close();
    }

    @Test
    public void calculateStreakIs1Test() {
        when(sharedPreferences.getInt("stepsGoal", 0)).thenReturn(6000);
        // Today, steps 6000
        DayData dayData1 = new DayData(Calendar.getInstance().getTimeInMillis(), 6000, 5, 100);
        // Today - 1, steps 4000
        DayData dayData2 = new DayData(Calendar.getInstance().getTimeInMillis() - 86400000, 2000, 4, 80);
        // Today -2 , steps 6000
        DayData dayData3 = new DayData(Calendar.getInstance().getTimeInMillis() - 86400000 * 2, 6000, 5, 100);


        db.dayDataDao().insert(dayData1);
        db.dayDataDao().insert(dayData2);
        db.dayDataDao().insert(dayData3);

        viewModel = new StreaksViewModel(db, sharedPreferences);

        int streak = viewModel.calculateStreak();

        assertEquals(1, streak);
        db.dayDataDao().deleteAllDayData();
    }

    @Test
    public void calculateStreakSuccessTest() {
        when(sharedPreferences.getInt("stepsGoal", 0)).thenReturn(3000);
        // Today until today -4, all days reach goal of 3000
        DayData dayData1 = new DayData(Calendar.getInstance().getTimeInMillis(), 6000, 5, 100);
        DayData dayData2 = new DayData(Calendar.getInstance().getTimeInMillis() - 86400000, 4000, 4, 80);
        DayData dayData3 = new DayData(Calendar.getInstance().getTimeInMillis() - 86400000 * 2, 6000, 5, 100);
        DayData dayData4 = new DayData(Calendar.getInstance().getTimeInMillis() - 86400000 * 3, 6000, 5, 100);
        DayData dayData5 = new DayData(Calendar.getInstance().getTimeInMillis() - 86400000 * 4, 6000, 5, 100);


        db.dayDataDao().insert(dayData1);
        db.dayDataDao().insert(dayData2);
        db.dayDataDao().insert(dayData3);
        db.dayDataDao().insert(dayData4);
        db.dayDataDao().insert(dayData5);

        viewModel = new StreaksViewModel(db, sharedPreferences);

        int streak = viewModel.calculateStreak();

        assertEquals(5, streak);
        db.dayDataDao().deleteAllDayData();
    }

    @Test
    public void allStreaksAndCurrentStreakSuccessTest() {
        when(sharedPreferences.getInt("stepsGoal", 0)).thenReturn(3000);
        DayData dayData1 = new DayData(Calendar.getInstance().getTimeInMillis(), 6000, 5, 100);
        DayData dayData2 = new DayData(Calendar.getInstance().getTimeInMillis() - 86400000, 5000, 4, 80);
        DayData dayData3 = new DayData(Calendar.getInstance().getTimeInMillis() - 86400000 * 2, 2000, 5, 100);
        DayData dayData4 = new DayData(Calendar.getInstance().getTimeInMillis() - 86400000 * 3, 6000, 5, 100);
        DayData dayData5 = new DayData(Calendar.getInstance().getTimeInMillis() - 86400000 * 4, 6000, 5, 100);
        DayData dayData6 = new DayData(Calendar.getInstance().getTimeInMillis() - 86400000 * 5, 6000, 5, 100);


        db.dayDataDao().insert(dayData1);
        db.dayDataDao().insert(dayData2);
        db.dayDataDao().insert(dayData3);
        db.dayDataDao().insert(dayData4);
        db.dayDataDao().insert(dayData5);
        db.dayDataDao().insert(dayData6);

        viewModel = new StreaksViewModel(db, sharedPreferences);

        assertEquals(2, viewModel.getAllStreaks().size());
        assertEquals(3, viewModel.getAllStreaks().get(0).size());
        assertEquals(2, viewModel.getAllStreaks().get(1).size());
        db.dayDataDao().deleteAllDayData();
        //assertEquals(new Date(dayData5.getDatum()), viewModel.getAllStreaks().get(0).get(0));
    }


}
