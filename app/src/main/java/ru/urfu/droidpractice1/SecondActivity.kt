package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycle("onCreate")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            deliverReadResult()
            finish()
        }

        val isRead = savedInstanceState?.getBoolean(EXTRA_IS_READ)
            ?: intent.getBooleanExtra(EXTRA_IS_READ, false)
        binding.readSwitch.isChecked = isRead

        binding.image.load(R.drawable.article2_photo) {
            crossfade(true)
        }

        binding.readSwitch.setOnCheckedChangeListener { _, _ ->
            deliverReadResult()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                deliverReadResult()
                finish()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("onStart")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("onResume")
    }

    override fun onPause() {
        logLifecycle("onPause")
        super.onPause()
    }

    override fun onStop() {
        logLifecycle("onStop")
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
        logLifecycle("onRestart")
    }

    override fun onDestroy() {
        logLifecycle("onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(EXTRA_IS_READ, binding.readSwitch.isChecked)
        logLifecycle("onSaveInstanceState")
    }

    private fun deliverReadResult() {
        val data = Intent().putExtra(EXTRA_IS_READ, binding.readSwitch.isChecked)
        setResult(RESULT_OK, data)
    }

    private fun logLifecycle(event: String) {
        Log.d(TAG, event)
    }

    companion object {
        const val EXTRA_IS_READ = "extra_is_read"
        private const val TAG = "SecondActivity"
    }
}
