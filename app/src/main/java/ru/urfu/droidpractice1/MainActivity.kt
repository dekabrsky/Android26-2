package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.setContent
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivity"
        const val EXTRA_ARTICLE_READ = "article_read"
        private const val KEY_ARTICLE_READ = "article_read_state"
    }

    private var secondArticleRead = false

    private val secondActivityLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == Activity.RESULT_OK) {
                secondArticleRead =
                    result.data?.getBooleanExtra(
                        EXTRA_ARTICLE_READ,
                        false
                    ) ?: false

                showArticle()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate")

        secondArticleRead =
            savedInstanceState?.getBoolean(KEY_ARTICLE_READ) ?: false

        showArticle()
    }

    private fun showArticle() {
        setContent {
            MainActivityScreen(
                secondArticleRead = secondArticleRead,
                onOpenSecondArticle = {
                    val intent = Intent(this, SecondActivity::class.java)

                    intent.putExtra(
                        EXTRA_ARTICLE_READ,
                        secondArticleRead
                    )

                    secondActivityLauncher.launch(intent)
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
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(
            KEY_ARTICLE_READ,
            secondArticleRead
        )

        super.onSaveInstanceState(outState)
    }
}