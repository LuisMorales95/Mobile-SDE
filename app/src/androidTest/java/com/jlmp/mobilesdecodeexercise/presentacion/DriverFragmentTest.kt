package com.jlmp.mobilesdecodeexercise.presentacion

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jlmp.mobilesdecodeexercise.R
import com.jlmp.mobilesdecodeexercise.presentation.driver.DriverFragment
import com.jlmp.mobilesdecodeexercise.presentation.driver.DriverFragmentDirections
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class DriverFragmentTest {

    @Test
    fun clickDriver_navigateToDetailFragment() {
        val driver = "Everardo Welch"
        val scenario = launchFragmentInContainer<DriverFragment>(
            fragmentArgs = Bundle(),
            themeResId = R.style.Theme_MobileSDECodeExercise
        )
        val navController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
        Espresso.onView(withId(R.id.recycler)).perform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                ViewMatchers.hasDescendant(ViewMatchers.withText(driver)),
                ViewActions.click()
            )
        )
        verify(navController).navigate(
            DriverFragmentDirections.actionDriverFragmentToDriverDetailFragment(1L)
        )
    }
}