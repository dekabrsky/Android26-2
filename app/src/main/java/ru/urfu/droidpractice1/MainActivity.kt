package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {
    
    private var isSecondArticleRead = false
    
    private val startSecondActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            isSecondArticleRead = result.data?.getBooleanExtra(EXTRA_IS_READ, false) ?: false
            Log.d(TAG, "Second article read status: $isSecondArticleRead")
            setContent {
                MainActivityScreen(
                    isSecondArticleRead = isSecondArticleRead,
                    onNavigateToSecond = { navigateToSecond() }
                )
            }
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        
        if (savedInstanceState != null) {
            isSecondArticleRead = savedInstanceState.getBoolean(KEY_SECOND_READ, false)
        }
        
        setContent {
            MainActivityScreen(
                isSecondArticleRead = isSecondArticleRead,
                onNavigateToSecond = { navigateToSecond() }
            )
        }
    }
    
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putBoolean(KEY_SECOND_READ, isSecondArticleRead)
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
    
    private fun navigateToSecond() {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(SecondActivity.EXTRA_IS_READ, isSecondArticleRead)
        startSecondActivityLauncher.launch(intent)
    }
    
    companion object {
        const val EXTRA_IS_READ = "is_read"
        private const val KEY_SECOND_READ = "second_read"
        private const val TAG = "MainActivity"
    }
}