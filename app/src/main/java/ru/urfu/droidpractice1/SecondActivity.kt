package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import android.os.Bundle
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private val tag = "SecondActivity"
    private lateinit var binding: ActivitySecondBinding
    private var isRead = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.readCheckBox.setOnCheckedChangeListener { _, isChecked ->
            isRead = isChecked
        }

        binding.toolbar.setNavigationOnClickListener {
            closeWithResult()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                closeWithResult()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }   

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }

    private fun closeWithResult() {
        val resultIntent = Intent()
        resultIntent.putExtra("isRead", isRead)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}
