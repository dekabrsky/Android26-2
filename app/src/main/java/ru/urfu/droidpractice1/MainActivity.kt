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
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private var secondArticleRead by mutableStateOf(false)

    private val articleLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == RESULT_OK) {
                secondArticleRead =
                    result.data?.getBooleanExtra(
                        SecondActivity.RESULT_READ_STATE,
                        false
                    ) ?: false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("MainLifecycle", "onCreate")

        secondArticleRead =
            savedInstanceState?.getBoolean(
                "second_article_read"
            ) ?: false

        setContent {
            MainActivityScreen(
                secondArticleRead = secondArticleRead,
                onSecondArticleClick = {

                    val intent = Intent(
                        this,
                        SecondActivity::class.java
                    ).apply {
                        putExtra(
                            SecondActivity.EXTRA_READ_STATE,
                            secondArticleRead
                        )
                    }

                    articleLauncher.launch(intent)
                }
            )
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainLifecycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainLifecycle", "onResume")
    }

    override fun onPause() {
        Log.d("MainLifecycle", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("MainLifecycle", "onStop")
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainLifecycle", "onRestart")
    }

    override fun onDestroy() {
        Log.d("MainLifecycle", "onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(
            "second_article_read",
            secondArticleRead
        )

        Log.d("MainLifecycle", "onSaveInstanceState")

        super.onSaveInstanceState(outState)
    }
}