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
import okhttp3.OkHttpClient
import ru.urfu.droidpractice1.content.MainActivityScreen

const val EXTRA_IS_READ = "is_read"

class MainActivity : ComponentActivity() {

    private var isArticleRead by mutableStateOf(false)

    private val secondActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                isArticleRead = result.data?.getBooleanExtra(EXTRA_IS_READ, false) ?: false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "MainActivity: onCreate")
        isArticleRead = savedInstanceState?.getBoolean(EXTRA_IS_READ, false) ?: false

        Coil.setImageLoader(
            ImageLoader.Builder(this)
                .okHttpClient(
                    OkHttpClient.Builder()
                        .addInterceptor { chain ->
                            chain.proceed(
                                chain.request().newBuilder()
                                    .header("User-Agent", "Mozilla/5.0 (Linux; Android 10)")
                                    .build()
                            )
                        }
                        .build()
                )
                .build()
        )

        setContent {
            MainActivityScreen(
                isArticleRead = isArticleRead,
                onOpenSecondArticle = {
                    val intent = Intent(this, SecondActivity::class.java).apply {
                        putExtra(EXTRA_IS_READ, isArticleRead)
                    }
                    secondActivityLauncher.launch(intent)
                }
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(EXTRA_IS_READ, isArticleRead)
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "MainActivity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "MainActivity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "MainActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "MainActivity: onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Lifecycle", "MainActivity: onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "MainActivity: onDestroy")
    }
}
