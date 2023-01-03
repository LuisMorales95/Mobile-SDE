package com.jlmp.mobilesdecodeexercise.presentacion

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jlmp.mobilesdecodeexercise.R
import com.jlmp.mobilesdecodeexercise.launchFragmentInHiltContainer
import com.jlmp.mobilesdecodeexercise.presentation.driver.DriverFragment
import com.jlmp.mobilesdecodeexercise.presentation.driver.DriverFragmentDirections
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class DriverFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    // single task rule
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun navigation_navigateToDriverDetailFragment() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        launchFragmentInHiltContainer<DriverFragment>() {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(this.view!!,navController)
            assertThat(navController.currentDestination?.id, IsEqual(R.id.driverFragment))
            navController.navigate(DriverFragmentDirections.actionDriverFragmentToDriverDetailFragment(1L))
            assertThat(navController.currentDestination?.id, IsEqual(R.id.driverDetailFragment))

        }
    }
}