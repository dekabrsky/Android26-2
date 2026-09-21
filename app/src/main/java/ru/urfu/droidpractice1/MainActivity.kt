package ru.urfu.droidpractice1

import android.util.Log
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.activity.compose.setContent
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    companion object {
        private const val KEY_SECOND_ARTICLE_READ = "second_article_read"
        private const val TAG = "MainActivity"
    }

    private var secondArticleRead by mutableStateOf(false)

    private val secondActivityLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                secondArticleRead =
                    result.data?.getBooleanExtra(
                        SecondActivity.EXTRA_ARTICLE_READ,
                        secondArticleRead
                    ) ?: secondArticleRead
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate")
        super.onCreate(savedInstanceState)

        secondArticleRead = savedInstanceState?.getBoolean(KEY_SECOND_ARTICLE_READ, false) ?: false

        setContent {
            MainActivityScreen(
                secondArticleRead = secondArticleRead,
                onOpenSecondArticle = {
                    val intent = Intent(this, SecondActivity::class.java).apply {
                        putExtra(SecondActivity.EXTRA_ARTICLE_READ, secondArticleRead)
                    }

                    secondActivityLauncher.launch(intent)
                }
            )
        }
    }

    override fun onStart() {
        Log.i(TAG, "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.i(TAG, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.i(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i(TAG, "onStop")
        super.onStop()
    }

    override fun onRestart() {
        Log.i(TAG, "onRestart")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i(TAG, "onSaveInstanceState")
        outState.putBoolean(KEY_SECOND_ARTICLE_READ, secondArticleRead)
        super.onSaveInstanceState(outState)
    }
}