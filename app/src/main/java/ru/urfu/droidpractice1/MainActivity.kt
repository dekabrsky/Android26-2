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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private var isSecondArticleRead by mutableStateOf(false)

    private val secondArticleLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            isSecondArticleRead = result.data
                ?.getBooleanExtra(SecondActivity.EXTRA_IS_READ, false)
                ?: false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycle("onCreate")
        if (savedInstanceState != null) {
            isSecondArticleRead = savedInstanceState.getBoolean(KEY_SECOND_READ, false)
        }
        setContent {
            var rating by rememberSaveable { mutableIntStateOf(0) }
            MainActivityScreen(
                rating = rating,
                isSecondArticleRead = isSecondArticleRead,
                onShareClick = ::shareArticle,
                onLikeClick = { rating++ },
                onDislikeClick = { rating-- },
                onOpenSecondArticle = ::openSecondArticle
            )
        }
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("onStart")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("onResume")
    }

    override fun onPause() {
        logLifecycle("onPause")
        super.onPause()
    }

    override fun onStop() {
        logLifecycle("onStop")
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
        logLifecycle("onRestart")
    }

    override fun onDestroy() {
        logLifecycle("onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_SECOND_READ, isSecondArticleRead)
        logLifecycle("onSaveInstanceState")
    }

    private fun shareArticle() {
        val shareText = getString(R.string.article1_share_text)
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.article1_title))
        }
        startActivity(Intent.createChooser(intent, getString(R.string.share_chooser)))
    }

    private fun openSecondArticle() {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(SecondActivity.EXTRA_IS_READ, isSecondArticleRead)
        }
        secondArticleLauncher.launch(intent)
    }

    private fun logLifecycle(event: String) {
        Log.d(TAG, event)
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val KEY_SECOND_READ = "key_second_read"
    }
}
