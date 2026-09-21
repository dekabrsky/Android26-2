package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var isRead = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "SecondActivity: onCreate")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        isRead = savedInstanceState?.getBoolean(KEY_IS_READ)
            ?: intent.getBooleanExtra(EXTRA_IS_READ, false)
        binding.readSwitch.isChecked = isRead
        binding.readSwitch.setOnCheckedChangeListener { _, checked ->
            isRead = checked
        }

        binding.articleImage.load("https://images.unsplash.com/photo-1551650975-87deedd944c3?auto=format&fit=crop&w=1200&q=80") {
            placeholder(R.drawable.article_image_placeholder)
            error(R.drawable.article_image_placeholder)
        }

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishWithReadResult()
            }
        })
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

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "SecondActivity: onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "SecondActivity: onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(KEY_IS_READ, isRead)
        super.onSaveInstanceState(outState)
    }

    private fun finishWithReadResult() {
        val result = Intent().putExtra(EXTRA_IS_READ, isRead)
        setResult(RESULT_OK, result)
        finish()
    }

    companion object {
        const val EXTRA_IS_READ = "ru.urfu.droidpractice1.extra.IS_READ"
        private const val TAG = "SecondActivityLifecycle"
        private const val KEY_IS_READ = "key_is_read"
    }
}
