package com.jlmp.mobilesdecodeexercise

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun openDriverDetail() {
        val driver = "Everardo Welch"
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.progress)).check(matches(not(isDisplayed())))
        onView(withId(R.id.recycler)).check(matches(hasDescendant(withText(driver))))
        onView(withId(R.id.recycler)).perform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                hasDescendant(withText(driver)),
                click()
            )
        )
        onView(withId(R.id.driver_text)).check(matches(withText(driver)))

        activityScenario.close()
    }
}