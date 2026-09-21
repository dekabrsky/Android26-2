package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    companion object {
        const val EXTRA_IS_READ = "extra_is_read"
        const val RESULT_IS_READ = "result_is_read"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("SecondActivityLifecycle", "onCreate")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isRead = intent.getBooleanExtra(EXTRA_IS_READ, false)

        binding.readSwitch.isChecked = isRead

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            returnResult()
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d("SecondActivityLifecycle", "onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.d("SecondActivityLifecycle", "onResume")
    }

    override fun onPause() {

        Log.d("SecondActivityLifecycle", "onPause")

        super.onPause()
    }

    override fun onStop() {

        Log.d("SecondActivityLifecycle", "onStop")

        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()

        Log.d("SecondActivityLifecycle", "onRestart")
    }

    override fun onDestroy() {

        Log.d("SecondActivityLifecycle", "onDestroy")

        super.onDestroy()
    }

    private fun returnResult() {

        val resultIntent = Intent().apply {
            putExtra(
                RESULT_IS_READ,
                binding.readSwitch.isChecked
            )
        }

        setResult(RESULT_OK, resultIntent)

        finish()
    }
}