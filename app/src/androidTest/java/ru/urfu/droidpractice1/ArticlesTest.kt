package ru.urfu.droidpractice1

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.content.res.Configuration
import android.content.pm.ActivityInfo
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import org.hamcrest.Matchers.not
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class ArticlesTest {
    @get:Rule val compose = createAndroidComposeRule<MainActivity>()

    @Test fun countersSurviveActivityRecreation() {
        compose.onNodeWithTag("like").performScrollTo().performClick().performClick()
        compose.onNodeWithTag("dislike").performScrollTo().performClick()
        // Temporarily unlock orientation in the test; the production manifest stays portrait.
        compose.activityRule.scenario.onActivity {
            assertEquals(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT, it.requestedOrientation)
            it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        compose.waitUntil(10_000) {
            compose.activity.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        }
        compose.onNodeWithTag("like").performScrollTo().assertTextEquals("Нравится · 2")
        compose.onNodeWithTag("dislike").performScrollTo().assertTextEquals("Не нравится · 1")
        compose.activityRule.scenario.onActivity {
            it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        compose.waitUntil(10_000) {
            compose.activity.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        }
        compose.activityRule.scenario.recreate()
        compose.onNodeWithTag("like").performScrollTo().assertTextEquals("Нравится · 2")
        compose.onNodeWithTag("dislike").assertTextEquals("Не нравится · 1")
        compose.activityRule.scenario.onActivity {
            assertEquals(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT, it.requestedOrientation)
        }
    }

    @Test fun readSwitchSurvivesRecreationAndReturnsBothTrueAndFalse() {
        compose.onNodeWithTag("like").performScrollTo().performClick()
        compose.onNodeWithTag("next_article").performScrollTo().performClick()
        onView(withId(R.id.read_switch)).perform(scrollTo()).check(matches(not(isChecked())))
            .perform(click())
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        instrumentation.runOnMainSync {
            val second = ActivityLifecycleMonitorRegistry.getInstance()
                .getActivitiesInStage(Stage.RESUMED).filterIsInstance<SecondActivity>().single()
            assertEquals(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT, second.requestedOrientation)
            second.recreate()
        }
        instrumentation.waitForIdleSync()
        onView(withId(R.id.read_switch)).perform(scrollTo()).check(matches(isChecked()))
        onView(withContentDescription(R.string.back)).perform(click())
        compose.onNodeWithTag("read_status").performScrollTo().assertTextEquals("✓ Прочитано")
        compose.onNodeWithTag("like").performScrollTo().assertTextEquals("Нравится · 1")
        compose.activityRule.scenario.recreate()
        compose.onNodeWithTag("read_status").performScrollTo().assertTextEquals("✓ Прочитано")
        compose.onNodeWithTag("next_article").performScrollTo().performClick()
        onView(withId(R.id.read_switch)).perform(scrollTo()).check(matches(isChecked())).perform(click())
        pressBack()
        compose.onNodeWithTag("read_status").performScrollTo().assertTextEquals("Ещё не прочитано")
        compose.onNodeWithTag("next_article").performScrollTo().performClick()
        onView(withId(R.id.read_switch)).perform(scrollTo()).check(matches(not(isChecked())))
        onView(withId(R.id.back_button)).perform(scrollTo(), click())
        compose.onNodeWithTag("read_status").performScrollTo().assertTextEquals("Ещё не прочитано")
    }

    @Suppress("DEPRECATION")
    @Test fun shareSendsTheWholeArticleAsPlainText() {
        Intents.init()
        try {
            intending(hasAction(Intent.ACTION_CHOOSER)).respondWith(
                Instrumentation.ActivityResult(Activity.RESULT_CANCELED, null))
            compose.onNodeWithTag("share").performScrollTo().performClick()
            intended(hasAction(Intent.ACTION_CHOOSER))
            val chooser = Intents.getIntents().single { it.action == Intent.ACTION_CHOOSER }
            val send = chooser.getParcelableExtra<Intent>(Intent.EXTRA_INTENT)!!
            assertEquals(Intent.ACTION_SEND, send.action)
            assertEquals("text/plain", send.type)
            val text = send.getStringExtra(Intent.EXTRA_TEXT)!!
            listOf(R.string.article_title, R.string.article_lead, R.string.article_heading_one,
                R.string.article_body_one, R.string.article_quote, R.string.article_heading_two,
                R.string.article_body_two, R.string.article_body_three).forEach {
                assertTrue("Missing article section: $it", text.contains(compose.activity.getString(it)))
            }
        } finally {
            Intents.release()
        }
    }
}
