package com.example.androidjava;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.androidjava.FitnessTracker.Views.SetupActivity;

import org.junit.Rule;
import org.junit.Test;

public class TestTest {
    ViewInteraction viewInteraction;

    @Rule
    public ActivityScenarioRule<SetupActivity> activityScenarioRule
            = new ActivityScenarioRule<>(SetupActivity.class);

    @Test
    public void inputDataStillPresentAfterSwitchingPages() throws InterruptedException {

        // First page
        Espresso.onView(ViewMatchers.withId(R.id.inputName)).perform(ViewActions.typeText("Reinardus"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(ViewMatchers.withId(R.id.btnNext1)).perform(ViewActions.click());

        //Second page
        Espresso.onView(ViewMatchers.withId(R.id.inputAge)).perform(ViewActions.typeText("20"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(ViewMatchers.withId(R.id.inputHeight)).perform(ViewActions.typeText("180"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(ViewMatchers.withId(R.id.inputWeight)).perform(ViewActions.typeText("88"));
        Espresso.closeSoftKeyboard();


        Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(ViewActions.click());
        // Type in steps goal
        Espresso.onView(ViewMatchers.withId(R.id.inputSteps)).perform(ViewActions.typeText("10000"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.btnNext3)).perform(ViewActions.click());

        Thread.sleep(100);


        //Espresso.onView(ViewMatchers.withId(R.id.nav_input)).perform(ViewActions.click());

        //Espresso.onView(ViewMatchers.withId(R.id.btnNext3)).perform(ViewActions.click());



        Thread.sleep(1000000000);


/*        // Go back to second page, check input data still present there
        Espresso.onView(ViewMatchers.withId(R.id.btnBack3)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.inputAge)).check(ViewAssertions.matches((ViewMatchers.withText("20"))));
        Espresso.onView(ViewMatchers.withId(R.id.inputHeight)).check(ViewAssertions.matches((ViewMatchers.withText("180"))));
        Espresso.onView(ViewMatchers.withId(R.id.inputWeight)).check(ViewAssertions.matches((ViewMatchers.withText("88"))));

        // Go back to first page, check if name typed still there
        Espresso.onView(ViewMatchers.withId(R.id.btnBack2)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.inputName)).check(ViewAssertions.matches((ViewMatchers.withText("Reinardus"))));


        // GO forward again to third page check step goal
        Espresso.onView(ViewMatchers.withId(R.id.btnNext1)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.inputSteps)).check(ViewAssertions.matches((ViewMatchers.withText("10000"))));*/
    }

}
