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

        // Set History selected
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_settings);
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
                else if (itemId == R.id.nav_input) {
                    startActivity(new Intent(getApplicationContext(), InputActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }

                return false;
            }
        });    }

    private void fillInputFields() {
        UserProfile userProfile = userProfileViewModel.getUserProfile();
        settingsName.setText(userProfile.getName());
        settingsAge.setText(String.valueOf(userProfile.getAge()));
        settingsHeight.setText(String.valueOf(userProfile.getHeight()));
        settingsWeight.setText(String.valueOf(userProfile.getWeight()));
        settingsStepsGoal.setText(String.valueOf(userProfile.getStepsGoal()));
    }

    public void saveChanges(View view) {
        String name = settingsName.getText().toString();
        String age = settingsAge.getText().toString();
        String height = settingsHeight.getText().toString();
        String weight = settingsWeight.getText().toString();
        String stepsGoal = settingsStepsGoal.getText().toString();

        if (!userProfileViewModel.validateName(name)) {
            settingsName.setError(userProfileViewModel.getValidationMessage().getValue());
        }

        else if (!userProfileViewModel.validateAge(age)) {
            settingsAge.setError(userProfileViewModel.getValidationMessage().getValue());
        }

        else if (!userProfileViewModel.validateHeight(height)) {
            settingsHeight.setError(userProfileViewModel.getValidationMessage().getValue());
        }

        else if (!userProfileViewModel.validateWeight(weight)) {
            settingsWeight.setError(userProfileViewModel.getValidationMessage().getValue());
        }

        else if (!userProfileViewModel.validateStepsGoal(stepsGoal)) {
            settingsStepsGoal.setError(userProfileViewModel.getValidationMessage().getValue());
        }

        else {
            userProfileViewModel.updateUserProfile(
                    name,
                    Integer.parseInt(age),
                    Integer.parseInt(height),
                    Double.parseDouble(weight),
                    Integer.parseInt(stepsGoal)
            );
            Toast.makeText(this, "Changes saved!", Toast.LENGTH_SHORT).show();
        }

    }
}
