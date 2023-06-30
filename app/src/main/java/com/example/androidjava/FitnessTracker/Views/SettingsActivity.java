package com.example.androidjava.FitnessTracker.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidjava.FitnessTracker.Models.UserProfile;
import com.example.androidjava.FitnessTracker.Viewmodels.UserProfileViewModel;
import com.example.androidjava.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {
    private UserProfileViewModel userProfileViewModel;
    private EditText settingsName, settingsAge, settingsHeight, settingsWeight, settingsStepsGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsName = findViewById(R.id.settingsName);
        settingsAge = findViewById(R.id.settingsAge);
        settingsHeight = findViewById(R.id.settingsHeight);
        settingsWeight = findViewById(R.id.settingsWeight);
        settingsStepsGoal = findViewById(R.id.settingsStepsGoal);

        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);

        userProfileViewModel = new UserProfileViewModel();
        userProfileViewModel.initialize(sharedPreferences);

        fillInputFields();

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set History selected
        bottomNavigationView.setSelectedItemId(R.id.nav_settings);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_main) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0,0);
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
                    return true;
                }
                return false;
            }
        });
    }

    private void fillInputFields() {
        UserProfile userProfile = userProfileViewModel.getUserProfile();
        settingsName.setText(userProfile.getName());
        settingsAge.setText(String.valueOf(userProfile.getAge()));
        settingsHeight.setText(String.valueOf(userProfile.getHeight()));
        settingsWeight.setText(String.valueOf(userProfile.getWeight()));
        settingsStepsGoal.setText(String.valueOf(userProfile.getStepsGoal()));
    }

    public void saveChanges(View view) {
        if (validateInputs()) {
            int age = Integer.parseInt(settingsAge.getText().toString());
            int height = Integer.parseInt(settingsHeight.getText().toString());
            double weight = Double.parseDouble(settingsWeight.getText().toString());
            int stepsGoal = Integer.parseInt(settingsStepsGoal.getText().toString());

            userProfileViewModel.updateUserProfile(
                    settingsName.getText().toString(),
                    age,
                    height,
                    weight,
                    stepsGoal
            );

            Toast.makeText(this, "Changes saved!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInputs() {
        if (settingsName.getText().toString().isEmpty()) {
            settingsName.setError("Name field cannot be empty");
            return false;
        }

        if (settingsAge.getText().toString().isEmpty()) {
            settingsAge.setError("Age field cannot be empty");
            return false;
        }

        if (settingsHeight.getText().toString().isEmpty()) {
            settingsHeight.setError("Height field cannot be empty");
            return false;
        }

        if (settingsWeight.getText().toString().isEmpty()) {
            settingsWeight.setError("Weight field cannot be empty");
            return false;
        }

        if (settingsStepsGoal.getText().toString().isEmpty()) {
            settingsStepsGoal.setError("Goal Steps field cannot be empty");
            return false;
        }

        return true;
    }
}
