package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import androidx.activity.addCallback
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val articleImg = findViewById<ImageView>(R.id.articleImage)
        articleImg.load(getString(R.string.article2_img))

        val switchRead = findViewById<Switch>(R.id.switchRead)
        switchRead.isChecked = intent.getBooleanExtra("isRead", false)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        onBackPressedDispatcher.addCallback(this) {
            val intent = Intent().apply {
                putExtra("isRead", switchRead.isChecked)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}