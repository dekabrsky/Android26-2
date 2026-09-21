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
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

class MainActivity : ComponentActivity() {
    private var likes by mutableIntStateOf(0)
    private var dislikes by mutableIntStateOf(0)
    private var secondArticleRead by mutableStateOf(false)

    // Register unconditionally so a result also reaches a recreated Activity.
    private val secondArticle = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK && result.data?.hasExtra(SecondActivity.EXTRA_READ) == true) {
            secondArticleRead = result.data!!.getBooleanExtra(SecondActivity.EXTRA_READ, false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate restored=${savedInstanceState != null}")
        likes = savedInstanceState?.getInt(KEY_LIKES) ?: 0
        dislikes = savedInstanceState?.getInt(KEY_DISLIKES) ?: 0
        secondArticleRead = savedInstanceState?.getBoolean(KEY_READ) ?: false
        setContent {
            DroidPractice1Theme {
                MainActivityScreen(
                    likes = likes,
                    dislikes = dislikes,
                    secondArticleRead = secondArticleRead,
                    onLike = { likes++ },
                    onDislike = { dislikes++ },
                    onShare = ::shareArticle,
                    onNextArticle = {
                        secondArticle.launch(Intent(this, SecondActivity::class.java)
                            .putExtra(SecondActivity.EXTRA_READ, secondArticleRead))
                    }
                )
            }
        }
    }

    private fun shareArticle() {
        val articleText = listOf(
            R.string.article_title, R.string.article_lead,
            R.string.article_heading_one, R.string.article_body_one, R.string.article_quote,
            R.string.article_heading_two, R.string.article_body_two, R.string.article_body_three
        ).joinToString("\n\n") { getString(it) }
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.article_title).replace('\n', ' '))
            putExtra(Intent.EXTRA_TEXT, articleText)
        }
        startActivity(Intent.createChooser(sendIntent, getString(R.string.share_chooser)))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_LIKES, likes)
        outState.putInt(KEY_DISLIKES, dislikes)
        outState.putBoolean(KEY_READ, secondArticleRead)
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState likes=$likes dislikes=$dislikes read=$secondArticleRead")
    }

    override fun onStart() { super.onStart(); Log.d(TAG, "onStart") }
    override fun onResume() { super.onResume(); Log.d(TAG, "onResume") }
    override fun onPause() { Log.d(TAG, "onPause"); super.onPause() }
    override fun onStop() { Log.d(TAG, "onStop"); super.onStop() }
    override fun onRestart() { super.onRestart(); Log.d(TAG, "onRestart") }
    override fun onDestroy() { Log.d(TAG, "onDestroy changingConfigurations=$isChangingConfigurations"); super.onDestroy() }

    companion object {
        private const val TAG = "MainActivity"
        private const val KEY_LIKES = "likes"
        private const val KEY_DISLIKES = "dislikes"
        private const val KEY_READ = "second_article_read"
    }
}
