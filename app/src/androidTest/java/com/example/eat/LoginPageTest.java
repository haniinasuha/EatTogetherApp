package com.example.eat;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginPageTest {
    private FragmentScenario<CreatePlanFragment> fragmentScenario;
    @Rule
    public ActivityScenarioRule<LoginActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.eat", appContext.getPackageName());
    }

    @Test
    public void testPasswordField(){
        String email = "jfshenyik@gmail.com";
        String password = "2406_Jason";
        onView(withId(R.id.password)).perform(typeText(password));
        onView(withId(R.id.password)).check(matches(withText(password)));
    }

    @Test
    public void testLoginField(){
        String email = "jfshenyik@gmail.com";
        String password = "2406_Jason";
        onView(withId(R.id.email)).perform(typeText(email));
        onView(withId(R.id.email)).check(matches(withText(email)));
    }

    @Test
    public void testLoginBtn(){
        String email = "jfshenyik@gmail.com";
        String password = "2406_Jason";
        onView(withId(R.id.email)).perform(typeText(email));
        onView(withId(R.id.password)).perform(typeText(password));
        onView(withId(R.id.btn_login)).perform(click());
    }
}