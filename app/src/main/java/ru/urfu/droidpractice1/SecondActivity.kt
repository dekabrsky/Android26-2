package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articleRead = savedInstanceState?.getBoolean(KEY_ARTICLE_READ)
            ?: intent.getBooleanExtra(EXTRA_ARTICLE_READ, false)
        binding.readSwitch.isChecked = articleRead

        binding.articleImage.load(getString(R.string.second_article_image_url)) {
            crossfade(true)
            placeholder(R.drawable.article_placeholder)
            error(R.drawable.article_placeholder)
        }

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        onBackPressedDispatcher.addCallback(this) { finishWithResult() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(KEY_ARTICLE_READ, binding.readSwitch.isChecked)
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

    private fun finishWithResult() {
        setResult(
            Activity.RESULT_OK,
            Intent().putExtra(EXTRA_ARTICLE_READ, binding.readSwitch.isChecked)
        )
        finish()
    }

    companion object {
        const val EXTRA_ARTICLE_READ = "ru.urfu.droidpractice1.extra.ARTICLE_READ"

        private const val TAG = "SecondActivityLifecycle"
        private const val KEY_ARTICLE_READ = "article_read"
    }
}
