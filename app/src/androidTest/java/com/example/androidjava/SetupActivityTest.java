package com.example.androidjava;


import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.androidjava.FitnessTracker.Views.SetupActivity;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

public class SetupActivityTest {

    ViewInteraction viewInteraction;

    @Rule
    public ActivityScenarioRule<SetupActivity> activityScenarioRule
            = new ActivityScenarioRule<>(SetupActivity.class);

   @Test
    public void allELementsPresentFirstPage() {
       Espresso.onView(withId(R.id.btnNext1)).check(matches(isDisplayed()));
       Espresso.onView(withId(R.id.inputNameLabel)).check(matches(isDisplayed()));
       Espresso.onView(withId(R.id.inputName)).check(matches(isDisplayed()));
   }


    @Test
    public void allELementsPresentSecondPage() {
       Espresso.onView(ViewMatchers.withId(R.id.inputName)).perform(ViewActions.typeText("Reinardus"));

       Espresso.closeSoftKeyboard();

       Espresso.onView(ViewMatchers.withId(R.id.btnNext1)).perform(ViewActions.click());


        Espresso.onView(withId(R.id.inputAgeLabel)).check(matches(isCompletelyDisplayed()));
        Espresso.onView(withId(R.id.inputAge)).check(matches(isDisplayed()));

        Espresso.onView(withId(R.id.inputHeightLabel)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.inputHeight)).check(matches(isDisplayed()));

        Espresso.onView(withId(R.id.inputWeightLabel)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.inputWeight)).check(matches(isDisplayed()));


        Espresso.onView(withId(R.id.btnBack2)).check(matches(isDisplayed()));
    }


    @Test
    public void allELementsPresentThirdPage() {

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

        // Third page check elements
        Espresso.onView(withId(R.id.inputStepsLabel)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.inputSteps)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.btnNext3)).check(matches(isDisplayed()));

    }

    @Test
    public void inputDataStillPresentAfterSwitchingPages() {

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


        // Go back to second page, check input data still present there
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
        Espresso.onView(ViewMatchers.withId(R.id.inputSteps)).check(ViewAssertions.matches((ViewMatchers.withText("10000"))));
   }

   @Test
   public void errorTextPresentTest() {
       // First page
       Espresso.onView(ViewMatchers.withId(R.id.btnNext1)).perform(ViewActions.click());
       Espresso.onView(ViewMatchers.withId(R.id.inputName)).check(matches(hasErrorText("Name cannot be empty")));

       Espresso.onView(ViewMatchers.withId(R.id.inputName)).perform(ViewActions.typeText("Reinardus"));
       Espresso.closeSoftKeyboard();
       Espresso.onView(ViewMatchers.withId(R.id.btnNext1)).perform(ViewActions.click());

       // Second page
       Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(ViewActions.click());
       Espresso.onView(ViewMatchers.withId(R.id.inputAge))
               .check(matches(hasErrorText("Age cannot be empty")));
       Espresso.onView(ViewMatchers.withId(R.id.inputAge)).perform(ViewActions.typeText("20"));
       Espresso.closeSoftKeyboard();

       Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(ViewActions.click());
       Espresso.onView(ViewMatchers.withId(R.id.inputHeight))
               .check(matches(hasErrorText("Height cannot be empty")));
       Espresso.onView(ViewMatchers.withId(R.id.inputHeight)).perform(ViewActions.typeText("180"));
       Espresso.closeSoftKeyboard();

       Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(ViewActions.click());
       Espresso.onView(ViewMatchers.withId(R.id.inputWeight))
               .check(matches(hasErrorText("Weight cannot be empty")));
       Espresso.onView(ViewMatchers.withId(R.id.inputWeight)).perform(ViewActions.typeText("88"));
       Espresso.closeSoftKeyboard();


       Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(ViewActions.click());


       Espresso.onView(ViewMatchers.withId(R.id.btnNext3)).perform(ViewActions.click());
       Espresso.onView(ViewMatchers.withId(R.id.inputSteps))
               .check(matches(hasErrorText("Steps Goal cannot be empty")));



   }
}
