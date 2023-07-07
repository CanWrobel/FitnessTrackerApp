package com.example.androidjava.FitnessTracker.Views;


import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.androidjava.FitnessTracker.Views.HistoryActivity;
import com.example.androidjava.FitnessTracker.Views.MainActivity;
import com.example.androidjava.FitnessTracker.Views.SettingsActivity;
import com.example.androidjava.FitnessTracker.Views.StreaksActivity;
import com.example.androidjava.R;

import org.junit.Rule;
import org.junit.Test;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;


public class SettingsActivityTest {

    @Rule
    public ActivityScenarioRule<SettingsActivity> mActivityRule =
            new ActivityScenarioRule<>(SettingsActivity.class);

    @Test
    public void allElementsPresentTest() {
        Espresso.onView(ViewMatchers.withId(R.id.headingSettings)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.settingsName)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.settingsAge)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.settingsHeight)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.settingsWeight)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.settingsStepsGoal)).check(matches(isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.btnSaveSettings)).check(matches(isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.bottom_navigation)).check(matches(isDisplayed()));
    }

    @Test
    public void testBottomNavigationFooter() {
        Intents.init();
        // Navigate to History
        Espresso.onView(ViewMatchers.withId(R.id.nav_history)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(HistoryActivity.class.getName()));

        // Navigate back to Settings
        Espresso.onView(ViewMatchers.withId(R.id.nav_settings)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(SettingsActivity.class.getName()));

        // Navigate to Streaks
        Espresso.onView(ViewMatchers.withId(R.id.nav_streaks)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(StreaksActivity.class.getName()));

        // Navigate back to Settings
        Espresso.onView(ViewMatchers.withId(R.id.nav_settings)).perform(ViewActions.click());

        // Navigate to Main
        Espresso.onView(ViewMatchers.withId(R.id.nav_main)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(MainActivity.class.getName()));

        Intents.release();
    }
}
