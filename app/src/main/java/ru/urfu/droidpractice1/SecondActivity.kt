package ru.urfu.droidpractice1

import android.util.Log
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    companion object {
        const val EXTRA_ARTICLE_READ = "article_read"
        private const val KEY_ARTICLE_READ = "article_read_state"
        private const val TAG = "SecondActivity"
    }

    private lateinit var binding: ActivitySecondBinding

    private var articleRead = false

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate")
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        articleRead = savedInstanceState?.getBoolean(KEY_ARTICLE_READ)
            ?: intent.getBooleanExtra(EXTRA_ARTICLE_READ, false)

        binding.switchRead.isChecked = articleRead
        binding.switchRead.setOnCheckedChangeListener { _, isChecked ->
            articleRead = isChecked
        }

        binding.toolbar.setNavigationOnClickListener {
            finishWithResult()
        }

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    finishWithResult()
                }
            }
        )
    }

    override fun onStart() {
        Log.i(TAG, "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.i(TAG, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.i(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i(TAG, "onStop")
        super.onStop()
    }

    override fun onRestart() {
        Log.i(TAG, "onRestart")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i(TAG, "onSaveInstanceState")
        outState.putBoolean(KEY_ARTICLE_READ, articleRead)
        super.onSaveInstanceState(outState)
    }

    private fun finishWithResult() {
        val resultIntent = Intent().apply {
            putExtra(EXTRA_ARTICLE_READ, articleRead)
        }

        setResult(RESULT_OK, resultIntent)
        finish()
    }
}