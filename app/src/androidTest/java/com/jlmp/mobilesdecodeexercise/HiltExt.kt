package com.jlmp.mobilesdecodeexercise

import android.content.ComponentName
import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.jlmp.mobilesdecodeexerxise.HiltTestActivity
import dagger.hilt.internal.Preconditions
import kotlinx.coroutines.ExperimentalCoroutinesApi

const val THEME_EXTRAS_BUNDLE_KEY = "androidx.fragment.app.testing.FragmentScenario.EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY"
@ExperimentalCoroutinesApi
inline fun <reified T : Fragment> launchFragmentInHiltContainer(
    fragmentArgs: Bundle? = null,
    themeResId: Int = R.style.Theme_MobileSDECodeExercise,
    fragmentFactory: FragmentFactory? = null,
    initialState: Lifecycle.State = Lifecycle.State.RESUMED,
    crossinline action: T.() -> Unit = {}
) {
    val mainActivityIntent = makeMainActivity(
        ComponentName(
            ApplicationProvider.getApplicationContext(),
            HiltTestActivity::class.java
        )
    ).putExtra(THEME_EXTRAS_BUNDLE_KEY, themeResId)
    ActivityScenario.launch<HiltTestActivity>(mainActivityIntent).onActivity { activity ->
        fragmentFactory?.let {
            activity.supportFragmentManager.fragmentFactory = it
        }
        val fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
            Preconditions.checkNotNull(T::class.java.classLoader),
            T::class.java.name
        )
        fragment.arguments = fragmentArgs
        activity.supportFragmentManager.beginTransaction()
            .add(android.R.id.content, fragment, "")
            .setMaxLifecycle(fragment,initialState)
            .commitNow()
        (fragment as T).action()
    }
}