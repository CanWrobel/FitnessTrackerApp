package com.example.androidjava.FitnessTracker.Viewmodels;

import android.content.SharedPreferences;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Date;
import java.util.List;

/**
 * Interface for StreaksViewModel
 */
public interface IStreaksViewModel {

    /**
     * Calculate the current streak based on user's steps goal.
     * @return the current (ongoing) streak
     */
    int calculateStreak();

    /**
     * Get all the streaks, current and including all previous
     * @return a list of all streaks (list of dates)
     */
    List<List<Date>> getAllStreaks();

    /**
     * Get the days of the current streak
     * @return a list of Date in the current streak
     */
    List<Date> getCurrentStreakDays();

    /**
     * Get the date parts to highlight based on the provided date list and part name.
     * @param dateList the list of dates
     * @param part the part name (left, center, right or independent)
     * @return a list of CalendarDay
     */
    List<CalendarDay> getDatePartToHighlight(List<Date> dateList, String part);
}
