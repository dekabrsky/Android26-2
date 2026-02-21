package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.urfu.droidpractice1.content.FirstArticleScreen

class FirstArticleActivity : ComponentActivity() {

    companion object {
        private const val TAG = "FirstArticleActivity"
        const val EXTRA_IS_READ = "first_article_is_read"
        const val EXTRA_LIKES = "first_article_likes"
        const val EXTRA_DISLIKES = "first_article_dislikes"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        val alreadyRead = intent.getBooleanExtra(EXTRA_IS_READ, false)
        val initialLikes = intent.getIntExtra(EXTRA_LIKES, 0)
        val initialDislikes = intent.getIntExtra(EXTRA_DISLIKES, 0)

        setContent {
            FirstArticleScreen(
                initialIsRead = alreadyRead,
                initialLikeCount = initialLikes,
                initialDislikeCount = initialDislikes,
                onShareArticle = { text ->
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, text)
                    }
                    startActivity(
                        Intent.createChooser(shareIntent, getString(R.string.share_article))
                    )
                },
                onCountersChanged = { likes, dislikes, isRead ->
                    val data = Intent().apply {
                        putExtra(EXTRA_IS_READ, isRead)
                        putExtra(EXTRA_LIKES, likes)
                        putExtra(EXTRA_DISLIKES, dislikes)
                    }
                    setResult(RESULT_OK, data)
                },
                onBack = {
                    onBackPressedDispatcher.onBackPressed()
                }
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
