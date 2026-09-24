package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private var articleRead by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContent {
            MainActivityScreen(
                articleRead = articleRead,
                onOpenSecondArticle = ::openSecondArticle
            )
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        articleRead = isSecondArticleRead
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    private fun openSecondArticle() {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(SecondActivity.EXTRA_READ, articleRead)
        startActivity(intent)
    }

    private val isSecondArticleRead: Boolean
        get() = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
            .getBoolean(KEY_SECOND_READ, false)

    companion object {
        private const val TAG = "MainActivity"
        private const val PREFS_NAME = "article_prefs"
        private const val KEY_SECOND_READ = "second_article_read"
    }
}
