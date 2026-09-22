package ru.urfu.droidpractice1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("LifeCycle", "MainActivity onCreate")

        setContent {
            MainActivityScreen()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("LifeCycle", "MainActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle", "MainActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycle", "MainActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycle", "MainActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle", "MainActivity onDestroy")
    }
}