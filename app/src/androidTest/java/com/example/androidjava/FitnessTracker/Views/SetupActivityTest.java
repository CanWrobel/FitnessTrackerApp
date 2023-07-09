package com.example.androidjava.FitnessTracker.Views;


import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.mockito.Mockito.when;

import android.content.SharedPreferences;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.androidjava.FitnessTracker.Viewmodels.UserProfileViewModel;
import com.example.androidjava.FitnessTracker.Views.SetupActivity;
import com.example.androidjava.R;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

// Test for SetupActivity
// Requirements: Run in a device opening the app the very first time
// In virtual device can first off perform wipe data before running
public class SetupActivityTest {

    @Rule
    public ActivityScenarioRule<SetupActivity> activityScenarioRule
            = new ActivityScenarioRule<>(SetupActivity.class);


   @Test
    public void allELementsPresentFirstPage() {
       Espresso.onView(ViewMatchers.withId(R.id.btnNext1)).check(matches(isDisplayed()));
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
   public void errorTextPresentEmptyInputsTest() {
       // First page
       Espresso.onView(ViewMatchers.withId(R.id.btnNext1)).perform(ViewActions.click());
       Espresso.onView(ViewMatchers.withId(R.id.inputName)).check(matches(hasErrorText("Name field cannot be empty")));

       Espresso.onView(ViewMatchers.withId(R.id.inputName)).perform(ViewActions.typeText("Reinardus"));
       Espresso.closeSoftKeyboard();
       Espresso.onView(ViewMatchers.withId(R.id.btnNext1)).perform(ViewActions.click());

       // Second page
       Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(ViewActions.click());
       Espresso.onView(ViewMatchers.withId(R.id.inputAge))
               .check(matches(hasErrorText("Age field cannot be empty")));
       Espresso.onView(ViewMatchers.withId(R.id.inputAge)).perform(ViewActions.typeText("20"));
       Espresso.closeSoftKeyboard();

       Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(ViewActions.click());
       Espresso.onView(ViewMatchers.withId(R.id.inputHeight))
               .check(matches(hasErrorText("Height field cannot be empty")));
       Espresso.onView(ViewMatchers.withId(R.id.inputHeight)).perform(ViewActions.typeText("180"));
       Espresso.closeSoftKeyboard();

       Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(ViewActions.click());
       Espresso.onView(ViewMatchers.withId(R.id.inputWeight))
               .check(matches(hasErrorText("Weight field cannot be empty")));
       Espresso.onView(ViewMatchers.withId(R.id.inputWeight)).perform(ViewActions.typeText("88"));
       Espresso.closeSoftKeyboard();


       Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(ViewActions.click());


       Espresso.onView(ViewMatchers.withId(R.id.btnNext3)).perform(ViewActions.click());
       Espresso.onView(ViewMatchers.withId(R.id.inputSteps))
               .check(matches(hasErrorText("Steps goal field cannot be empty")));


   }

    @Test
    public void errorTextPresentFaultyAgeInputTest() {
        // First page
        Espresso.onView(ViewMatchers.withId(R.id.inputName)).perform(ViewActions.typeText("Reinardus"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(ViewMatchers.withId(R.id.btnNext1)).perform(ViewActions.click());

        // Second page

        // Input age 0 and 105 cause error
        String ageErrorMessage = "Please enter a valid age";

        Espresso.onView(ViewMatchers.withId(R.id.inputAge)).perform(ViewActions.typeText("0"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.inputAge))
                .check(matches(hasErrorText(ageErrorMessage)));

        Espresso.onView(ViewMatchers.withId(R.id.inputAge)).perform(ViewActions.typeText("105"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.inputAge))
                .check(matches(hasErrorText(ageErrorMessage)));

    }

    @Test
    public void errorTextPresentFaultyHeightInputTest() {
        // First page setup
        Espresso.onView(ViewMatchers.withId(R.id.btnNext1)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.inputName)).check(matches(hasErrorText("Name field cannot be empty")));

        Espresso.onView(ViewMatchers.withId(R.id.inputName)).perform(ViewActions.typeText("Reinardus"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(ViewMatchers.withId(R.id.btnNext1)).perform(ViewActions.click());

        // Second page

        // Input age 0 and 105 cause error
        String heightErrorMessage = "Please enter a valid height";

        Espresso.onView(ViewMatchers.withId(R.id.inputAge)).perform(ViewActions.typeText("20"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputHeight)).perform(ViewActions.typeText("0"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.inputHeight))
                .check(matches(hasErrorText(heightErrorMessage)));

        Espresso.onView(ViewMatchers.withId(R.id.inputHeight)).perform(ViewActions.typeText("240"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.inputHeight))
                .check(matches(hasErrorText(heightErrorMessage)));

    }

    @Test
    public void errorTextPresentFaultyWeightInputTest() {
        // First page setup
        Espresso.onView(ViewMatchers.withId(R.id.btnNext1)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.inputName)).check(matches(hasErrorText("Name field cannot be empty")));

        Espresso.onView(ViewMatchers.withId(R.id.inputName)).perform(ViewActions.typeText("Reinardus"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(ViewMatchers.withId(R.id.btnNext1)).perform(ViewActions.click());

        // Second page

        // Input age 0 and 105 cause error
        String weightErrorMessage = "Please enter a valid weight";

        Espresso.onView(ViewMatchers.withId(R.id.inputAge)).perform(ViewActions.typeText("20"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputHeight)).perform(ViewActions.typeText("180"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.inputWeight)).perform(ViewActions.typeText("300"));
        Espresso.closeSoftKeyboard();


        Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.inputWeight))
                .check(matches(hasErrorText(weightErrorMessage)));

        Espresso.onView(ViewMatchers.withId(R.id.inputWeight)).perform(ViewActions.typeText("0"));
        Espresso.closeSoftKeyboard();


        Espresso.onView(ViewMatchers.withId(R.id.btnNext2)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.inputWeight))
                .check(matches(hasErrorText(weightErrorMessage)));

    }
}
