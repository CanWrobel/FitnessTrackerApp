package com.example.androidjava.FitnessTracker.Views;

import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidjava.FitnessTracker.Models.Room.DayData;
import com.example.androidjava.FitnessTracker.Models.UserProfile;
import com.example.androidjava.FitnessTracker.Services.StepCounterService;
import com.example.androidjava.FitnessTracker.Viewmodels.UserProfileViewModel;
import com.example.androidjava.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView progressText;
    private Button btnShowSteps;
    private Button btnShowDistance;
    private Button btnShowCalories;

    private String choice = "steps";

    //TODO habe public gemacht fuer hack
    private DayData dayData;
    private SharedPreferences sharedPreferences;

    private UserProfile userProfile;

    private UserProfileViewModel userProfileViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        userProfileViewModel = new ViewModelProvider(this).get(UserProfileViewModel.class);
        userProfileViewModel.initialize(sharedPreferences);
        userProfile = userProfileViewModel.getUserProfile();

        long datum = sharedPreferences.getLong("datum", new Date().getTime());
        Date today = new Date();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTimeInMillis(datum);
        cal2.setTime(today);

        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

        if (sameDay) {
            dayData = new DayData(
                    datum,
                    sharedPreferences.getInt("steps", 0),
                    Double.longBitsToDouble(sharedPreferences.getLong("distance", 0L)),
                    sharedPreferences.getInt("calories", 0)
            );
        } else {
            dayData = new DayData(
                    today.getTime(),
                    0,
                    0.0,
                    0
            );
        }



        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.progress_text);
        btnShowSteps = findViewById(R.id.btnShowSteps);
        btnShowDistance = findViewById(R.id.btnShowDistance);
        btnShowCalories = findViewById(R.id.btnShowCalories);

        updateProgress("steps");

        Intent intent = new Intent(this, StepCounterService.class);
        startService(intent);

        IntentFilter intentFilter = new IntentFilter("com.example.androidjava.FitnessTracker.STEP_COUNT");
        registerReceiver(receiver, intentFilter);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set History selected
        bottomNavigationView.setSelectedItemId(R.id.nav_main);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_main) {
                    return true;
                } else if (itemId == R.id.nav_history) {
                    startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                    overridePendingTransition(0,0);
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

    public void endDay(View v){
        progressText.setText("0");
        progressBar.setProgress(0);
        Intent intent = new Intent("com.example.androidjava.FitnessTracker.RESET_STEP_COUNT");
        sendBroadcast(intent);
        Button btnEndDay = findViewById(R.id.btnEndDay);
        //btnEndDay.setVisibility(View.GONE);
    }

    public void showSteps(View view) {
        choice = "steps";
        updateProgress(choice);
    }

    public void showDistance(View view) {
        choice = "distance";
        updateProgress(choice);
    }
    public void showCalories(View view) {
        choice = "calories";
        updateProgress(choice);
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
                progressText.setText(String.format("%.1f", dayData.getDistance()) + " km");
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





    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
