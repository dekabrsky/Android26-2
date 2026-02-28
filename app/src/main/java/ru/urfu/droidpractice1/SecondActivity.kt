package ru.urfu.droidpractice1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

        val isRead = prefs.getBoolean("second_article_read", false)
        binding.readSwitch.isChecked = isRead

        binding.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("second_article_read", isChecked).apply()
        }

        binding.toolbar.setNavigationOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("is_read", binding.readSwitch.isChecked)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    override fun onBackPressed() {
        val resultIntent = Intent()
        resultIntent.putExtra("is_read", binding.readSwitch.isChecked)
        setResult(RESULT_OK, resultIntent)
        super.onBackPressed()
    }
}