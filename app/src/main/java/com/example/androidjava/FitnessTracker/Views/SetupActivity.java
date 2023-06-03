package com.example.androidjava.FitnessTracker.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    private float height, weight;
    private int stepsGoal;
    private float distanceGoal;
    private int caloriesGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_activites_flipper);

        viewModel = new ViewModelProvider(this).get(UserProfileViewModel.class);

        viewFlipper = findViewById(R.id.view_flipper);

        nameInput = findViewById(R.id.inputName);
        ageInput = findViewById(R.id.inputAge);
        heightInput = findViewById(R.id.inputHeight);
        weightInput = findViewById(R.id.inputWeight);
        stepsInput = findViewById(R.id.inputSteps);
        distanceInput = findViewById(R.id.inputDistance);
        caloriesInput = findViewById(R.id.inputCalories);

        Button nextButton1 = findViewById(R.id.btnNext1);
        Button nextButton2 = findViewById(R.id.btnNext2);
        Button nextButton3 = findViewById(R.id.btnNext3);

        Button backButton2 = findViewById(R.id.btnBack2);
        Button backButton3 = findViewById(R.id.btnBack3);

        //TODO: Set guard if input values are null (user did not input) and next button is clicked
        // Possibly not allowing to continue

        nextButton1.setOnClickListener(view -> {
            name = nameInput.getText().toString();
            viewFlipper.showNext();
        });

        nextButton2.setOnClickListener(view -> {
            age = Integer.parseInt(ageInput.getText().toString());
            height = Float.parseFloat(heightInput.getText().toString());
            weight = Float.parseFloat(weightInput.getText().toString());
            viewFlipper.showNext();
        });

        nextButton3.setOnClickListener(view -> {
            stepsGoal = Integer.parseInt(stepsInput.getText().toString());
            distanceGoal = Float.parseFloat(distanceInput.getText().toString());
            caloriesGoal = Integer.parseInt(caloriesInput.getText().toString());

            // Save the variables in the ViewModel.
            viewModel.setName(name);
            viewModel.setAge(age);
            viewModel.setHeight(height);
            viewModel.setWeight(weight);
            viewModel.setStepsGoal(stepsGoal);
            viewModel.setDistanceGoal(distanceGoal);
            viewModel.setCaloriesGoal(caloriesGoal);

            // Continue to main acitivity page
            Intent intent = new Intent(SetupActivity.this, MainActivity.class);
            startActivity(intent);
        });

        backButton2.setOnClickListener(view -> viewFlipper.showPrevious());

        backButton3.setOnClickListener(view -> viewFlipper.showPrevious());
    }
}
