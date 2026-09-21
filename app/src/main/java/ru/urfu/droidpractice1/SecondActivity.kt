package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isRead = savedInstanceState?.getBoolean(KEY_IS_READ)
            ?: intent.getBooleanExtra(EXTRA_IS_READ, false)

        binding.readSwitch.isChecked = isRead
        updateResult(isRead)

        binding.readSwitch.setOnCheckedChangeListener { _, checked ->
            updateResult(checked)
        }

        binding.articleImage.load(IMAGE_URL) {
            crossfade(true)
            placeholder(R.drawable.app_icon)
            error(R.drawable.app_icon)
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun updateResult(isRead: Boolean) {
        val resultIntent = Intent().apply {
            putExtra(EXTRA_IS_READ, isRead)
        }

        setResult(RESULT_OK, resultIntent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(
            KEY_IS_READ,
            binding.readSwitch.isChecked
        )

        super.onSaveInstanceState(outState)
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

    companion object {
        const val EXTRA_IS_READ = "second_article_is_read"

        private const val KEY_IS_READ = "is_read"
        private const val TAG = "SecondActivityLifecycle"

        private const val IMAGE_URL =
            "https://images.unsplash.com/photo-1522778119026-d647f0596c20?w=1200"
    }
}