package ru.urfu.droidpractice1

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.View
import android.widget.CompoundButton
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SecondActivityStateTest {
    @Test
    fun checkedValueIsReturnedOnBack() {
        ActivityScenario.launchActivityForResult<SecondActivity>(intent(false)).use { scenario ->
            scenario.onActivity { activity ->
                val switch = activity.findViewById<CompoundButton>(R.id.read_switch)
                assertFalse(switch.isChecked)
                switch.isChecked = true
                activity.onBackPressedDispatcher.onBackPressed()
            }
            assertEquals(RESULT_OK, scenario.result.resultCode)
            assertTrue(scenario.result.resultData.getBooleanExtra(SecondActivity.READ_EXTRA, false))
        }
    }

    @Test
    fun uncheckedValueSurvivesRecreationAndIsReturnedByButton() {
        ActivityScenario.launchActivityForResult<SecondActivity>(intent(true)).use { scenario ->
            scenario.onActivity { activity ->
                val switch = activity.findViewById<CompoundButton>(R.id.read_switch)
                assertTrue(switch.isChecked)
                switch.isChecked = false
            }
            scenario.recreate()
            scenario.onActivity { activity ->
                assertFalse(activity.findViewById<CompoundButton>(R.id.read_switch).isChecked)
                activity.findViewById<View>(R.id.back_button).performClick()
            }
            assertEquals(RESULT_OK, scenario.result.resultCode)
            assertFalse(scenario.result.resultData.getBooleanExtra(SecondActivity.READ_EXTRA, true))
        }
    }

    private fun intent(read: Boolean) =
        Intent(ApplicationProvider.getApplicationContext(), SecondActivity::class.java)
            .putExtra(SecondActivity.READ_EXTRA, read)
}
