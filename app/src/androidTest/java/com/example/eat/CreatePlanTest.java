package com.example.eat;

import android.content.Context;

import androidx.test.filters.LargeTest;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreatePlanTest {
    private FragmentScenario<CreatePlanFragment> fragmentScenario;
    @Rule
    public ActivityScenarioRule<CreatePlanActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(CreatePlanActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.eat", appContext.getPackageName());
    }

    @Test
    public void testMealTypeBtn(){
        String mealType = "Breakfast";
        String location = "Curl Market";
        String date = "06/24/2024";
        String time = "13:01pm";
        String spot = "3";
        String desc = "Having breakfast!";
        onView(withId(R.id.btn_createBreakfast)).perform(click());
        onView(withId(R.id.btn_createBreakfast)).check(matches(isEnabled()));
    }

    @Test
    public void testDescTypeBtn(){
        String mealType = "Breakfast";
        String location = "Curl Market";
        String date = "06/24/2024";
        String time = "13:01pm";
        String spot = "3";
        String desc = "Having breakfast!";
        onView(withId(R.id.txtInput_desc)).perform(typeText(desc));
        onView(withId(R.id.txtInput_desc)).check(matches(withText(desc)));
    }

}