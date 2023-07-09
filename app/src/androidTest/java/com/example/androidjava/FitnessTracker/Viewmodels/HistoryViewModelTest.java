package com.example.androidjava.FitnessTracker.Viewmodels;


import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.androidjava.FitnessTracker.Models.Room.DayData;
import com.example.androidjava.FitnessTracker.Models.Room.DayDataDao;
import com.example.androidjava.FitnessTracker.Models.Room.DayDataDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(MockitoJUnitRunner.class)
public class HistoryViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private HistoryViewModel viewModel;
    private DayDataDatabase database;
    private DayDataDao dayDataDao;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        database = Room.inMemoryDatabaseBuilder(context, DayDataDatabase.class)
                .allowMainThreadQueries()
                .build();
        dayDataDao = database.dayDataDao();
        DayDataDatabase.setInstance(database);
        viewModel = new HistoryViewModel(context);
    }


    @Test
    public void getAllDayDataTest() {
        dayDataDao.insert(new DayData(Calendar.getInstance().getTimeInMillis(), 1000, 1.0, 500));
        dayDataDao.insert(new DayData(Calendar.getInstance().getTimeInMillis() - 86400000 * 2, 2000, 2.0, 700));
        dayDataDao.insert(new DayData(Calendar.getInstance().getTimeInMillis() - 86400000 * 3, 3000, 3.0, 700));
        dayDataDao.insert(new DayData(Calendar.getInstance().getTimeInMillis() - 86400000 * 4, 4000, 4.0, 700));
        dayDataDao.insert(new DayData(Calendar.getInstance().getTimeInMillis() - 86400000 * 5, 15000, 5.0, 700));

        LiveData<List<DayData>> data = viewModel.getAllDayData();

        data.observeForever(dayDataList -> {
            assertEquals(5, dayDataList.size());
        });
    }

    @Test
    public void getAllDayDataNewDataInputTest() throws InterruptedException {
        dayDataDao.insert(new DayData(Calendar.getInstance().getTimeInMillis(), 1000, 1.0, 500));
        dayDataDao.insert(new DayData(Calendar.getInstance().getTimeInMillis() - 86400000 * 2, 2000, 2.0, 700));
        dayDataDao.insert(new DayData(Calendar.getInstance().getTimeInMillis() - 86400000 * 3, 3000, 3.0, 700));
        dayDataDao.insert(new DayData(Calendar.getInstance().getTimeInMillis() - 86400000 * 4, 4000, 4.0, 700));
        dayDataDao.insert(new DayData(Calendar.getInstance().getTimeInMillis() - 86400000 * 5, 15000, 5.0, 700));

        LiveData<List<DayData>> data = viewModel.getAllDayData();

        CountDownLatch latch = new CountDownLatch(1);
        data.observeForever(new Observer<List<DayData>>() {
            @Override
            public void onChanged(List<DayData> dayDataList) {
                assertEquals(5, dayDataList.size());
                data.removeObserver(this);
                latch.countDown();
            }
        });

        latch.await(2, TimeUnit.SECONDS);

        dayDataDao.insert(new DayData(Calendar.getInstance().getTimeInMillis() - 86400000 * 6, 15000, 5.0, 700));

        LiveData<List<DayData>> newData = viewModel.getAllDayData();
        CountDownLatch latch2 = new CountDownLatch(1);
        newData.observeForever(new Observer<List<DayData>>() {
            @Override
            public void onChanged(List<DayData> dayDataList) {
                assertEquals(6, dayDataList.size());
                newData.removeObserver(this);
                latch2.countDown();
            }
        });

        latch2.await(2, TimeUnit.SECONDS);

    }

    @After
    public void tearDown() {
        database.close();
    }
}
