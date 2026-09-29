package ru.urfu.droidpractice1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {
    private val TAG = "Lifecycle_MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Экран 1 создан")
        setContent {
            MainActivityScreen()
        }
}

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Экран 1 стал видимым")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Экран 1 стал активен (фокус получен)")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Экран 1 частично скрыт (пауза)")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Экран 1 полностью скрылся из вида")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Экран 1 уничтожен")
    }
}