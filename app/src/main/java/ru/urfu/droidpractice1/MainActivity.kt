package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivity"
        const val EXTRA_IS_READ = "extra_is_read"
        private const val KEY_FIRST_READ = "key_first_article_read"
        private const val KEY_SECOND_READ = "key_second_article_read"
    }

    private val isFirstArticleRead = mutableStateOf(false)
    private val isSecondArticleRead = mutableStateOf(false)
    private var firstArticleLikes = 0
    private var firstArticleDislikes = 0

    private val firstArticleLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val read = result.data?.getBooleanExtra(
                FirstArticleActivity.EXTRA_IS_READ, false
            ) ?: false
            isFirstArticleRead.value = read
            firstArticleLikes = result.data?.getIntExtra(
                FirstArticleActivity.EXTRA_LIKES, 0
            ) ?: 0
            firstArticleDislikes = result.data?.getIntExtra(
                FirstArticleActivity.EXTRA_DISLIKES, 0
            ) ?: 0
        }
    }

    private val secondActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val read = result.data?.getBooleanExtra(EXTRA_IS_READ, false) ?: false
            isSecondArticleRead.value = read
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        if (savedInstanceState != null) {
            isFirstArticleRead.value = savedInstanceState.getBoolean(KEY_FIRST_READ, false)
            isSecondArticleRead.value = savedInstanceState.getBoolean(KEY_SECOND_READ, false)
            firstArticleLikes = savedInstanceState.getInt("key_first_likes", 0)
            firstArticleDislikes = savedInstanceState.getInt("key_first_dislikes", 0)
        }

        setContent {
            MainActivityScreen(
                isFirstArticleRead = isFirstArticleRead.value,
                isSecondArticleRead = isSecondArticleRead.value,
                onNavigateToFirst = {
                    val intent = Intent(this, FirstArticleActivity::class.java)
                    intent.putExtra(
                        FirstArticleActivity.EXTRA_IS_READ,
                        isFirstArticleRead.value
                    )
                    intent.putExtra(FirstArticleActivity.EXTRA_LIKES, firstArticleLikes)
                    intent.putExtra(FirstArticleActivity.EXTRA_DISLIKES, firstArticleDislikes)
                    firstArticleLauncher.launch(intent)
                },
                onNavigateToSecond = {
                    val intent = Intent(this, SecondActivity::class.java)
                    intent.putExtra(EXTRA_IS_READ, isSecondArticleRead.value)
                    secondActivityLauncher.launch(intent)
                }
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_FIRST_READ, isFirstArticleRead.value)
        outState.putBoolean(KEY_SECOND_READ, isSecondArticleRead.value)
        outState.putInt("key_first_likes", firstArticleLikes)
        outState.putInt("key_first_dislikes", firstArticleDislikes)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
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
}