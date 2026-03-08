package ru.urfu.droidpractice1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private val tag = "Lifecycle_MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")

        val isSecondArticleRead = mutableStateOf(false)
        val getIsSecondArticleReadResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val result = result.data?.getBooleanExtra("isRead", false) ?: false
                    isSecondArticleRead.value = result
                }
            }

        setContent {
            MainActivityScreen(isSecondArticleRead.value) { intent ->
                getIsSecondArticleReadResult.launch(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart(); Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume(); Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause(); Log.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop(); Log.d(tag, "onStop")
    }

    override fun onRestart() {
        super.onRestart(); Log.d(tag, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy(); Log.d(tag, "onDestroy")
    }
}
