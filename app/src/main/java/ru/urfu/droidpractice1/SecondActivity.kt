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

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.articleImage.load(ARTICLE_IMAGE_URL) {
            crossfade(true)
            setHeader("User-Agent", "DroidPractice1/1.0 (student project)")
        }

        binding.switchRead.isChecked = intent.getBooleanExtra(EXTRA_READ, false)
        binding.switchRead.setOnCheckedChangeListener { _, isChecked ->
            setResult(RESULT_OK, Intent().putExtra(EXTRA_READ, isChecked))
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
        const val EXTRA_READ = "extra_read"
        private const val TAG = "SecondActivityLifecycle"
        private const val ARTICLE_IMAGE_URL =
            "https://upload.wikimedia.org/wikipedia/commons/f/ff/Daniel_Negreanu_2007.jpg"
    }
}
