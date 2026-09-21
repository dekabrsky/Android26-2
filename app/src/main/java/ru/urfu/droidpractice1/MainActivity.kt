package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private var secondArticleRead by mutableStateOf(false)

    private val secondArticleLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            secondArticleRead = result.data?.getBooleanExtra(
                SecondActivity.EXTRA_ARTICLE_READ,
                secondArticleRead
            ) ?: secondArticleRead
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        secondArticleRead = savedInstanceState?.getBoolean(KEY_SECOND_ARTICLE_READ) ?: false

        setContent {
            MainActivityScreen(
                secondArticleRead = secondArticleRead,
                onOpenSecondArticle = ::openSecondArticle,
                onShareArticle = ::shareArticle
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(KEY_SECOND_ARTICLE_READ, secondArticleRead)
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

    private fun openSecondArticle() {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(SecondActivity.EXTRA_ARTICLE_READ, secondArticleRead)
        }
        secondArticleLauncher.launch(intent)
    }

    private fun shareArticle() {
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.first_article_title))
            putExtra(Intent.EXTRA_TEXT, getString(R.string.first_article_share_text))
        }
        startActivity(Intent.createChooser(sendIntent, getString(R.string.share_chooser_title)))
    }

    companion object {
        private const val TAG = "MainActivityLifecycle"
        private const val KEY_SECOND_ARTICLE_READ = "second_article_read"
    }
}
