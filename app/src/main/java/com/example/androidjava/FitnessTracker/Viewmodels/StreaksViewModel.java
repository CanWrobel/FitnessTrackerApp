package com.example.androidjava.FitnessTracker.Viewmodels;

import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;

import com.example.androidjava.FitnessTracker.Models.DatenbankDummy;
import com.example.androidjava.FitnessTracker.Models.DayDataMessage;
import com.example.androidjava.FitnessTracker.Models.UserProfile;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/*
 * Date-Range-Highlight im Kalender:
 * Angepasste Implementierung, originale Implementierung von:
 * Author: Thanvandh
 * Repo: Date-Range-Highlight
 * https://github.com/Thanvandh/Date-Range-Highlight
 *
 *
 * Material-Calendarview library source code:
 * https://github.com/prolificinteractive/material-calendarview
 */
public class StreaksViewModel extends ViewModel implements IStreaksViewModel {
    /*
    private List<DayData> allData;
    private UserProfile userProfile;
    private UserProfileViewModel userProfileViewModel;
    private DayDataDao dayDataDao;

    public StreaksViewModel(Context context, SharedPreferences sharedPreferences) {
        this.dayDataDao = DayDataDatabase.getDatabase(context).dayDataDao();

        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            //allData = dayDataDao.getAllDayData().getValue();
            allData = dayDataDao.getAllDayDataList();
            latch.countDown();
        }).start();

        try {
            latch.await();

            userProfileViewModel = new UserProfileViewModel();
            userProfileViewModel.initialize(sharedPreferences);
            userProfile = userProfileViewModel.getUserProfile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.sort(this.allData, Comparator.comparing(DayData::getDatum));

    }

    public StreaksViewModel(@NonNull DayDataDao dayDataDao, SharedPreferences sharedPreferences) {
        this.dayDataDao = dayDataDao;
        allData = dayDataDao.getAllDayDataList();

        userProfileViewModel = new UserProfileViewModel();
        userProfileViewModel.initialize(sharedPreferences);
        userProfile = userProfileViewModel.getUserProfile();
    }

    public int calculateStreak() {
        int streakCount = 0;
        Calendar calendar = Calendar.getInstance();
        Long today = calendar.getTime().getTime();

        // Iterating in reverse order to start from the most recent data
        for (int i = allData.size() - 1; i >= 0; i--) {
            DayData dayData = allData.get(i);
            if (dayData.getDatum().compareTo(today) > 0) {
                // Skip future data
                continue;
            }
            if (dayData.getSteps() >= userProfile.getStepsGoal()) {
                streakCount++;
                today = dayData.getDatum(); // move 'today' to the previous day
            } else {
                break; // streak is broken
            }
        }
        return streakCount;
    }

    public List<List<Date>> getAllStreaks() {
        List<List<Date>> allStreaks = new ArrayList<>();
        List<Date> currentStreak = new ArrayList<>();

        // Iterating from the oldest data
        for (int i = 0; i < allData.size(); i++) {
            DayData dayData = allData.get(i);

            if (dayData.getSteps() >= userProfile.getStepsGoal()) {
                currentStreak.add(new Date(dayData.getDatum()));
            } else {
                if (!currentStreak.isEmpty()) {
                    allStreaks.add(currentStreak);
                    currentStreak = new ArrayList<>();
                }
            }
        }

        // Adding the last streak if it's not empty
        if (!currentStreak.isEmpty()) {
            allStreaks.add(currentStreak);
        }

        return allStreaks;
    }


    private List<Date> getSuccessfulDays(UserProfile userProfile) {
        List<Date> successfulDays = new ArrayList<>();
        for (DayData dayData : allData) {
            if (dayData.getSteps() >= userProfile.getStepsGoal()) {
                successfulDays.add(new Date(dayData.getDatum()));
            }
        }
        return successfulDays;
    }

    public List<Date> getCurrentStreakDays() {
        List<Date> streakDays = new ArrayList<>();
        Long today = new Date().getTime();

        // Iterating in reverse order to start from the most recent data
        for (int i = allData.size() - 1; i >= 0; i--) {
            DayData dayData = allData.get(i);

            if (dayData.getDatum().compareTo(today) > 0) {
                // Skip future data
                continue;
            }
            if (dayData.getSteps() >= userProfile.getStepsGoal()) {
                streakDays.add(new Date(dayData.getDatum()));
                today = dayData.getDatum(); // move 'today' to the previous day
            } else {
                break; // streak is broken
            }
        }
        return streakDays;
    }


    public List<CalendarDay> getDatePartToHighlight(List<Date> dateList, String part) {
        List<CalendarDay> calendarDayList = dateList.stream()
                .map(date -> CalendarDay.from(date))
                .collect(Collectors.toList());

        List<CalendarDay> datesLeft = new ArrayList<>();
        List<CalendarDay> datesCenter = new ArrayList<>();
        List<CalendarDay> datesRight = new ArrayList<>();
        List<CalendarDay> datesIndependent = new ArrayList<>();

        for (CalendarDay calendarDay : calendarDayList) {
            boolean right = false;
            boolean left = false;

            for (CalendarDay day1 : calendarDayList) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(day1.getDate());

                // Create Calendar for day after day1
                Calendar nextDay = (Calendar) calendar.clone();
                nextDay.add(Calendar.DAY_OF_MONTH, 1);
                CalendarDay nextCalendarDay = CalendarDay.from(nextDay);

                // Create Calendar for day before day1
                Calendar previousDay = (Calendar) calendar.clone();
                previousDay.add(Calendar.DAY_OF_MONTH, -1);
                CalendarDay previousCalendarDay = CalendarDay.from(previousDay);

                if (calendarDay.equals(previousCalendarDay)) {
                    right = true;
                }
                if (calendarDay.equals(nextCalendarDay)) {
                    left = true;
                }
            }

            if (left && right) {
                datesCenter.add(calendarDay);
            } else if (left) {
                datesLeft.add(calendarDay);
            } else if (right) {
                datesRight.add(calendarDay);
            } else {
                datesIndependent.add(calendarDay);
            }
        }

        switch (part.toLowerCase()) {
            case "left":
                return datesLeft;
            case "right":
                return datesRight;
            case "center":
                return datesCenter;
            default:
                return datesIndependent;
        }
    }*/


    // Nutzt Dummy

    private List<DayDataMessage> allData;
    private UserProfile userProfile;
    private UserProfileViewModel userProfileViewModel;

    public StreaksViewModel(SharedPreferences sharedPreferences) {
        this.allData = new DatenbankDummy().fetchAllFitnessData();
        Collections.sort(this.allData, Comparator.comparing(DayDataMessage::getDatum));

        userProfileViewModel = new UserProfileViewModel();
        userProfileViewModel.initialize(sharedPreferences);
        userProfile = userProfileViewModel.getUserProfile();
    }

    public int calculateStreak() {
        int streakCount = 0;
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        // Iterating in reverse order to start from the most recent data
        for (int i = allData.size() - 1; i >= 0; i--) {
            DayDataMessage dayData = allData.get(i);
            if (dayData.getDatum().compareTo(today) > 0) {
                // Skip future data
                continue;
            }
            if (dayData.getSteps() >= userProfile.getStepsGoal()) {
                streakCount++;
                today = dayData.getDatum(); // move 'today' to the previous day
            } else {
                break; // streak is broken
            }
        }
        return streakCount;
    }

    public List<List<Date>> getAllStreaks() {
        List<List<Date>> allStreaks = new ArrayList<>();
        List<Date> currentStreak = new ArrayList<>();

        // Iterating from the oldest data
        for (int i = 0; i < allData.size(); i++) {
            DayDataMessage dayData = allData.get(i);

            if (dayData.getSteps() >= userProfile.getStepsGoal()) {
                currentStreak.add(dayData.getDatum());
            } else {
                if (!currentStreak.isEmpty()) {
                    allStreaks.add(currentStreak);
                    currentStreak = new ArrayList<>();
                }
            }
        }

        // Adding the last streak if it's not empty
        if (!currentStreak.isEmpty()) {
            allStreaks.add(currentStreak);
        }

        return allStreaks;
    }


    private List<Date> getSuccessfulDays(UserProfile userProfile) {
        List<Date> successfulDays = new ArrayList<>();
        for (DayDataMessage dayData : allData) {
            if (dayData.getSteps() >= userProfile.getStepsGoal()) {
                successfulDays.add(dayData.getDatum());
            }
        }
        return successfulDays;
    }

    public List<Date> getCurrentStreakDays() {
        List<Date> streakDays = new ArrayList<>();
        Date today = new Date();

        // Iterating in reverse order to start from the most recent data
        for (int i = allData.size() - 1; i >= 0; i--) {
            DayDataMessage dayData = allData.get(i);

            if (dayData.getDatum().compareTo(today) > 0) {
                // Skip future data
                continue;
            }
            if (dayData.getSteps() >= userProfile.getStepsGoal()) {
                streakDays.add(dayData.getDatum());
                today = dayData.getDatum(); // move 'today' to the previous day
            } else {
                break; // streak is broken
            }
        }
        return streakDays;
    }


    public List<CalendarDay> getDatePartToHighlight(List<Date> dateList, String part) {
        List<CalendarDay> calendarDayList = dateList.stream()
                .map(date -> CalendarDay.from(date))
                .collect(Collectors.toList());

        List<CalendarDay> datesLeft = new ArrayList<>();
        List<CalendarDay> datesCenter = new ArrayList<>();
        List<CalendarDay> datesRight = new ArrayList<>();
        List<CalendarDay> datesIndependent = new ArrayList<>();

        for (CalendarDay calendarDay : calendarDayList) {
            boolean right = false;
            boolean left = false;

            for (CalendarDay day1 : calendarDayList) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(day1.getDate());

                // Create Calendar for day after day1
                Calendar nextDay = (Calendar) calendar.clone();
                nextDay.add(Calendar.DAY_OF_MONTH, 1);
                CalendarDay nextCalendarDay = CalendarDay.from(nextDay);

                // Create Calendar for day before day1
                Calendar previousDay = (Calendar) calendar.clone();
                previousDay.add(Calendar.DAY_OF_MONTH, -1);
                CalendarDay previousCalendarDay = CalendarDay.from(previousDay);

                if (calendarDay.equals(previousCalendarDay)) {
                    right = true;
                }
                if (calendarDay.equals(nextCalendarDay)) {
                    left = true;
                }
            }

            if (left && right) {
                datesCenter.add(calendarDay);
            } else if (left) {
                datesLeft.add(calendarDay);
            } else if (right) {
                datesRight.add(calendarDay);
            } else {
                datesIndependent.add(calendarDay);
            }
        }

        switch (part.toLowerCase()) {
            case "left":
                return datesLeft;
            case "right":
                return datesRight;
            case "center":
                return datesCenter;
            default:
                return datesIndependent;
        }
    }

}
