package com.example.androidjava.FitnessTracker.Views;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidjava.FitnessTracker.Models.Room.DayData;
import com.example.androidjava.FitnessTracker.Viewmodels.InputViewModel;
import com.example.androidjava.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InputActivity extends AppCompatActivity {

    private Button post;
    private EditText inputDate;
    private EditText inputSteps;

    private InputViewModel inputViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_input);
        //TextView editTextDate =
        post = findViewById(R.id.btnSubmit);
        inputDate = findViewById(R.id.editTextDate);
        inputSteps = findViewById(R.id.etSteps);

        inputViewModel = new ViewModelProvider(this).get(InputViewModel.class);
        inputViewModel.initialize(this, getSharedPreferences("data", Context.MODE_PRIVATE));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_input);
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
                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                else if (itemId == R.id.nav_input) {
                    return true;
                }

                return false;
            }
        });

        //dayData


    }

    public void submitInput(View view){
        /*
        inputDate.setText("");
        inputSteps.setText("");
         */
        try {
            String dateString = inputDate.getText().toString();
            int steps = Integer.parseInt(inputSteps.getText().toString());

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

            Date date = formatter.parse(dateString);
            if(date != null) {
                Long dateInMilliseconds = date.getTime();
                DayData dayData = inputViewModel.createDayBasedOnSteps(dateInMilliseconds, steps);
                inputViewModel.insertDayDataToDB(dayData);
                Toast.makeText(this, "Manual data saved!", Toast.LENGTH_SHORT).show();
                inputDate.setText("");
                inputSteps.setText("");
            } else {
                inputDate.setError("PLease enter a valid date. (dd-MM-yyyy)");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(this, "Data for this day already present.", Toast.LENGTH_SHORT).show();
        }
    }
}
