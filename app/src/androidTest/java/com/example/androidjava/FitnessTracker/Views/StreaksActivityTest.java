package com.example.androidjava.FitnessTracker.Views;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.androidjava.FitnessTracker.Views.HistoryActivity;
import com.example.androidjava.FitnessTracker.Views.MainActivity;
import com.example.androidjava.FitnessTracker.Views.SettingsActivity;
import com.example.androidjava.FitnessTracker.Views.StreaksActivity;
import com.example.androidjava.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class StreaksActivityTest {
    @Rule
    public ActivityScenarioRule<StreaksActivity> activityRule =
            new ActivityScenarioRule<>(StreaksActivity.class);

    @Test
    public void streaksAllElementsExistTest() {
        Espresso.onView(ViewMatchers.withId(R.id.tvStreakNumber))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.calendar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.bottom_navigation))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testBottomNavigationFooter() {
        Intents.init();
        // Navigate to History
        Espresso.onView(ViewMatchers.withId(R.id.nav_history)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(HistoryActivity.class.getName()));

        // Navigate back to Streaks
        Espresso.onView(ViewMatchers.withId(R.id.nav_streaks)).perform(ViewActions.click());

        // Navigate to Settings
        Espresso.onView(ViewMatchers.withId(R.id.nav_settings)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(SettingsActivity.class.getName()));

        // Navigate back to Streaks
        Espresso.onView(ViewMatchers.withId(R.id.nav_streaks)).perform(ViewActions.click());

        // Navigate to Main
        Espresso.onView(ViewMatchers.withId(R.id.nav_main)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(MainActivity.class.getName()));

        Intents.release();
    }
}

