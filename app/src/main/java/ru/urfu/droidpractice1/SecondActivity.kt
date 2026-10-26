package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private val TAG = "Lifecycle_SecondActivity"
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.articleImage.load(
            "https://preview.redd.it/hey-i-realized-something-spoilers-for-canto-10-v0-qflgg68kc1qh1.jpeg?auto=webp&s=d2f50f31efd3e8439767f79721a6991a0fb89353"
        )

        val isRead = savedInstanceState?.getBoolean(EXTRA_IS_READ)
            ?: intent.getBooleanExtra(EXTRA_IS_READ, false)

        binding.readSwitch.isChecked = isRead

        binding.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            val resultIntent = Intent().apply {
                putExtra(EXTRA_IS_READ, isChecked)
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

        outState.putBoolean(
            EXTRA_IS_READ,
            binding.readSwitch.isChecked
        )

        Log.d(TAG, "onSaveInstanceState")
    }

    companion object {
        const val EXTRA_IS_READ = "extra_is_read"
    }
}