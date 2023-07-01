package com.example.androidjava.FitnessTracker.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ViewFlipper;

import androidx.lifecycle.ViewModelProvider;

import com.example.androidjava.FitnessTracker.Viewmodels.UserProfileViewModel;
import com.example.androidjava.R;
public class SetupActivity extends AppCompatActivity {
    private UserProfileViewModel viewModel;
    private ViewFlipper viewFlipper;

    private EditText nameInput, ageInput, heightInput, weightInput, stepsInput, distanceInput, caloriesInput;

    private String name;
    private int age;
    private int height;
    private double weight;
    private int stepsGoal;
    private float distanceGoal;
    private int caloriesGoal;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_activites_flipper);

        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        viewModel = new ViewModelProvider(this).get(UserProfileViewModel.class);
        viewModel.initialize(sharedPreferences);


        boolean isFirstRun = viewModel.getSharedPreferences().getBoolean("isFirstRun", true);
        if (!isFirstRun) {
            Intent intent = new Intent(SetupActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }



        viewFlipper = findViewById(R.id.view_flipper);

        nameInput = findViewById(R.id.inputName);
        ageInput = findViewById(R.id.inputAge);
        heightInput = findViewById(R.id.inputHeight);
        weightInput = findViewById(R.id.inputWeight);
        stepsInput = findViewById(R.id.inputSteps);
        distanceInput = findViewById(R.id.inputDistance);
        caloriesInput = findViewById(R.id.inputCalories);
    }

    public void nextButton1Click(View view) {
        name = nameInput.getText().toString();
        if (name.isEmpty()) {
            nameInput.setError("Name cannot be empty");
        } else {
            viewFlipper.showNext();
        }
    }

    public void nextButton2Click(View view) {
        String ageString = ageInput.getText().toString();
        String heightString = heightInput.getText().toString();
        String weightString = weightInput.getText().toString();

        if (ageString.isEmpty()) {
            ageInput.setError("Age cannot be empty");
        } if (heightString.isEmpty()) {
            heightInput.setError("Height cannot be empty");
        } else if (weightString.isEmpty()) {
            weightInput.setError("Weight cannot be empty");
        } else {
            age = Integer.parseInt(ageString);
            height = Integer.parseInt(heightString);
            weight = Double.parseDouble(weightString);
            viewFlipper.showNext();
        }
    }

    public void nextButton3Click(View view) {
        String stepsString = stepsInput.getText().toString();

        if (stepsString.isEmpty()) {
            stepsInput.setError("Steps Goal cannot be empty");
        } else {
            stepsGoal = Integer.parseInt(stepsString);
            viewModel.updateUserProfile(name, age, height, weight, stepsGoal);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstRun", false);
            editor.apply();

            // Continue to main acitivity page
            Intent intent = new Intent(SetupActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
