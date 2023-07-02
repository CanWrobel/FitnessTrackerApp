package com.example.androidjava;

import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.androidjava.FitnessTracker.Views.SetupActivity;

import org.junit.Rule;
import org.junit.Test;

//import UserStoryData;

public class TestTest {
    ViewInteraction viewInteraction;

    @Rule
    public ActivityScenarioRule<SetupActivity> activityScenarioRule
            = new ActivityScenarioRule<>(SetupActivity.class);

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


        Espresso.onView(withId(R.id.editTextDate)).perform(ViewActions.typeText("2023-06-30")); Espresso.onView(withId(R.id.etSteps)).perform(ViewActions.typeText("1000")); Espresso.onView(withId(R.id.btnSubmit)).perform(ViewActions.click()); Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextDate)).perform(ViewActions.typeText("2023-06-29")); Espresso.onView(withId(R.id.etSteps)).perform(ViewActions.typeText("1000")); Espresso.onView(withId(R.id.btnSubmit)).perform(ViewActions.click()); Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextDate)).perform(ViewActions.typeText("2023-06-28")); Espresso.onView(withId(R.id.etSteps)).perform(ViewActions.typeText("1000")); Espresso.onView(withId(R.id.btnSubmit)).perform(ViewActions.click()); Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextDate)).perform(ViewActions.typeText("2023-06-27")); Espresso.onView(withId(R.id.etSteps)).perform(ViewActions.typeText("1000")); Espresso.onView(withId(R.id.btnSubmit)).perform(ViewActions.click()); Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextDate)).perform(ViewActions.typeText("2023-06-26")); Espresso.onView(withId(R.id.etSteps)).perform(ViewActions.typeText("1000")); Espresso.onView(withId(R.id.btnSubmit)).perform(ViewActions.click()); Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.nav_history)).perform(ViewActions.click());

        Thread.sleep(300);



        Espresso.onView(withId(R.id.nav_streaks)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.calendar)).perform(swipeRight());

        Thread.sleep(300);

        Espresso.onView(ViewMatchers.withId(R.id.nav_settings)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.settingsWeight)).perform(ViewActions.clearText());

        Thread.sleep(50);

        Espresso.onView(withId(R.id.settingsWeight)).perform(ViewActions.typeText("69")); Espresso.closeSoftKeyboard();

        Thread.sleep(50);

        Espresso.onView(ViewMatchers.withId(R.id.btnSaveSettings)).perform(ViewActions.click());


        Espresso.onView(ViewMatchers.withId(R.id.nav_history)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.nav_settings)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.settingsStepsGoal)).perform(ViewActions.clearText());         Espresso.onView(withId(R.id.settingsStepsGoal)).perform(ViewActions.typeText("2000"));  Espresso.closeSoftKeyboard();
        Espresso.onView(ViewMatchers.withId(R.id.btnSaveSettings)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.nav_streaks)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.calendar)).perform(swipeRight());

        Thread.sleep(300);

        Espresso.onView(ViewMatchers.withId(R.id.nav_settings)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.settingsStepsGoal)).perform(ViewActions.clearText());         Espresso.onView(withId(R.id.settingsStepsGoal)).perform(ViewActions.typeText("100"));  Espresso.closeSoftKeyboard();
        Espresso.onView(ViewMatchers.withId(R.id.btnSaveSettings)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.nav_streaks)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.calendar)).perform(swipeRight());







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
