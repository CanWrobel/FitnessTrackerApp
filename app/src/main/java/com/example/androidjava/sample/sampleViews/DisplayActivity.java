package com.example.androidjava.sample.sampleViews;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidjava.R;

import java.util.Calendar;

import com.example.androidjava.sample.sampleViewModel.DayDataViewModel;
import com.example.androidjava.sample.sampleViewModel.DayDataViewModelFactory;

public class DisplayActivity extends AppCompatActivity {
    private DayDataViewModel viewModel;
    private TextView tvSelectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_display);

        // Find views
        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        Button btnFetchData = findViewById(R.id.btnFetchData);

        // Create a Date Picker
        tvSelectedDate.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(DisplayActivity.this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        String selectedDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                        // Display Selected date in TextView
                        tvSelectedDate.setText(selectedDate);
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        btnFetchData.setOnClickListener(v -> {
            // Get the selected date
            String date = tvSelectedDate.getText().toString();

            // Fetch the data from the database for the given date
            viewModel = new ViewModelProvider(this, new DayDataViewModelFactory(this.getApplication(), date)).get(DayDataViewModel.class);
            viewModel.getDayData().observe(this, dayData -> {
                // Assuming you have TextViews to display the data
                TextView tvSteps = findViewById(R.id.tvSteps);
                TextView tvDistance = findViewById(R.id.tvDistance);
                TextView tvCalories = findViewById(R.id.tvCalories);

                if (dayData != null) {
                    tvSteps.setText(String.valueOf(dayData.getSteps()));
                    tvDistance.setText(String.valueOf(dayData.getDistance()));
                    tvCalories.setText(String.valueOf(dayData.getCalories()));
                }
            });
        });
    }
}

