package ru.urfu.droidpractice1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private var article2Read = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("LIFECYCLE", "MainActivity: onCreate")

        setContent {
            MainActivityScreen(
                article2Read = article2Read.value
            )
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d("LIFECYCLE", "MainActivity: onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.d("LIFECYCLE", "MainActivity: onResume")

        val preferences = getSharedPreferences(
            "article_preferences",
            MODE_PRIVATE
        )

        article2Read.value = preferences.getBoolean(
            "article_2_read",
            false
        )
    }

    override fun onPause() {
        super.onPause()

        Log.d("LIFECYCLE", "MainActivity: onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.d("LIFECYCLE", "MainActivity: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("LIFECYCLE", "MainActivity: onDestroy")
    }
}