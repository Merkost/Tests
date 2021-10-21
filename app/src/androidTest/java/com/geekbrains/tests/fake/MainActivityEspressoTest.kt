package com.geekbrains.tests.fake

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.view.search.MainActivity
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import android.widget.TextView

import androidx.test.espresso.matcher.BoundedMatcher
import com.geekbrains.tests.R
import org.hamcrest.Description


@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun progressBarView_isGone() {
        onView(withId(R.id.progressBar)).check(matches(not(isCompletelyDisplayed())))
    }

    @Test
    fun toDetailsActivityButton_isTextCorrect() {
        onView(withId(R.id.toDetailsActivityButton)).check(matches(withText(R.string.to_details)))
    }

    @Test
    fun totalCountTextView_isInvisible() {
        onView(withId(R.id.totalCountTextView)).check(matches(not(isDisplayed())))
    }

    @Test
    fun toDetailsActivityButton_isTextSizeCorrect() {
        onView(withId(R.id.totalCountTextView)).check(matches(withFontSize(14)))
    }

    @Test
    fun toDetailsActivityButton_isButtonStyleCorrect() {
        onView(withId(R.id.totalCountTextView)).check(matches(hasTextColor(R.attr.materialButtonStyle))) //doesn't work -> need to add specific color in colors.xml
    }

    @Test
    fun activitySearch_IsWorkingCorrect() {
        onView(withId(R.id.searchEditText)).perform(click())
        onView(withId(R.id.searchEditText)).perform(replaceText("algol"), closeSoftKeyboard())
        onView(withId(R.id.searchEditText)).perform(pressImeActionButton())
        
        onView(withId(R.id.totalCountTextView)).check(matches(withText("Number of results: 42")))
    }

    private fun delay(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $2 seconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(2000)
            }
        }
    }

    fun withFontSize(expectedSize: Int): Matcher<View?> {
        return object : BoundedMatcher<View?, View>(View::class.java) {
            override fun matchesSafely(target: View): Boolean {
                if (target !is TextView) {
                    return false
                }
                val pixels = target.textSize
                val actualSize = Math.round(pixels / target.getResources().displayMetrics.scaledDensity)
                return java.lang.Integer.compare(actualSize, expectedSize) == 0
            }

            override fun describeTo(description: Description) {
                description.appendText("with fontSize: ")
                description.appendValue(expectedSize)
            }
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}
