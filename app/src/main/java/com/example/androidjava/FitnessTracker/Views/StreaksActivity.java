
package com.example.androidjava.FitnessTracker.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidjava.FitnessTracker.Viewmodels.StreaksViewModel;
import com.example.androidjava.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;
import java.util.List;

/*
 * https://github.com/Thanvandh/Date-Range-Highlight
 */
public class StreaksActivity extends AppCompatActivity {
    MaterialCalendarView calendar;
    TextView date_view;
    StreaksViewModel streaksViewModel;
    int pink = 0;
    int gray = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streaks);

        streaksViewModel = new StreaksViewModel(this, getSharedPreferences("data", MODE_PRIVATE));

        // Get CalendarView and TextView
        calendar = findViewById(R.id.calendar);
        date_view = findViewById(R.id.tvStreakNumber);

        // Set streak count
        int streakCount = streaksViewModel.calculateStreak();
        date_view.setText("Current streak: " + streakCount +" days");

        // Get the successful days and convert them into CalendarDays
        List<List<Date>> allStreaks = streaksViewModel.getAllStreaks();
        List<Date> currentStreakDays = streaksViewModel.getCurrentStreakDays();


        for (List<Date> streak : allStreaks ) {
            setEvent(streak, gray);
        }
        setEvent(currentStreakDays, pink);

        calendar.invalidateDecorators();




        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set History selected
        bottomNavigationView.setSelectedItemId(R.id.nav_streaks);

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
                    return true;
                }
                else if (itemId == R.id.nav_settings) {
                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                return false;
            }
        });
    }

    void setEvent(List<Date> dateList, int color) {
        List<CalendarDay> datesLeft = streaksViewModel.getDatePartToHighlight(dateList, "left");
        List<CalendarDay> datesCenter = streaksViewModel.getDatePartToHighlight(dateList, "center");
        List<CalendarDay> datesRight = streaksViewModel.getDatePartToHighlight(dateList, "right");
        List<CalendarDay> datesIndependent = streaksViewModel.getDatePartToHighlight(dateList, "independent");


        if (color == pink) {
            setDecor(datesCenter, R.drawable.p_center);
            setDecor(datesLeft, R.drawable.p_left);
            setDecor(datesRight, R.drawable.p_right);
            setDecor(datesIndependent, R.drawable.p_independent);
        } else {
            setDecor(datesCenter, R.drawable.g_center);
            setDecor(datesLeft, R.drawable.g_left);
            setDecor(datesRight, R.drawable.g_right);
            setDecor(datesIndependent, R.drawable.g_independent);
        }
    }

    public void setDecor(List<CalendarDay> calendarDayList, int drawable) {
        calendar.addDecorators(new EventDecorator(this
                , drawable
                , calendarDayList));
    }

}
