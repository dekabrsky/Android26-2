package ru.urfu.droidpractice1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity

abstract class LoggedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(javaClass.simpleName, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(javaClass.simpleName, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(javaClass.simpleName, "onResume")
    }

    override fun onPause() {
        Log.d(javaClass.simpleName, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(javaClass.simpleName, "onStop")
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(javaClass.simpleName, "onRestart")
    }

    override fun onDestroy() {
        Log.d(javaClass.simpleName, "onDestroy")
        super.onDestroy()
    }
}
