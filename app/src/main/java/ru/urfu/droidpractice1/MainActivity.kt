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

    private val secondActivityLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == RESULT_OK) {

                secondArticleRead =
                    result.data?.getBooleanExtra(
                        SecondActivity.RESULT_IS_READ,
                        false
                    ) ?: false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("MainActivityLifecycle", "onCreate")

        secondArticleRead =
            savedInstanceState?.getBoolean(
                "second_article_read"
            ) ?: false

        setContent {

            MainActivityScreen(
                isSecondArticleRead = secondArticleRead,

                onOpenSecondArticle = {

                    val intent = Intent(
                        this,
                        SecondActivity::class.java
                    ).apply {

                        putExtra(
                            SecondActivity.EXTRA_IS_READ,
                            secondArticleRead
                        )
                    }

                    secondActivityLauncher.launch(intent)
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

        Log.d("MainActivityLifecycle", "onPause")

        super.onPause()
    }

    override fun onStop() {

        Log.d("MainActivityLifecycle", "onStop")

        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()

        Log.d("MainActivityLifecycle", "onRestart")
    }

    override fun onDestroy() {

        Log.d("MainActivityLifecycle", "onDestroy")

        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {

        Log.d("MainActivityLifecycle", "onSaveInstanceState")

        outState.putBoolean(
            "second_article_read",
            secondArticleRead
        )

        super.onSaveInstanceState(outState)
    }
}