package ru.urfu.droidpractice1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {
    private var articleRead = mutableStateOf(false);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "MainActivity:onCreate")
        articleRead.value = savedInstanceState?.getBoolean("article_read", false) ?: false;

        val startArticle = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                articleRead.value = result.data?.getBooleanExtra("article_read", false) ?: false;
            }
        }
        setContent {
            MainActivityScreen(articleRead.value) { intent ->
                startArticle.launch(intent)
            }
        }
    }

    // Упражнение 1
    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "MainActivity:onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "MainActivity:onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "MainActivity:onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Lifecycle", "MainActivity:onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "MainActivity:onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("Lifecycle", "MainActivity:onSaveInstanceState")

        outState.putBoolean("article_read", articleRead.value);
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("Lifecycle", "MainActivity:onRestoreInstanceState")
    }
}