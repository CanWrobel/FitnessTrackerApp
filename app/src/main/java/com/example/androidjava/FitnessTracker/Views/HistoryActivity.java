package com.example.androidjava.FitnessTracker.Views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidjava.FitnessTracker.Models.DayDataMessage;
import com.example.androidjava.R;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ArrayList<DayDataMessage> dayDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        RecyclerView recyclerView = findViewById(R.id.historyRecyclerView);

        setUpDayDataList();

        HistoryRecyclerViewAdapter adapter = new HistoryRecyclerViewAdapter(this, dayDataList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpDayDataList() {
        // Date[] dates  = get all dates from database
        // Int[] steps = get steps from all days in database
        // Double[] distance = get distance from all days in database
        // Double[] calories = get calories from all days in database

        // Create all inputs as instances of DayDataMessage/DayData and add into list
        /**
         for (int i = 0; i < dates.length; i++) {
         dayDataList.add(new DayDataMessage(steps[i], dates[i],distance[i], calories[i]));
         }
         **/



    }
}
