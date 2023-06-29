package com.example.androidjava.FitnessTracker.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidjava.FitnessTracker.Models.DatenbankDummy;
import com.example.androidjava.FitnessTracker.Models.DayDataMessage;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        RecyclerView recyclerView = findViewById(R.id.historyRecyclerView);

        viewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        setUpDayDataList();

        adapter = new HistoryRecyclerViewAdapter(this, dayDataList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Intent intent;

                int itemId = item.getItemId();
                if (itemId == R.id.nav_main) {
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                } else if (itemId == R.id.nav_streaks) {
                    intent = new Intent(getApplicationContext(), StreaksActivity.class);
                } else if (itemId == R.id.nav_history) {
                    intent = new Intent(getApplicationContext(), HistoryActivity.class);
                } else {
                    // handle more items if needed
                    return false;
                }


                startActivity(intent);
                return true;
            }
        });

    }

    private void setUpDayDataList() {

        viewModel.getAllDayData().observe(this, new Observer<List<DayData>>() {
            @Override
            public void onChanged(List<DayData> dayData) {
                // update the data list and notify the adapter
                dayDataList.clear();
                dayDataList.addAll(dayData);
                adapter.notifyDataSetChanged();
            }
        });

        /**
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
        /**
         for (int i = 0; i < dates.length; i++) {
         dayDataList.add(new DayDataMessage(steps[i], dates[i],distance[i], calories[i]));
         }
         **/



    }

    public void switchToMain(View view) {
        Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void switchToStreaks(View view) {
        Intent intent = new Intent(HistoryActivity.this, StreaksActivity.class);
        startActivity(intent);
    }

    public void switchToHistory(View view) {
        Intent intent = new Intent(HistoryActivity.this, HistoryActivity.class);
        startActivity(intent);
    }

    public void switchToSample(View v){
        setContentView(R.layout.sample_input);
    }

    public void switchToSettings(View v){
        //TODO
        Button btn = findViewById(R.id.footerButton5);

        btn.setText("Drueck");
    }
}
