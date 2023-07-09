package com.example.androidjava;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.androidjava.FitnessTracker.Models.Room.DayData;
import com.example.androidjava.FitnessTracker.Models.Room.DayDataDao;
import com.example.androidjava.FitnessTracker.Models.Room.DayDataDatabase;
import com.example.androidjava.FitnessTracker.Models.UserProfile;
import com.example.androidjava.FitnessTracker.Viewmodels.IStreaksViewModel;
import com.example.androidjava.FitnessTracker.Viewmodels.InputViewModel;
import com.example.androidjava.FitnessTracker.Viewmodels.StreaksViewModel;
import com.example.androidjava.FitnessTracker.Viewmodels.UserProfileViewModel;
import com.example.androidjava.FitnessTracker.Views.SetupActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import UserStoryData;

public class UserStoryTest {

    @Rule
    public ActivityScenarioRule<SetupActivity> activityScenarioRule
            = new ActivityScenarioRule<>(SetupActivity.class);

    // End-to-end test, requirements is oepning app for the very first time
    // in virtual device first off wipe data before running test
    @Test
    public void userStoryTest() throws InterruptedException {

        // First page
        Espresso.onView(withId(R.id.inputName)).perform(ViewActions.typeText(UserStoryData.user_name));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btnNext1)).perform(ViewActions.click());

        //Second page
        Espresso.onView(withId(R.id.inputAge)).perform(ViewActions.typeText(UserStoryData.age));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.inputHeight)).perform(ViewActions.typeText(UserStoryData.height));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.inputWeight)).perform(ViewActions.typeText(UserStoryData.weight));
        Espresso.closeSoftKeyboard();


        Espresso.onView(withId(R.id.btnNext2)).perform(ViewActions.click());

        Thread.sleep(300);

        // Type in steps goal
        Espresso.onView(withId(R.id.inputSteps)).perform(ViewActions.typeText(UserStoryData.steps));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.btnNext3)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.nav_input)).perform(ViewActions.click());


        Espresso.onView(withId(R.id.editTextDate)).perform(ViewActions.typeText("2023-06-30"));
        Espresso.onView(withId(R.id.etSteps)).perform(ViewActions.typeText("1000"));
        Espresso.onView(withId(R.id.btnSubmit)).perform(ViewActions.click());
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextDate)).perform(ViewActions.typeText("2023-06-29"));
        Espresso.onView(withId(R.id.etSteps)).perform(ViewActions.typeText("1000"));
        Espresso.onView(withId(R.id.btnSubmit)).perform(ViewActions.click());
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextDate)).perform(ViewActions.typeText("2023-06-28"));
        Espresso.onView(withId(R.id.etSteps)).perform(ViewActions.typeText("1000"));
        Espresso.onView(withId(R.id.btnSubmit)).perform(ViewActions.click());
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextDate)).perform(ViewActions.typeText("2023-06-27"));
        Espresso.onView(withId(R.id.etSteps)).perform(ViewActions.typeText("1000"));
        Espresso.onView(withId(R.id.btnSubmit)).perform(ViewActions.click());
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextDate)).perform(ViewActions.typeText("2023-06-26"));
        Espresso.onView(withId(R.id.etSteps)).perform(ViewActions.typeText("1000"));
        Espresso.onView(withId(R.id.btnSubmit)).perform(ViewActions.click());
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.nav_history)).perform(ViewActions.click());

        Thread.sleep(300);

        Espresso.onView(withId(R.id.nav_streaks)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.calendar)).perform(swipeRight());

        Thread.sleep(300);

        Espresso.onView(ViewMatchers.withId(R.id.nav_settings)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.settingsWeight)).perform(ViewActions.clearText());

        Thread.sleep(50);

        Espresso.onView(withId(R.id.settingsWeight)).perform(ViewActions.typeText("69"));
        Espresso.closeSoftKeyboard();

        Thread.sleep(50);

        Espresso.onView(ViewMatchers.withId(R.id.btnSaveSettings)).perform(ViewActions.click());


        Espresso.onView(ViewMatchers.withId(R.id.nav_history)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.nav_settings)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.settingsStepsGoal)).perform(ViewActions.clearText());
        Espresso.onView(withId(R.id.settingsStepsGoal)).perform(ViewActions.typeText("2000"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(ViewMatchers.withId(R.id.btnSaveSettings)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.nav_streaks)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.calendar)).perform(swipeRight());

        Thread.sleep(300);

        Espresso.onView(ViewMatchers.withId(R.id.nav_settings)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.settingsStepsGoal)).perform(ViewActions.clearText());
        Espresso.onView(withId(R.id.settingsStepsGoal)).perform(ViewActions.typeText("100"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(ViewMatchers.withId(R.id.btnSaveSettings)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.nav_streaks)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.calendar)).perform(swipeRight());

        Thread.sleep(10000);
    }



    /*
    User Bob of age 20, height 180, and weight 88 has a daily step goal of 8000.
    He used the app for 3 days, up until including today
    Day 1: 6500 steps
    Day 2: 9500 steps
    Day 3: 8500 steps

    Integration test to see if all components work
    Bob then also changes his weight to 80. Test settings component.
     */

    // End-to-end test, requirements is oepning app for the very first time
    // in virtual device first off wipe data before running test
    @Test
    public void userStoryTest2() throws InterruptedException {
            String name = UserStoryData.user_name2;
            String age = UserStoryData.age2;
            String height = UserStoryData.height2;
            String weight = UserStoryData.weight2;
            String stepsGoal = UserStoryData.steps2;

            // Fill in Setup First page
            Espresso.onView(ViewMatchers.withId(R.id.inputName)).perform(typeText(name));
            Espresso.closeSoftKeyboard();
            Espresso.onView(ViewMatchers.withId(R.id.btnNext1)).perform(click());

            //Fill in Setup Second page
            Espresso.onView(ViewMatchers.withId(R.id.inputAge)).perform(typeText(age));
            Espresso.closeSoftKeyboard();
            Espresso.onView(ViewMatchers.withId(R.id.inputHeight)).perform(typeText(height));
            Espresso.closeSoftKeyboard();
            Espresso.onView(ViewMatchers.withId(R.id.inputWeight)).perform(typeText(weight));
            Espresso.closeSoftKeyboard();

            Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(click());

            // Fill in setup Third page
            Espresso.onView(ViewMatchers.withId(R.id.inputSteps)).perform(typeText(stepsGoal));
            Espresso.closeSoftKeyboard();
            Espresso.onView(withId(R.id.btnNext3)).check(matches(isDisplayed()));

            // In main activity
            Espresso.onView(ViewMatchers.withId(R.id.btnNext3)).perform(click());
            Espresso.onView(withId(R.id.progress_text)).check(matches(isDisplayed()));

            Context testContext = ApplicationProvider.getApplicationContext();
            SharedPreferences sharedPreferences = testContext.getSharedPreferences("data", Context.MODE_PRIVATE);

            assertEquals(name, sharedPreferences.getString("name", ""));
            assertEquals(Integer.parseInt(age), sharedPreferences.getInt("age", 0));
            assertEquals(Integer.parseInt(height), sharedPreferences.getInt("height", 0));
            assertEquals(Float.parseFloat(weight), sharedPreferences.getFloat("weight", 0), 0.0);
            assertEquals(Integer.parseInt(stepsGoal), sharedPreferences.getInt("stepsGoal", 0));

            InputViewModel inputViewModel = new InputViewModel();
            inputViewModel.initialize(testContext, sharedPreferences);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            calendar.add(Calendar.DAY_OF_MONTH, -2);
            Long twoDaysAgo = calendar.getTimeInMillis();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            int yesterdayDate = calendar.get(Calendar.DAY_OF_MONTH);
            Long yesterday = calendar.getTimeInMillis();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            int todayDate = calendar.get(Calendar.DAY_OF_MONTH);
            Long today = calendar.getTimeInMillis();

            DayData dayData1 = inputViewModel.createDayBasedOnSteps(twoDaysAgo, 6500);
            DayData dayData2 = inputViewModel.createDayBasedOnSteps(yesterday, 9500);
            DayData dayData3 = inputViewModel.createDayBasedOnSteps(today, 8500);


            DayDataDatabase testDb = Room.inMemoryDatabaseBuilder(
                            testContext,
                            DayDataDatabase.class
                    ).allowMainThreadQueries()
                    .build();

            DayDataDatabase.setInstance(testDb);

            testDb.dayDataDao().insert(dayData1);
            testDb.dayDataDao().insert(dayData2);
            testDb.dayDataDao().insert(dayData3);

            // Test History showing the elements

            Espresso.onView(ViewMatchers.withId(R.id.nav_history)).perform(click());
            onView(withId(R.id.historyRecyclerView))
                    .perform(scrollToPosition(0))
                    .check(matches(hasChildCount(3)));

            // Test Streaks
            // Cannot test streaks view using Espresso (Which date highlighted)
            // Limitation between Espresso & MaterialCalendarView
            DayDataDao dayDataDao = testDb.dayDataDao();
            IStreaksViewModel streaksViewModel = new StreaksViewModel(testDb, sharedPreferences);
            List<Date> currentStreak = streaksViewModel.getCurrentStreakDays();
            int streakCount = streaksViewModel.calculateStreak();

            calendar.setTime(currentStreak.get(1));
            int day1 = calendar.get(Calendar.DAY_OF_MONTH);

            calendar.setTime(currentStreak.get(0));
            int day2 = calendar.get(Calendar.DAY_OF_MONTH);

            assertEquals(yesterdayDate, day1);
            assertEquals(todayDate, day2);
            assertEquals(2, streakCount);

            // Move to settings
            Espresso.onView(ViewMatchers.withId(R.id.nav_settings)).perform(click());

            // Test correctly displaying current data of User
            Espresso.onView(ViewMatchers.withId(R.id.settingsName)).check(ViewAssertions.matches((ViewMatchers.withText(name))));
            Espresso.onView(ViewMatchers.withId(R.id.settingsAge)).check(ViewAssertions.matches((ViewMatchers.withText(age))));
            Espresso.onView(ViewMatchers.withId(R.id.settingsHeight)).check(ViewAssertions.matches((ViewMatchers.withText(height))));
            Espresso.onView(ViewMatchers.withId(R.id.settingsWeight)).check(ViewAssertions.matches((ViewMatchers.withText("88.0"))));
            Espresso.onView(ViewMatchers.withId(R.id.settingsStepsGoal)).check(ViewAssertions.matches((ViewMatchers.withText(stepsGoal))));


            // Bob changes his weight to 80
            Espresso.onView(ViewMatchers.withId(R.id.settingsWeight)).perform(ViewActions.clearText());
            Espresso.onView(ViewMatchers.withId(R.id.settingsWeight)).perform(ViewActions.typeText("80"));
            Espresso.closeSoftKeyboard();
            Espresso.onView(ViewMatchers.withId(R.id.btnSaveSettings)).perform(click());

            // Test data correctly changed
            UserProfileViewModel userProfileViewModel = new UserProfileViewModel();
            userProfileViewModel.initialize(sharedPreferences);
            UserProfile userProfile = userProfileViewModel.getUserProfile();

            assertEquals(80, userProfile.getWeight(), 0.1);

            // Move to Input
            Espresso.onView(ViewMatchers.withId(R.id.nav_input)).perform(click());
            Espresso.onView(ViewMatchers.withId(R.id.editTextDate)).perform(ViewActions.typeText("20203-06-05"));
            Espresso.onView(ViewMatchers.withId(R.id.etSteps)).perform(ViewActions.typeText("6000"));
            Espresso.closeSoftKeyboard();

            Espresso.onView(ViewMatchers.withId(R.id.btnSubmit)).perform(click());

            // Move to history
            // CHeck if now no. of past inputs is 4
            Espresso.onView(ViewMatchers.withId(R.id.nav_history)).perform(click());

            Thread.sleep(2000);

            onView(withId(R.id.historyRecyclerView))
                    .perform(scrollToPosition(0))
                    .check(matches(hasChildCount(4)));

        }
    }

