package ru.urfu.droidpractice1

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Rule
import org.junit.Test

class ArticleFlowTest {
    @get:Rule
    val compose = createAndroidComposeRule<MainActivity>()

    @Test
    fun countersSurviveRecreationAndReadStatusTravelsBetweenActivities() {
        compose.onNodeWithText("Лайк · 0").performScrollTo().performClick()
        compose.onNodeWithText("Дизлайк · 0").performClick()
        compose.activityRule.scenario.recreate()
        compose.onNodeWithText("Лайк · 1").performScrollTo().assertIsDisplayed()
        compose.onNodeWithText("Дизлайк · 1").assertIsDisplayed()
        compose.onNodeWithText("Открыть статью →").performScrollTo().performClick()
        onView(withId(R.id.readSwitch)).perform(scrollTo(), click())
        pressBack()
        compose.onNodeWithText("Прочитано ✓").performScrollTo().assertIsDisplayed()
        compose.activityRule.scenario.recreate()
        compose.onNodeWithText("Прочитано ✓").performScrollTo().assertIsDisplayed()
        compose.onNodeWithText("Открыть статью →").performScrollTo().performClick()
        onView(withId(R.id.readSwitch)).check(matches(isChecked())).perform(scrollTo(), click())
        pressBack()
        compose.onNodeWithText("Ещё не прочитано").performScrollTo().assertIsDisplayed()
        compose.onNodeWithText("Лайк · 1").performScrollTo().assertIsDisplayed()
        compose.onNodeWithText("Дизлайк · 1").assertIsDisplayed()
    }
}
