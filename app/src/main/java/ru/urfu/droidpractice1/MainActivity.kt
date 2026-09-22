package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivityLifecycle"

        private const val PREFS_NAME = "article_state"
        private const val PREF_LIKES = "likes"
        private const val PREF_DISLIKES = "dislikes"
        private const val PREF_SECOND_ARTICLE_READ = "second_article_read"

        private const val STATE_LIKES = "state_likes"
        private const val STATE_DISLIKES = "state_dislikes"
        private const val STATE_SECOND_ARTICLE_READ = "state_second_article_read"
    }

    private val prefs by lazy {
        getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    private var likes by mutableIntStateOf(0)
    private var dislikes by mutableIntStateOf(0)
    private var secondArticleRead by mutableStateOf(false)

    private val secondArticleLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                secondArticleRead =
                    result.data?.getBooleanExtra(
                        SecondActivity.EXTRA_IS_READ,
                        secondArticleRead
                    ) ?: secondArticleRead

                prefs.edit()
                    .putBoolean(PREF_SECOND_ARTICLE_READ, secondArticleRead)
                    .apply()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate")

        likes =
            savedInstanceState?.getInt(STATE_LIKES)
                ?: prefs.getInt(PREF_LIKES, 0)

        dislikes =
            savedInstanceState?.getInt(STATE_DISLIKES)
                ?: prefs.getInt(PREF_DISLIKES, 0)

        secondArticleRead =
            savedInstanceState?.getBoolean(STATE_SECOND_ARTICLE_READ)
                ?: prefs.getBoolean(PREF_SECOND_ARTICLE_READ, false)

        setContent {
            DroidPractice1Theme {
                MainActivityScreen(
                    likes = likes,
                    dislikes = dislikes,
                    secondArticleRead = secondArticleRead,
                    onLikeClick = {
                        likes++
                        saveCounters()
                    },
                    onDislikeClick = {
                        dislikes++
                        saveCounters()
                    },
                    onOpenSecondArticle = {
                        openSecondArticle()
                    }
                )
            }
        }
    }

    private fun saveCounters() {
        prefs.edit()
            .putInt(PREF_LIKES, likes)
            .putInt(PREF_DISLIKES, dislikes)
            .apply()
    }

    private fun openSecondArticle() {
        val intent = Intent(
            this,
            SecondActivity::class.java
        ).apply {
            putExtra(
                SecondActivity.EXTRA_IS_READ,
                secondArticleRead
            )
        }

        secondArticleLauncher.launch(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_LIKES, likes)
        outState.putInt(STATE_DISLIKES, dislikes)
        outState.putBoolean(STATE_SECOND_ARTICLE_READ, secondArticleRead)

        super.onSaveInstanceState(outState)
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
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }
}