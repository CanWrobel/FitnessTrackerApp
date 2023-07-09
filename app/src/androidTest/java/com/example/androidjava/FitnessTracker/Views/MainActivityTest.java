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

public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void allElementsPresentTest() {
        Espresso.onView(ViewMatchers.withId(R.id.progress_text)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.progress_bar)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.btnShowDistance)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.btnShowCalories)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.btnShowSteps)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.btnEndDay)).check(matches(isDisplayed()));

    }


    @Test
    public void buttonsToShowProgressTest() {
        Espresso.onView(ViewMatchers.withId(R.id.progress_text)).check(matches(withText("0")));
        Espresso.onView(ViewMatchers.withId(R.id.btnShowDistance)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.progress_text)).check(matches(withText("0.0 km")));
        Espresso.onView(ViewMatchers.withId(R.id.btnShowSteps)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.progress_text)).check(matches(withText("0")));
        Espresso.onView(ViewMatchers.withId(R.id.btnShowCalories)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.progress_text)).check(matches(withText("0 kcal")));
        Espresso.onView(ViewMatchers.withId(R.id.btnShowCalories)).perform(ViewActions.click());


    }

    @Test
    public void bottomNavigationFooterTest() {
        Intents.init();
        // Navigate to History
        Espresso.onView(ViewMatchers.withId(R.id.nav_history)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(HistoryActivity.class.getName()));

        // Navigate back to main
        Espresso.onView(ViewMatchers.withId(R.id.nav_main)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(SettingsActivity.class.getName()));

        // Navigate to Streaks
        Espresso.onView(ViewMatchers.withId(R.id.nav_streaks)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(StreaksActivity.class.getName()));

        // Navigate back to main
        Espresso.onView(ViewMatchers.withId(R.id.nav_main)).perform(ViewActions.click());

        // Navigate to Settings
        Espresso.onView(ViewMatchers.withId(R.id.nav_settings)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(SettingsActivity.class.getName()));

        // Navigate back to main
        Espresso.onView(ViewMatchers.withId(R.id.nav_main)).perform(ViewActions.click());

        // Navigate to Input
        Espresso.onView(ViewMatchers.withId(R.id.nav_input)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(InputActivity.class.getName()));

        Intents.release();
    }
}
