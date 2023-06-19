package com.example.androidjava.FitnessTracker.Viewmodels;

import com.example.androidjava.FitnessTracker.Models.UserProfile;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Date;
import java.util.List;

/**
 * Interface for ViewModel handling the display and calculation of streaks in a fitness tracker app.
 */
public interface IStreaksViewModel {

    /**
     * Calculates number of days of the current streak of the user
     * @param userProfile The user's profile.
     * @return The length of the current streak.
     */
    int calculateStreak(UserProfile userProfile);

    /**
     * Returns all the previous streaks a user has had, excluding current streak
     * @param userProfile The user's profile.
     * @return A list of streaks (excluding current). Each streak is represented by a list of dates.
     */
    List<List<Date>> getAllStreaks(UserProfile userProfile);

    /**
     * Gets the dates of the current streak for a user
     * @param userProfile The user's profile.
     * @return A list of dates representing the current streak.
     */
    List<Date> getCurrentStreakDays(UserProfile userProfile);

    /**
     * Returns list of dates which is a part of the streak
     * (left, right, center, or independent) to be highlighted
     * @param dateList The list of dates to work on
     * @param part     The part of the streak to be highlighted (left, right, center, or independent).
     * @return A list of CalendarDay objects that should be highlighted.
     */
    List<CalendarDay> getDatePartToHighlight(List<Date> dateList, String part);
}
