package ru.urfu.droidpractice1

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import coil.imageLoader
import coil.request.ImageRequest
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isRead = intent.getBooleanExtra(EXTRA_READ, false)
        binding.readSwitch.isChecked = isRead
        binding.readSwitch.setOnCheckedChangeListener { _, checked ->
            prefs.edit().putBoolean(KEY_SECOND_READ, checked).apply()
        }

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.image.loadArticleImage()
    }

    private fun android.widget.ImageView.loadArticleImage() {
        val request = ImageRequest.Builder(context)
            .data(getString(R.string.second_image_url))
            .target(this)
            .build()
        context.imageLoader.enqueue(request)
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

    companion object {
        private const val TAG = "SecondActivity"
        private const val PREFS_NAME = "article_prefs"
        private const val KEY_SECOND_READ = "second_article_read"
        const val EXTRA_READ = "extra_read"
    }
}
