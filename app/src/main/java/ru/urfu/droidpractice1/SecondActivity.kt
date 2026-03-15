package ru.urfu.droidpractice1

import androidx.activity.ComponentActivity
import android.os.Bundle
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import com.bumptech.glide.Glide
import android.content.Intent
import androidx.activity.addCallback
import android.util.Log

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    companion object {
        private const val tag = "SecondActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load("https://img.championat.com/s/732x488/news/big/b/q/gonschiki-formuly-1-o-bolidah-2026-goda_1772998510899375702.jpg")
            .into(binding.photo)

        binding.switchRead.isChecked =
            intent.getBooleanExtra(MainActivity.EXTRA_IS_READ, false)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            returnResult()
        }
    }

    private fun returnResult() {
        val intent = Intent().apply {
            putExtra(MainActivity.EXTRA_IS_READ, binding.switchRead.isChecked)
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onStart() {
        super.onStart(); Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume(); Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause(); Log.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop(); Log.d(tag, "onStop")
    }

    override fun onRestart() {
        super.onRestart(); Log.d(tag, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy(); Log.d(tag, "onDestroy")
    }
}