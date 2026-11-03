package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import coil3.load
import coil3.request.crossfade
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    companion object {
        const val EXTRA_IS_READ = "is_read"
        private const val STATE_IS_READ = "state_is_read"
        private const val TAG = "Lifecycle"
    }

    private lateinit var binding: ActivitySecondBinding
    private var isRead: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "SecondActivity: onCreate")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.articleCover.load("https://images.cybersport.ru/images/material-card/plain/61/6161771e-e0f0-429f-b667-5cd9bf57ebeb.jpg") {
            crossfade(true)
        }

        isRead = savedInstanceState?.getBoolean(STATE_IS_READ)
            ?: intent.getBooleanExtra(EXTRA_IS_READ, false)

        binding.readCheckbox.isChecked = isRead

        binding.readCheckbox.setOnCheckedChangeListener { _, isChecked ->
            isRead = isChecked
            val resultIntent = Intent().apply {
                putExtra(EXTRA_IS_READ, isChecked)
            }
            setResult(RESULT_OK, resultIntent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(STATE_IS_READ, isRead)
        Log.d(TAG, "SecondActivity: onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "SecondActivity: onRestoreInstanceState")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "SecondActivity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "SecondActivity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "SecondActivity: onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "SecondActivity: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "SecondActivity: onDestroy")
    }
}