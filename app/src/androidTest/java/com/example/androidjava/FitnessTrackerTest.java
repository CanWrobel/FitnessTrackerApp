//package com.example.androidjava;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.*;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
//import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//
//import static org.junit.Assert.assertEquals;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//import androidx.room.Room;
//import androidx.test.core.app.ApplicationProvider;
//import androidx.test.espresso.Espresso;
//import androidx.test.espresso.action.ViewActions;
//import androidx.test.espresso.assertion.ViewAssertions;
//import androidx.test.espresso.matcher.ViewMatchers;
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//
//import com.example.androidjava.FitnessTracker.Models.Room.DayData;
//import com.example.androidjava.FitnessTracker.Models.Room.DayDataDao;
//import com.example.androidjava.FitnessTracker.Models.Room.DayDataDatabase;
//import com.example.androidjava.FitnessTracker.Models.UserProfile;
//import com.example.androidjava.FitnessTracker.Viewmodels.IStreaksViewModel;
//import com.example.androidjava.FitnessTracker.Viewmodels.InputViewModel;
//import com.example.androidjava.FitnessTracker.Viewmodels.StreaksViewModel;
//import com.example.androidjava.FitnessTracker.Viewmodels.UserProfileViewModel;
//import com.example.androidjava.FitnessTracker.Views.SetupActivity;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//@RunWith(AndroidJUnit4.class)
//public class FitnessTrackerTest {
//
//
//    @Rule
//    public ActivityScenarioRule<SetupActivity> activityScenarioRule
//            = new ActivityScenarioRule<>(SetupActivity.class);
//
//    /*
//    User Bob of age 20, height 180, and weight 88 has a daily step goal of 8000.
//    He used the app for 3 days, up until including today
//    Day 1: 6500 steps
//    Day 2: 9500 steps
//    Day 3: 8500 steps
//
//    Integration test to see if all components work
//    Bob then also changes his weight to 80. Test settings component.
//     */
//    @Test
//    public void FirstUserStoryTest() {
//        String name = "Bob";
//        String age = "20";
//        String height = "180";
//        String weight = "88";
//        String stepsGoal = "8000";
//
//        // Fill in Setup First page
//        Espresso.onView(ViewMatchers.withId(R.id.inputName)).perform(typeText(name));
//        Espresso.closeSoftKeyboard();
//        Espresso.onView(ViewMatchers.withId(R.id.btnNext1)).perform(click());
//
//        //Fill in Setup Second page
//        Espresso.onView(ViewMatchers.withId(R.id.inputAge)).perform(typeText(age));
//        Espresso.closeSoftKeyboard();
//        Espresso.onView(ViewMatchers.withId(R.id.inputHeight)).perform(typeText(height));
//        Espresso.closeSoftKeyboard();
//        Espresso.onView(ViewMatchers.withId(R.id.inputWeight)).perform(typeText(weight));
//        Espresso.closeSoftKeyboard();
//
//        Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(click());
//
//        // Fill in setup Third page
//        Espresso.onView(ViewMatchers.withId(R.id.inputSteps)).perform(typeText(stepsGoal));
//        Espresso.closeSoftKeyboard();
//        Espresso.onView(withId(R.id.btnNext3)).check(matches(isDisplayed()));
//
//        // In main activity
//        Espresso.onView(ViewMatchers.withId(R.id.btnNext3)).perform(click());
//        Espresso.onView(withId(R.id.progress_text)).check(matches(isDisplayed()));
//
//        Context context = ApplicationProvider.getApplicationContext();
//        SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
//
//        assertEquals(name, sharedPreferences.getString("name", ""));
//        assertEquals(Integer.parseInt(age), sharedPreferences.getInt("age", 0));
//        assertEquals(Integer.parseInt(height), sharedPreferences.getInt("height", 0));
//        assertEquals(Float.parseFloat(weight), sharedPreferences.getFloat("weight", 0), 0.0);
//        assertEquals(Integer.parseInt(stepsGoal), sharedPreferences.getInt("stepsGoal", 0));
//
//        InputViewModel inputViewModel = new InputViewModel();
//        inputViewModel.initialize(sharedPreferences);
//
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//
//        calendar.add(Calendar.DAY_OF_MONTH, -2); // Move two days back
//        Long twoDaysAgo = calendar.getTimeInMillis();
//        calendar.add(Calendar.DAY_OF_MONTH, 1); // Move one day forward
//        int yesterdayDate = calendar.get(Calendar.DAY_OF_MONTH);
//        Long yesterday = calendar.getTimeInMillis();
//        calendar.add(Calendar.DAY_OF_MONTH, 1); // Move one day forward
//        int todayDate = calendar.get(Calendar.DAY_OF_MONTH);
//        Long today = calendar.getTimeInMillis();
//
//        DayData dayData1 = inputViewModel.createDayBasedOnSteps(twoDaysAgo, 6500);
//        DayData dayData2 = inputViewModel.createDayBasedOnSteps(yesterday, 9500);
//        DayData dayData3 = inputViewModel.createDayBasedOnSteps(today, 8500);
//
//
//        DayDataDatabase testDb = Room.inMemoryDatabaseBuilder(
//                        ApplicationProvider.getApplicationContext(),
//                        DayDataDatabase.class
//                ).allowMainThreadQueries() // Allowing main thread queries, just for testing
//                .build();
//
//        DayDataDatabase.setInstance(testDb);
//
//        testDb.dayDataDao().insert(dayData1);
//        testDb.dayDataDao().insert(dayData2);
//        testDb.dayDataDao().insert(dayData3);
//
//        // Test History showing the elements
//
//        Espresso.onView(ViewMatchers.withId(R.id.nav_history)).perform(click());
//        onView(withId(R.id.historyRecyclerView))
//                .perform(scrollToPosition(0))
//                .check(matches(hasMinimumChildCount(3))); // check at least three items
//
//
//        // Test Streaks
//        // Cannot test streaks view using Espresso (Which date highlighted)
//        // Limitation between Espresso & MaterialCalendarView
//        DayDataDao dayDataDao = testDb.dayDataDao();
//        IStreaksViewModel streaksViewModel = new StreaksViewModel(dayDataDao, sharedPreferences);
//        List<Date> currentStreak = streaksViewModel.getCurrentStreakDays();
//        int streakCount = streaksViewModel.calculateStreak();
//
//        calendar.setTime(currentStreak.get(1));
//        int day1 = calendar.get(Calendar.DAY_OF_MONTH);
//
//        calendar.setTime(currentStreak.get(0));
//        int day2 = calendar.get(Calendar.DAY_OF_MONTH);
//
//        assertEquals(yesterdayDate, day1);
//        assertEquals(todayDate, day2);
//        assertEquals(2, streakCount);
//
//        // Move to settings
//        Espresso.onView(ViewMatchers.withId(R.id.nav_settings)).perform(click());
//
//        // Test correctly displaying current data of User
//        Espresso.onView(ViewMatchers.withId(R.id.settingsName)).check(ViewAssertions.matches((ViewMatchers.withText(name))));
//        Espresso.onView(ViewMatchers.withId(R.id.settingsAge)).check(ViewAssertions.matches((ViewMatchers.withText(age))));
//        Espresso.onView(ViewMatchers.withId(R.id.settingsHeight)).check(ViewAssertions.matches((ViewMatchers.withText(height))));
//        Espresso.onView(ViewMatchers.withId(R.id.settingsWeight)).check(ViewAssertions.matches((ViewMatchers.withText("88.0"))));
//        Espresso.onView(ViewMatchers.withId(R.id.settingsStepsGoal)).check(ViewAssertions.matches((ViewMatchers.withText(stepsGoal))));
//
//
//        // Bob changes his weight to 80
//        Espresso.onView(ViewMatchers.withId(R.id.settingsWeight)).perform(ViewActions.clearText());
//        Espresso.onView(ViewMatchers.withId(R.id.settingsWeight)).perform(ViewActions.typeText("80"));
//        Espresso.closeSoftKeyboard();
//        Espresso.onView(ViewMatchers.withId(R.id.btnSaveSettings)).perform(click());
//
//        // Test data correctly changed
//        UserProfileViewModel userProfileViewModel = new UserProfileViewModel();
//        userProfileViewModel.initialize(sharedPreferences);
//        UserProfile userProfile = userProfileViewModel.getUserProfile();
//
//        assertEquals(80, userProfile.getWeight(), 0.1);
//    }
//
//}
