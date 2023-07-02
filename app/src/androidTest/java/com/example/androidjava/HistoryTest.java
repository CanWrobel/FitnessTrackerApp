package com.example.androidjava;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.androidjava.FitnessTracker.Models.Room.DayData;
import com.example.androidjava.FitnessTracker.Models.Room.DayDataDatabase;
import com.example.androidjava.FitnessTracker.Views.HistoryActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class HistoryTest {
    private DayDataDatabase testDb;

    @Rule
    public ActivityScenarioRule<HistoryActivity> activityRule =
            new ActivityScenarioRule<>(HistoryActivity.class);

    // Run this before each test
    @Before
    public void setUp() {

        testDb = Room.inMemoryDatabaseBuilder(
                        ApplicationProvider.getApplicationContext(),
                        DayDataDatabase.class
                ).allowMainThreadQueries() // Allowing main thread queries, just for testing
                .build();

        testDb.dayDataDao().insert(new DayData(1L, 1000, 1.2, 200));
        testDb.dayDataDao().insert(new DayData(2L, 2000, 2.3, 250));
        testDb.dayDataDao().insert(new DayData(3L, 3000, 3.4, 300));
        //testDb.dayDataDao().deleteAllDayData();
    }

    @Test
    public void historyViewModelRetrieveAndShowCorrectData() {
        onView(withId(R.id.historyRecyclerView))
                .perform(scrollToPosition(0))
                .check(matches(hasMinimumChildCount(3))); // check at least three items
    }

    @Test
    public void historyViewModelUpdateCorrectly() {
        testDb.dayDataDao().insert(new DayData(4L, 4000, 5, 400));

        onView(withId(R.id.historyRecyclerView))
                .perform(scrollToPosition(0))
                .check(matches(hasMinimumChildCount(4))); // check at least three items
    }
}
