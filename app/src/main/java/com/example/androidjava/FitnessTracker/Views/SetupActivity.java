package com.example.androidjava.FitnessTracker.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
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

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_activites_flipper);

        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        viewModel = new ViewModelProvider(this).get(UserProfileViewModel.class);
        viewModel.initialize(sharedPreferences);


        boolean isFirstRun = viewModel.getFirstRunFromRepo();
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
    }

    public void nextButton1Click(View view) {
        name = nameInput.getText().toString();
        if (viewModel.validateName(name)) {
            viewFlipper.showNext();
        } else {
            nameInput.setError(viewModel.getValidationMessage().toString());
        }
    }

    public void nextButton2Click(View view) {
        String ageString = ageInput.getText().toString();
        String heightString = heightInput.getText().toString();
        String weightString = weightInput.getText().toString();

        if (!viewModel.validateAge(ageString)) {
            ageInput.setError(viewModel.getValidationMessage().toString());
        } else if (!viewModel.validateHeight(heightString)) {
            heightInput.setError(viewModel.getValidationMessage().toString());
        } else if (!viewModel.validateWeight(weightString)) {
            weightInput.setError(viewModel.getValidationMessage().toString());
        } else {
            age = Integer.parseInt(ageString);
            height = Integer.parseInt(heightString);
            weight = Double.parseDouble(weightString);
            viewFlipper.showNext();
        }
    }

    public void nextButton3Click(View view) {
        String stepsString = stepsInput.getText().toString();

        if (!viewModel.validateStepsGoal(stepsString)) {
            stepsInput.setError(viewModel.getValidationMessage().toString());
        } else {
            stepsGoal = Integer.parseInt(stepsString);
            viewModel.updateUserProfile(name, age, height, weight, stepsGoal);
            viewModel.saveFirstRunToRepo(false);

            // Continue to main acitivity page
            Intent intent = new Intent(SetupActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void backButton2Click(View view) {
        viewFlipper.showPrevious();
    }

    public void backButton3Click(View view) {
        viewFlipper.showPrevious();
    }
}
