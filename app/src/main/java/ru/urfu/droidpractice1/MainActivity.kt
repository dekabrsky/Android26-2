package ru.urfu.droidpractice1

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

class MainActivity : ComponentActivity() {

    private var likes by mutableIntStateOf(0)
    private var dislikes by mutableIntStateOf(0)
    private var secondArticleRead by mutableStateOf(false)

    private val secondArticleLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            secondArticleRead = result.data?.getBooleanExtra(
                SecondActivity.EXTRA_IS_READ,
                secondArticleRead
            ) ?: secondArticleRead
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        likes = savedInstanceState?.getInt(KEY_LIKES) ?: 0
        dislikes = savedInstanceState?.getInt(KEY_DISLIKES) ?: 0

        secondArticleRead =
            savedInstanceState?.getBoolean(KEY_READ) ?: false

        setContent {
            MainActivityScreen(
                likes = likes,
                dislikes = dislikes,
                secondArticleRead = secondArticleRead,
                onLike = { likes++ },
                onDislike = { dislikes++ },
                onShare = { shareArticle() },
                onOpenSecondArticle = { openSecondArticle() }
            )
        }
    }

    private fun openSecondArticle() {
        val nextActivity = Intent(
            this,
            SecondActivity::class.java
        ).apply {
            putExtra(
                SecondActivity.EXTRA_IS_READ,
                secondArticleRead
            )
        }

        secondArticleLauncher.launch(nextActivity)
    }

    private fun shareArticle() {
        val articleText = listOf(
            R.string.first_article_title,
            R.string.first_article_lead,
            R.string.first_article_paragraph_1,
            R.string.first_article_subtitle,
            R.string.first_article_paragraph_2
        ).joinToString("\n\n") { getString(it) }

        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"

            putExtra(
                Intent.EXTRA_SUBJECT,
                getString(R.string.first_article_title)
            )

            putExtra(Intent.EXTRA_TEXT, articleText)
        }

        startActivity(
            Intent.createChooser(
                sendIntent,
                getString(R.string.share_article)
            )
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_LIKES, likes)
        outState.putInt(KEY_DISLIKES, dislikes)
        outState.putBoolean(KEY_READ, secondArticleRead)

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

    private companion object {
        const val TAG = "MainActivityLifecycle"
        const val KEY_LIKES = "likes"
        const val KEY_DISLIKES = "dislikes"
        const val KEY_READ = "second_article_read"
    }
}