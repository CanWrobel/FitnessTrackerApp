package com.example.androidjava.FitnessTracker.Views;

import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androidjava.FitnessTracker.Models.Room.DayData;
import com.example.androidjava.FitnessTracker.Models.UserProfile;
import com.example.androidjava.FitnessTracker.Services.StepCounterService;
import com.example.androidjava.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView progressText;
    private Button btnShowSteps;
    private Button btnShowDistance;
    private Button btnShowCalories;

    private String choice = "steps";

    private DayData dayData;
    private SharedPreferences sharedPreferences;

    private UserProfile userProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        userProfile = new UserProfile(sharedPreferences);
        dayData = new DayData(
                0L,
                sharedPreferences.getInt("steps", 0),
                Double.longBitsToDouble(sharedPreferences.getLong("distance", 0L)),
                sharedPreferences.getInt("calories", 0)
        );

        Intent intent = new Intent(this, StepCounterService.class);
        startService(intent);

        IntentFilter intentFilter = new IntentFilter("com.example.androidjava.FitnessTracker.STEP_COUNT");
        registerReceiver(receiver, intentFilter);

        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.progress_text);
        btnShowSteps = findViewById(R.id.btnShowSteps);
        btnShowDistance = findViewById(R.id.btnShowDistance);
        btnShowCalories = findViewById(R.id.btnShowCalories);

        updateProgress("steps");

        // Show steps on button click
        btnShowSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = "steps";
                updateProgress(choice);
            }
        });

        // Show distance on button click
        btnShowDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = "distance";
                updateProgress(choice);
            }
        });

        // Show calories on button click
        btnShowCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = "calories";
                updateProgress(choice);
            }
        });



        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Intent intent;

                int itemId = item.getItemId();
                if (itemId == R.id.nav_main) {
                    intent = new Intent(MainActivity.this, MainActivity.class);
                } else if (itemId == R.id.nav_streaks) {
                    intent = new Intent(MainActivity.this, StreaksActivity.class);
                } else if (itemId == R.id.nav_history) {
                    intent = new Intent(MainActivity.this, HistoryActivity.class);
                } else {
                    return false;
                }


                startActivity(intent);
                return true;
            }
        });



    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            dayData = (DayData) intent.getSerializableExtra("dayData");
            updateProgress(choice);
        }

    };

    private void updateProgress(String choice){
        switch (choice) {
            case "distance":
                progressText.setText(String.format("%.2f", dayData.getDistance()) + " km");
                progressBar.setMax((int)userProfile.getDistanceGoal());
                progressBar.setProgress((int) Math.ceil(dayData.getDistance()));
                break;
            case "calories":
                progressText.setText((dayData.getCalories()) + " kcal");
                progressBar.setMax(userProfile.getCaloriesGoal());
                progressBar.setProgress((int) dayData.getCalories());
                break;
            default:
                progressText.setText(String.valueOf(dayData.getSteps()));
                progressBar.setMax(userProfile.getStepsGoal());
                progressBar.setProgress(dayData.getSteps());
                break;
        }
    }

    public void endDay(View v){
        progressText.setText("0");
        progressBar.setProgress(0);
        Intent intent = new Intent("com.example.androidjava.FitnessTracker.RESET_STEP_COUNT");
        sendBroadcast(intent);
        Button btnEndDay = findViewById(R.id.btnEndDay);
        //btnEndDay.setVisibility(View.GONE);
    }
    /**
    public void switchToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void switchToStreaks(View view) {
        Intent intent = new Intent(this, StreaksActivity.class);
        startActivity(intent);
    }

    public void switchToHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    public void switchToSample(View v){
        setContentView(R.layout.activity_history);
    }

    public void switchToSettings(View v){
        //TODO
        Button btn = findViewById(R.id.footerButton5);

        btn.setText("Drueck");
    }**/


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}

