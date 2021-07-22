package com.example.neaplus.ui;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import com.example.neaplus.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CategoryArticleTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void categoryArticleTest() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.business_button), withText("Business"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.horizontalScrollView),
                                        0),
                                0)));
        materialButton.perform(scrollTo(), click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction cardView = onView(
                allOf(withId(R.id.cardViewNews),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withId(R.id.listRecyclerView),
                                                0)),
                                0),
                        isDisplayed()));
        cardView.check(matches(isDisplayed()));

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.entertainment_button), withText("Entertainment"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.horizontalScrollView),
                                        0),
                                1)));
        materialButton2.perform(scrollTo(), click());

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction cardView1 = onView(
                allOf(withId(R.id.cardViewNews),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withId(R.id.listRecyclerView),
                                                0)),
                                0),
                        isDisplayed()));
        cardView1.check(matches(isDisplayed()));

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.general_button), withText("General"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.horizontalScrollView),
                                        0),
                                2)));
        materialButton3.perform(scrollTo(), click());

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction cardView2 = onView(
                allOf(withId(R.id.cardViewNews),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withId(R.id.listRecyclerView),
                                                0)),
                                0),
                        isDisplayed()));
        cardView2.check(matches(isDisplayed()));

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.health_button), withText("Health"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.horizontalScrollView),
                                        0),
                                3)));
        materialButton4.perform(scrollTo(), click());

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction cardView3 = onView(
                allOf(withId(R.id.cardViewNews),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withId(R.id.listRecyclerView),
                                                0)),
                                0),
                        isDisplayed()));
        cardView3.check(matches(isDisplayed()));

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.science_button), withText("Science"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.horizontalScrollView),
                                        0),
                                4)));
        materialButton5.perform(scrollTo(), click());

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction cardView4 = onView(
                allOf(withId(R.id.cardViewNews),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withId(R.id.listRecyclerView),
                                                0)),
                                0),
                        isDisplayed()));
        cardView4.check(matches(isDisplayed()));

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.sports_button), withText("Sports"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.horizontalScrollView),
                                        0),
                                5)));
        materialButton6.perform(scrollTo(), click());

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction cardView5 = onView(
                allOf(withId(R.id.cardViewNews),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withId(R.id.listRecyclerView),
                                                0)),
                                0),
                        isDisplayed()));
        cardView5.check(matches(isDisplayed()));

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.technology_button), withText("Technology"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.horizontalScrollView),
                                        0),
                                6)));
        materialButton7.perform(scrollTo(), click());

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction cardView6 = onView(
                allOf(withId(R.id.cardViewNews),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withId(R.id.listRecyclerView),
                                                0)),
                                0),
                        isDisplayed()));
        cardView6.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
