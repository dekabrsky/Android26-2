package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.readSwitch.isChecked = intent.getBooleanExtra(EXTRA_ARTICLE_READ, false)
        updateReadStatus(binding.readSwitch.isChecked)

        binding.articleImage.load(SECOND_ARTICLE_IMAGE_URL) {
            crossfade(true)
        }

        binding.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            updateReadStatus(isChecked)
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishWithResult()
            }
        })
    }

    private fun updateReadStatus(isRead: Boolean) {
        binding.readStatus.text = getString(
            if (isRead) R.string.second_article_read else R.string.second_article_not_read
        )
    }

    private fun finishWithResult() {
        setResult(
            RESULT_OK,
            Intent().putExtra(EXTRA_ARTICLE_READ, binding.readSwitch.isChecked)
        )
        finish()
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

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }

    companion object {
        const val TAG = "SecondActivity"
        const val EXTRA_ARTICLE_READ = "extra_article_read"
        const val SECOND_ARTICLE_IMAGE_URL =
            "https://images.unsplash.com/photo-1456324504439-367cee3b3c32?auto=format&fit=crop&w=1200&q=85"
    }
}
