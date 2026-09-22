package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.activity.ComponentActivity
import coil.load

class SecondActivity : ComponentActivity() {

    companion object {
        const val EXTRA_IS_READ = "extra_is_read"

        private const val TAG = "SecondActivityLifecycle"
        private const val STATE_IS_READ = "is_read"
    }

    private var isRead = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate")

        setContentView(R.layout.activity_second)

        isRead =
            savedInstanceState?.getBoolean(STATE_IS_READ)
                ?: intent.getBooleanExtra(EXTRA_IS_READ, false)

        val articleImage =
            findViewById<ImageView>(R.id.articleImage)

        val switchRead =
            findViewById<Switch>(R.id.switchRead)

        val backButton =
            findViewById<TextView>(R.id.backButton)

        articleImage.load(R.drawable.article_moon)

        switchRead.isChecked = isRead

        switchRead.setOnCheckedChangeListener { _, checked ->
            isRead = checked
            updateResult()
        }

        backButton.setOnClickListener {
            finish()
        }

        updateResult()
    }

    private fun updateResult() {
        setResult(
            Activity.RESULT_OK,
            Intent().apply {
                putExtra(EXTRA_IS_READ, isRead)
            }
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(STATE_IS_READ, isRead)

        super.onSaveInstanceState(outState)
    }

    override fun finish() {
        updateResult()
        super.finish()
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
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }
}