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

    private val secondArticleLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                secondArticleRead = result.data
                    ?.getBooleanExtra(SecondActivity.EXTRA_READ, secondArticleRead)
                    ?: secondArticleRead
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        savedInstanceState?.let {
            likes = it.getInt(KEY_LIKES)
            dislikes = it.getInt(KEY_DISLIKES)
            secondArticleRead = it.getBoolean(KEY_READ)
        }

        setContent {
            MainActivityScreen(
                likes = likes,
                dislikes = dislikes,
                secondArticleRead = secondArticleRead,
                onLike = { likes++ },
                onDislike = { dislikes++ },
                onShare = ::shareArticle,
                onOpenSecondArticle = ::openSecondArticle,
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_LIKES, likes)
        outState.putInt(KEY_DISLIKES, dislikes)
        outState.putBoolean(KEY_READ, secondArticleRead)
    }

    private fun shareArticle() {
        val text = getString(R.string.article_title) + "\n\n" + getString(R.string.article_lead)
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        startActivity(Intent.createChooser(intent, getString(R.string.share)))
    }

    private fun openSecondArticle() {
        val intent = Intent(this, SecondActivity::class.java)
            .putExtra(SecondActivity.EXTRA_READ, secondArticleRead)
        secondArticleLauncher.launch(intent)
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

    companion object {
        private const val TAG = "MainActivityLifecycle"
        private const val KEY_LIKES = "likes"
        private const val KEY_DISLIKES = "dislikes"
        private const val KEY_READ = "second_article_read"
    }
}
