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

    private val secondArticleRead = mutableStateOf(false)

    private val secondActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            secondArticleRead.value = result.data?.getBooleanExtra(
                SecondActivity.EXTRA_ARTICLE_READ,
                false
            ) ?: false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        secondArticleRead.value = savedInstanceState?.getBoolean(
            KEY_SECOND_ARTICLE_READ,
            false
        ) ?: false

        setContent {
            MainActivityScreen(
                isSecondArticleRead = secondArticleRead.value,
                onOpenSecondArticle = {
                    val intent = Intent(this, SecondActivity::class.java).apply {
                        putExtra(
                            SecondActivity.EXTRA_ARTICLE_READ,
                            secondArticleRead.value
                        )
                    }
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
        outState.putBoolean(KEY_SECOND_ARTICLE_READ, secondArticleRead.value)
        super.onSaveInstanceState(outState)
    }

    private companion object {
        const val TAG = "MainActivity"
        const val KEY_SECOND_ARTICLE_READ = "key_second_article_read"
    }
}
