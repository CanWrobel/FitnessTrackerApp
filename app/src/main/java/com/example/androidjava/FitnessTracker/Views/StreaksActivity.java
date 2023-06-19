
package com.example.androidjava.FitnessTracker.Views;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidjava.FitnessTracker.Models.UserProfile;
import com.example.androidjava.FitnessTracker.Viewmodels.StreaksViewModel;
import com.example.androidjava.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;
import java.util.List;

public class StreaksActivity extends AppCompatActivity {
    MaterialCalendarView calendar;
    TextView date_view;
    UserProfile userProfile;
    StreaksViewModel streaksViewModel;
    int pink = 0;
    int gray = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streaks);

        userProfile = new UserProfile();
        streaksViewModel = new ViewModelProvider(this).get(StreaksViewModel.class);

        // Get CalendarView and TextView
        calendar = findViewById(R.id.calendar);
        date_view = findViewById(R.id.tvStreakNumber);

        // Set streak count
        int streakCount = streaksViewModel.calculateStreak(userProfile);
        date_view.setText("Current streak: " + streakCount +" days");

        // Get the successful days and convert them into CalendarDays
        List<List<Date>> allStreaks = streaksViewModel.getAllStreaks(userProfile);
        List<Date> currentStreakDays = streaksViewModel.getCurrentStreakDays(userProfile);


        for (List<Date> streak : allStreaks ) {
            setEvent(streak, gray);
        }
        setEvent(currentStreakDays, pink);

        calendar.invalidateDecorators();
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
