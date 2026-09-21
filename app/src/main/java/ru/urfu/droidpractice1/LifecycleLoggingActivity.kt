package ru.urfu.droidpractice1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity

abstract class LifecycleLoggingActivity : ComponentActivity() {

    private val logTag: String
        get() = "Lifecycle/${javaClass.simpleName}"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(logTag, "onCreate (savedInstanceState=${if (savedInstanceState == null) "null" else "есть"})")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        Log.d(logTag, "onStart")
        super.onStart()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d(logTag, "onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onResume() {
        Log.d(logTag, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(logTag, "onPause")
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(logTag, "onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        Log.d(logTag, "onStop")
        super.onStop()
    }

    override fun onRestart() {
        Log.d(logTag, "onRestart")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.d(
            logTag,
            "onDestroy (isFinishing=$isFinishing, isChangingConfigurations=$isChangingConfigurations)",
        )
        super.onDestroy()
    }
}
