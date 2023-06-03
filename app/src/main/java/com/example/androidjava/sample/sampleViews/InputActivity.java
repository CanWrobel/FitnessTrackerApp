package com.example.androidjava.sample.sampleViews;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidjava.R;

import java.util.Calendar;

import com.example.androidjava.sample.sampleModel.DayData;
import com.example.androidjava.sample.sampleViewModel.DayDataViewModelFactory;
import com.example.androidjava.sample.sampleViewModel.DayDataViewModel;

public class InputActivity extends AppCompatActivity {

    Button btnSubmit;
    EditText etSteps, etDistance, etBurntCalories;

    TextView tvSelectedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_input);

        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        etSteps = findViewById(R.id.etSteps);
        etDistance = findViewById(R.id.etDistance);
        etBurntCalories = findViewById(R.id.etBurntCalories);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Toggle Date picker
        tvSelectedDate.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year1, monthOfYear, dayOfMonth) ->
                            tvSelectedDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1), year, month, day);
            datePickerDialog.show();
        });


        // Submit Data
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting inputs
                String date = tvSelectedDate.getText().toString();
                int steps = Integer.parseInt(etSteps.getText().toString());
                double distance = Double.parseDouble(etDistance.getText().toString());
                int calories = Integer.parseInt(etBurntCalories.getText().toString());

                // Create DayData object
                DayData dayData = new DayData(date, steps, distance, calories);

                // Save data to the database through the ViewModel
                DayDataViewModelFactory factory = new DayDataViewModelFactory(InputActivity.this.getApplication(), date);
                DayDataViewModel viewModel = new ViewModelProvider(InputActivity.this, factory).get(DayDataViewModel.class);

                viewModel.insert(dayData);

                // Pass data to DisplayActivity
                Intent intent = new Intent(InputActivity.this, DisplayActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

    }

}
