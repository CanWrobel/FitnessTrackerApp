package com.example.androidjava.FitnessTracker.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidjava.FitnessTracker.Models.Datenbank.DatenbankDummy;
import com.example.androidjava.FitnessTracker.Models.Datenbank.DayDataMessage;
import com.example.androidjava.FitnessTracker.Models.Room.DayData;
import com.example.androidjava.FitnessTracker.Viewmodels.HistoryRecyclerViewAdapter;
import com.example.androidjava.FitnessTracker.Viewmodels.HistoryViewModel;
import com.example.androidjava.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    //ArrayList<DayDataMessage> dayDataList = new ArrayList<>();
    ArrayList<DayData> dayDataList = new ArrayList<>();
    private HistoryViewModel viewModel;
    HistoryRecyclerViewAdapter adapter;

    TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        RecyclerView recyclerView = findViewById(R.id.historyRecyclerView);

        viewModel = new HistoryViewModel(this);
        setUpDayDataList();

        adapter = new HistoryRecyclerViewAdapter(this, dayDataList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_history);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_main) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                } else if (itemId == R.id.nav_history) {
                    return true;
                } else if (itemId == R.id.nav_streaks) {
                    startActivity(new Intent(getApplicationContext(), StreaksActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                else if (itemId == R.id.nav_settings) {
                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                else if (itemId == R.id.nav_input) {
                    startActivity(new Intent(getApplicationContext(), InputActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }

                return false;
            }
        });


    }

    private void setUpDayDataList() {
        viewModel.getAllDayData().observe(this, dayData -> {
            // update the data list and notify the adapter
            dayDataList.clear();
            dayDataList.addAll(dayData);
            adapter.notifyDataSetChanged();
            if (dayDataList.size() == 0) {
                tvEmpty = findViewById(R.id.tvEmpty);
                tvEmpty.setVisibility(View.VISIBLE);
            }
        });



        /*
        DatenbankDummy dummy = new DatenbankDummy();

        List<DayDataMessage> list = dummy.fetchAllFitnessData();


        for (int i = 0; i < list.size(); i++) {
            Date date = list.get(i).getDatum();
            int steps = list.get(i).getSteps();
            double distance = list.get(i).getDistance();
            double calories = list.get(i).getCalories();

            dayDataList.add(new DayDataMessage(steps, date, distance, calories));

        }

        // Date[] dates  = get all dates from database
        // Int[] steps = get steps from all days in database
        // Double[] distance = get distance from all days in database
        // Double[] calories = get calories from all days in database

        // Create all inputs as instances of DayDataMessage/DayData and add into list

         for (int i = 0; i < dates.length; i++) {
         dayDataList.add(new DayDataMessage(steps[i], dates[i],distance[i], calories[i]));
         }
         */



    }
}
