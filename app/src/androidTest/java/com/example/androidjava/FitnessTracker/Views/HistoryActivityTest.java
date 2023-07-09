package com.example.androidjava.FitnessTracker.Views;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.androidjava.R;

import org.junit.Rule;
import org.junit.Test;

public class HistoryActivityTest {
    @Rule
    public ActivityScenarioRule<HistoryActivity> activityScenarioRule
            = new ActivityScenarioRule<>(HistoryActivity.class);

    @Test
    public void allElementsPresentTest() {
        Espresso.onView(ViewMatchers.withId(R.id.historyRecyclerView)).check(matches(isDisplayed()));

    }

    @Test
    public void bottomNavigationFooterTest() {
        Intents.init();
        // Navigate to Main
        Espresso.onView(ViewMatchers.withId(R.id.nav_main)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(MainActivity.class.getName()));

        // Navigate back to history
        Espresso.onView(ViewMatchers.withId(R.id.nav_history)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(HistoryActivity.class.getName()));

        // Navigate to Streaks
        Espresso.onView(ViewMatchers.withId(R.id.nav_streaks)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(StreaksActivity.class.getName()));

        // Navigate back to history
        Espresso.onView(ViewMatchers.withId(R.id.nav_history)).perform(ViewActions.click());

        // Navigate to Settings
        Espresso.onView(ViewMatchers.withId(R.id.nav_settings)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(SettingsActivity.class.getName()));

        // Navigate back to history
        Espresso.onView(ViewMatchers.withId(R.id.nav_history)).perform(ViewActions.click());

        // Navigate to Input
        Espresso.onView(ViewMatchers.withId(R.id.nav_input)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(InputActivity.class.getName()));

        Intents.release();
    }

}
