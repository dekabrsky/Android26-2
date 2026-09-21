package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {
    private var isSecondArticleRead by mutableStateOf(false)
    private val secondActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            isSecondArticleRead = data?.getBooleanExtra("EXTRA_IS_READ", false) ?: false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("Lifecycle", "MainActivity: onCreate")

        setContent {
            MainActivityScreen(
                isSecondArticleRead = isSecondArticleRead,
                onNavigateToSecond = {
                    val intent = Intent(this, SecondActivity::class.java).apply {
                        putExtra("EXTRA_IS_READ", isSecondArticleRead)
                    }
                    secondActivityLauncher.launch(intent)
                }
            )
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "MainActivity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "MainActivity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "MainActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "MainActivity: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "MainActivity: onDestroy")
    }
}