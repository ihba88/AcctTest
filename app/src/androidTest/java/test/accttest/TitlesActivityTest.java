package test.accttest;

/**
 * Created by Abhishek on 30/11/18.
 */

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class TitlesActivityTest {

    @Before
    public void before(){
        //initial setup code
    }

    @Rule
    public ActivityTestRule<TitlesActivity> mActivityTestRule = new  ActivityTestRule<TitlesActivity>(TitlesActivity.class);

    @Test
    public void checkRecyclerClick() throws InterruptedException {

        onView(withId(R.id.rlView)).check(matches(isDisplayed()));
        onView(withId(R.id.rlView)).perform(RecyclerViewActions.scrollToHolder(withHolderTimeView("voluptates delectus iure iste qui")));
        onView(withId(R.id.rlView)).perform(RecyclerViewActions.scrollToPosition(50));

    }

    @After
    public void after(){
        //clean up code
    }

    public static Matcher<RecyclerView.ViewHolder> withHolderTimeView(final String text) {
        return new BoundedMatcher<RecyclerView.ViewHolder, TitleAdapter.TitleViewHolder>(TitleAdapter.TitleViewHolder.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("No ViewHolder found with text: " + text);
            }

            @Override
            protected boolean matchesSafely(TitleAdapter.TitleViewHolder item) {
                TextView timeViewText = (TextView) item.itemView.findViewById(R.id.tvTitles);
                if (timeViewText == null) {
                    return false;
                }
                return timeViewText.getText().toString().contains(text);
            }
        };
    }


}