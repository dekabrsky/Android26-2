package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private val tag = "Lifecycle_MainActivity"

    private var isSecondArticleReadState by mutableStateOf(false)

    private val secondActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val isRead = result.data?.getBooleanExtra(SecondActivity.EXTRA_IS_READ, false) ?: false
            isSecondArticleReadState = isRead
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")

        setContent {
            MainActivityScreen(
                isSecondArticleRead = isSecondArticleReadState,
                onSecondArticleReadChange = { isRead -> isSecondArticleReadState = isRead },
                onShareClick = { textToShare -> shareArticleText(textToShare) },
                onNavigateToSecond = {
                    val intent = Intent(this, SecondActivity::class.java).apply {
                        putExtra(SecondActivity.EXTRA_IS_READ, isSecondArticleReadState)
                    }
                    secondActivityLauncher.launch(intent)
                }
            )
        }
    }

    private fun shareArticleText(text: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, "Поделиться статьей"))
    }

    override fun onStart() { super.onStart(); Log.d(tag, "onStart") }
    override fun onResume() { super.onResume(); Log.d(tag, "onResume") }
    override fun onPause() { super.onPause(); Log.d(tag, "onPause") }
    override fun onStop() { super.onStop(); Log.d(tag, "onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d(tag, "onDestroy") }
}