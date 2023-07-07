package com.example.androidjava.FitnessTracker.Viewmodels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import android.content.SharedPreferences;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.example.androidjava.FitnessTracker.Models.UserProfile;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserProfileViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private SharedPreferences sharedPreferences;
    @Mock
    private SharedPreferences.Editor editor;

    @Mock
    private Observer<String> observer;

    private UserProfileViewModel userProfileViewModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(sharedPreferences.edit()).thenReturn(editor);
        when(editor.putBoolean(anyString(), anyBoolean())).thenReturn(editor);

        userProfileViewModel = new UserProfileViewModel();
        userProfileViewModel.initialize(sharedPreferences);
        userProfileViewModel.getValidationMessage().observeForever(observer);
    }

    @Test
    public void validateNameGoodTest() {
        userProfileViewModel.validateName("Rein");
        verifyNoInteractions(observer);
    }

    @Test
    public void validateNameEmptyTest() {
        userProfileViewModel.validateName("");
        verify(observer).onChanged("Name field cannot be empty");
    }

    @Test
    public void validateAgeGoodTest() {
        userProfileViewModel.validateName("30");
        verifyNoInteractions(observer);
    }

    @Test
    public void validateAgeEmptyOrInvalidTest() {
        userProfileViewModel.validateAge("");
        verify(observer).onChanged("Age field cannot be empty");

        userProfileViewModel.validateAge("105");
        verify(observer).onChanged("Please enter a valid age");

        userProfileViewModel.validateAge("0");
        verify(observer, times(2)).onChanged("Please enter a valid age");
    }

    @Test
    public void validateHeightGoodTest() {
        userProfileViewModel.validateHeight("167");
        verifyNoInteractions(observer);
    }

    @Test
    public void validateHeightEmptyOrInvalidTest() {
        userProfileViewModel.validateHeight("");
        verify(observer).onChanged("Height field cannot be empty");

        userProfileViewModel.validateHeight("250");
        verify(observer).onChanged("Please enter a valid height");

        userProfileViewModel.validateHeight("0");
        verify(observer, times(2)).onChanged("Please enter a valid height");
    }

    @Test
    public void validateWeightGoodTest() {
        userProfileViewModel.validateHeight("100");
        verifyNoInteractions(observer);
    }

    @Test
    public void validateWeightEmptyOrInvalidTest() {
        userProfileViewModel.validateWeight("");
        verify(observer).onChanged("Weight field cannot be empty");

        userProfileViewModel.validateWeight("300");
        verify(observer).onChanged("Please enter a valid weight");

        userProfileViewModel.validateWeight("10");
        verify(observer, times(2)).onChanged("Please enter a valid weight");
    }

    @Test
    public void validateStepsGoalGoodTest() {
        userProfileViewModel.validateStepsGoal("5000");
        verifyNoInteractions(observer);
    }

    @Test
    public void validateStepsGoalEmptyOrInvalidTest() {
        userProfileViewModel.validateStepsGoal("");
        verify(observer).onChanged("Steps goal field cannot be empty");

        userProfileViewModel.validateStepsGoal("100000");
        verify(observer).onChanged("Please enter a valid steps goal");

        userProfileViewModel.validateStepsGoal("0");
        verify(observer, times(2)).onChanged("Please enter a valid steps goal");
    }

    @Test
    public void testLoadUserProfile() {
        String name = "Bob";
        int age = 20;
        int height = 180;
        float weight = 90.0f;
        int stepsGoal = 1000;

        when(sharedPreferences.getString("name", "")).thenReturn(name);
        when(sharedPreferences.getInt("age", 0)).thenReturn(age);
        when(sharedPreferences.getInt("height", 0)).thenReturn(height);
        when(sharedPreferences.getFloat("weight", 0)).thenReturn(weight);
        when(sharedPreferences.getInt("stepsGoal", 0)).thenReturn(stepsGoal);


        userProfileViewModel.initialize(sharedPreferences);
        UserProfile loadedUserProfile = userProfileViewModel.getUserProfile();
        assertEquals(name, loadedUserProfile.getName());
        assertEquals(age, loadedUserProfile.getAge());
        assertEquals(height, loadedUserProfile.getHeight());
        assertEquals(weight, loadedUserProfile.getWeight(), 0.01);
        assertEquals(stepsGoal, loadedUserProfile.getStepsGoal());
    }

    @Test
    public void setAndGetFirstRunFromRepoTest() {
        when(sharedPreferences.getBoolean("isFirstRun", true)).thenReturn(true);
        assertTrue(userProfileViewModel.getFirstRunFromRepo());

        userProfileViewModel.saveFirstRunToRepo(false);
        verify(editor).putBoolean("isFirstRun", false);
        verify(editor).apply();

        when(sharedPreferences.getBoolean("isFirstRun", true)).thenReturn(false);
        assertFalse(userProfileViewModel.getFirstRunFromRepo());

    }

}

