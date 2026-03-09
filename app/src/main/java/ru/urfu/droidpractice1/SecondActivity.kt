package ru.urfu.droidpractice1

import androidx.activity.ComponentActivity
import android.os.Bundle
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import com.bumptech.glide.Glide
import android.content.Intent
import androidx.activity.addCallback

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        Glide.with(this)
            .load("https://img.championat.com/s/732x488/news/big/b/q/gonschiki-formuly-1-o-bolidah-2026-goda_1772998510899375702.jpg")
            .into(binding.photo)

        binding.switchRead.isChecked = intent.getBooleanExtra("isRead", false)
        binding.toolbar.setNavigationOnClickListener {
            returnResult()
        }
        onBackPressedDispatcher.addCallback(this) {
            returnResult()
        }
    }

    private fun returnResult() {
        val intent = Intent().apply {
            putExtra("isRead", binding.switchRead.isChecked)
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}