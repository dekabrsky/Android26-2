package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "SecondActivity"
        const val IS_READ = "is_read"
    }

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.articleImage.load("https://icdn.lenta.ru/images/2026/09/11/18/20260911180625679/owl_wide_1200_643fee33adad4e2816f2bf38f3f8d5ee.jpg")

        val isRead = intent.getBooleanExtra(IS_READ, false)
        binding.readSwitch.isChecked = isRead

        binding.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            val resultIntent = Intent().apply {
                putExtra(IS_READ, isChecked)
            }
            setResult(RESULT_OK, resultIntent)
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

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
    }
}