package com.example.neaplus.ui;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.neaplus.R;
import com.example.neaplus.core.resource.ArticleDao;
import com.example.neaplus.core.resource.NeaDatabase;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class BookmarksAddDeleteTest {
    private NeaDatabase db;
    private ArticleDao articleDao;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    // Override function setUp() and annotate it with @Before
    // this function will be called at first when this test class is called
    @Before
    public void setUp() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        db = NeaDatabase.getDatabase(appContext);
        articleDao = db.articleDao();
        articleDao.deleteAll();

    }

    // Override function closeDb() and annotate it with @After
    // this function will be called at last when this test class is called
    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void bookmarksAddDeleteTest() {

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_bookmark), withContentDescription("Bookmark"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.countArticle), withText("Show 0 news"),
                        withParent(withParent(withId(R.id.nav_host_fragment_activity_main))),
                        isDisplayed()));
        textView.check(matches(withText("Show 0 news")));

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.navigation_home), withContentDescription("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        try {
            Thread.sleep(2000);
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
        cardView.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.image_bookmark),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.image_back),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatImageView2.perform(click());


        ViewInteraction bottomNavigationItemView3 = onView(
                allOf(withId(R.id.navigation_bookmark), withContentDescription("Bookmark"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView3.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.countArticle), withText("Show 1 news"),
                        withParent(withParent(withId(R.id.nav_host_fragment_activity_main))),
                        isDisplayed()));
        textView2.check(matches(withText("Show 1 news")));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.news_delete),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.card_view),
                                        0),
                                3),
                        isDisplayed()));
        appCompatImageView3.perform(click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.countArticle), withText("Show 0 news"),
                        withParent(withParent(withId(R.id.nav_host_fragment_activity_main))),
                        isDisplayed()));
        textView3.check(matches(withText("Show 0 news")));
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
