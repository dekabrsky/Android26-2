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
        const val EXTRA_READ_STATE = "read_state"
        const val RESULT_READ_STATE = "result_read_state"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("SecondLifecycle", "onCreate")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val previousState =
            intent.getBooleanExtra(EXTRA_READ_STATE, false)

        binding.readSwitch.isChecked = previousState

        binding.toolbar.setNavigationOnClickListener {
            closeArticle()
        }

        onBackPressedDispatcher.addCallback(this) {
            closeArticle()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("SecondLifecycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondLifecycle", "onResume")
    }

    override fun onPause() {
        Log.d("SecondLifecycle", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("SecondLifecycle", "onStop")
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("SecondLifecycle", "onRestart")
    }

    override fun onDestroy() {
        Log.d("SecondLifecycle", "onDestroy")
        super.onDestroy()
    }

    private fun closeArticle() {
        val data = Intent().apply {
            putExtra(
                RESULT_READ_STATE,
                binding.readSwitch.isChecked
            )
        }

        setResult(RESULT_OK, data)
        finish()
    }
}