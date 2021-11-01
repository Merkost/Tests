package com.geekbrains.tests

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

internal const val TEST_NUMBER = 42
internal const val TEST_NUMBER_OF_RESULTS_ZERO = "Number of results: 0"
internal const val TEST_NUMBER_OF_RESULTS_PLUS_1 = "Number of results: 1"
internal const val TEST_NUMBER_OF_RESULTS_MINUS_1 = "Number of results: -1"

fun delay(): ViewAction? {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> = ViewMatchers.isRoot()
        override fun getDescription(): String = "wait for $2 seconds"
        override fun perform(uiController: UiController, v: View?) {
            uiController.loopMainThreadForAtLeast(2000)
        }
    }
}