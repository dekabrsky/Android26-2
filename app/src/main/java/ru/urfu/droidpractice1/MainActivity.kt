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

    private var isSecondArticleRead by mutableStateOf(false)

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                isSecondArticleRead =
                    result.data?.getBooleanExtra("article_read", false) ?: false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LIFECYCLE", "MainActivity onCreate")

        if (savedInstanceState != null) {
            isSecondArticleRead = savedInstanceState.getBoolean("article_read", false)
        }

        setContent {
            MainActivityScreen(
                isSecondArticleRead = isSecondArticleRead,
                openSecondArticle = {
                    val intent = Intent(this, SecondActivity::class.java)
                    launcher.launch(intent)
                }
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("article_read", isSecondArticleRead)
    }

    override fun onStart() { super.onStart(); Log.d("LIFECYCLE", "MainActivity onStart") }
    override fun onResume() { super.onResume(); Log.d("LIFECYCLE", "MainActivity onResume") }
    override fun onPause() { super.onPause(); Log.d("LIFECYCLE", "MainActivity onPause") }
    override fun onStop() { super.onStop(); Log.d("LIFECYCLE", "MainActivity onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d("LIFECYCLE", "MainActivity onDestroy") }
}