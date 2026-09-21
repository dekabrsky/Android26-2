package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {
    private var isSecondArticleRead by mutableStateOf(false)

    private val secondArticleLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            isSecondArticleRead = result.data?.getBooleanExtra(
                SecondActivity.EXTRA_IS_READ,
                isSecondArticleRead
            ) ?: isSecondArticleRead
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "MainActivity: onCreate")
        isSecondArticleRead = savedInstanceState?.getBoolean(KEY_SECOND_ARTICLE_READ) ?: false
        setContent {
            MainActivityScreen(
                isSecondArticleRead = isSecondArticleRead,
                onOpenSecondArticle = {
                    val intent = Intent(this, SecondActivity::class.java).putExtra(
                        SecondActivity.EXTRA_IS_READ,
                        isSecondArticleRead
                    )
                    secondArticleLauncher.launch(intent)
                }
            )
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "MainActivity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "MainActivity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "MainActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "MainActivity: onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "MainActivity: onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MainActivity: onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(KEY_SECOND_ARTICLE_READ, isSecondArticleRead)
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val TAG = "MainActivityLifecycle"
        private const val KEY_SECOND_ARTICLE_READ = "key_second_article_read"
    }
}
