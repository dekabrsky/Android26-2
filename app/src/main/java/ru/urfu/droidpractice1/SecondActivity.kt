package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import coil.load
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding


class SecondActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SecondActivity"
        private const val KEY_IS_READ = "is_read"
    }

    private lateinit var binding: ActivitySecondBinding

    private var isRead = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.articleImage.load(R.drawable.article_blackhole) {
            crossfade(true)
        }

        isRead = savedInstanceState?.getBoolean(KEY_IS_READ) ?: false

        binding.readSwitch.isChecked = isRead

        binding.readSwitch.setOnCheckedChangeListener { _, checked ->
            isRead = checked
        }

        binding.toolbar.setNavigationOnClickListener {
            returnResult()
        }

        onBackPressedDispatcher.addCallback(
            this,
            object : androidx.activity.OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    returnResult()
                }
            }
        )
    }

    private fun returnResult() {
        val resultIntent = Intent().apply {
            putExtra(
                MainActivity.EXTRA_ARTICLE_READ,
                isRead
            )
        }

        setResult(Activity.RESULT_OK, resultIntent)
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

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(
            KEY_IS_READ,
            isRead
        )

        super.onSaveInstanceState(outState)
    }
}