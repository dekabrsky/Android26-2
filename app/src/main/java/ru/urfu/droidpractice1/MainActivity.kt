package ru.urfu.droidpractice1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.urfu.droidpractice1.content.MainActivityScreen
import androidx.compose.runtime.mutableStateOf
import androidx.activity.result.contract.ActivityResultContracts
import android.util.Log

class MainActivity : ComponentActivity() {

    companion object {
        const val EXTRA_IS_READ = "isRead"
        private const val tag = "MainActivity"
    }
    val isSecondArticleRead = mutableStateOf(false)

    val secondActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val value = result.data?.getBooleanExtra(EXTRA_IS_READ, false) ?: false
                isSecondArticleRead.value = value
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainActivityScreen(
                isSecondArticleRead = isSecondArticleRead.value,
                startSecondArticle = { intent -> secondActivityLauncher.launch(intent)
                }
            )
        }
    }

    override fun onStart() {
        super.onStart(); Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume(); Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause(); Log.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop(); Log.d(tag, "onStop")
    }

    override fun onRestart() {
        super.onRestart(); Log.d(tag, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy(); Log.d(tag, "onDestroy")
    }
}