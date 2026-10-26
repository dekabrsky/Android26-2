package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : AppCompatActivity() {
    private val TAG = "Lifecycle_MainActivity"
    private var isSecondArticleRead by mutableStateOf(false)

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            isSecondArticleRead = result.data?.getBooleanExtra(SecondActivity.EXTRA_IS_READ, false) ?: false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        
        if (savedInstanceState != null) {
            isSecondArticleRead = savedInstanceState.getBoolean(KEY_IS_READ, false)
        }

        setContent {
            MainActivityScreen(
                isRead = isSecondArticleRead,
                onNextArticleClick = {
                    val intent = Intent(this, SecondActivity::class.java).apply {
                        putExtra(SecondActivity.EXTRA_IS_READ, isSecondArticleRead)
                    }
                    startForResult.launch(intent)
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
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putBoolean(KEY_IS_READ, isSecondArticleRead)
    }

    companion object {
        private const val KEY_IS_READ = "key_is_read"
    }
}
