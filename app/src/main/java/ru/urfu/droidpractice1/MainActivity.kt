package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import coil.Coil
import coil.ImageLoader
import coil.util.DebugLogger
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private var isSecondArticleRead by mutableStateOf(false)

    private val startSecondActivityForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            isSecondArticleRead = result.data?.getBooleanExtra(SecondActivity.EXTRA_IS_READ, false) ?: false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivityLifecycle", "onCreate")

        val imageLoader = ImageLoader.Builder(this)
            .logger(DebugLogger())
            .build()
        Coil.setImageLoader(imageLoader)

        if (savedInstanceState != null) {
            isSecondArticleRead = savedInstanceState.getBoolean(KEY_IS_READ, false)
        }

        setContent {
            MainActivityScreen(
                isSecondArticleRead = isSecondArticleRead,
                onShareClick = { text ->
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, text)
                    }
                    startActivity(Intent.createChooser(shareIntent, null))
                },
                onNextArticleClick = {
                    val intent = Intent(this, SecondActivity::class.java).apply {
                        putExtra(SecondActivity.EXTRA_IS_READ, isSecondArticleRead)
                    }
                    startSecondActivityForResult.launch(intent)
                }
            )
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivityLifecycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivityLifecycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivityLifecycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivityLifecycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivityLifecycle", "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_IS_READ, isSecondArticleRead)
    }

    companion object {
        private const val KEY_IS_READ = "KEY_IS_READ"
    }
}
