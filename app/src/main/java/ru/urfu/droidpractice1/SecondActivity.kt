package ru.urfu.droidpractice1

import android.annotation.SuppressLint
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

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val img = findViewById<ImageView>(R.id.articleImage)
        img.load(getString(R.string.article2_img))
        val haveRead = findViewById<Switch>(R.id.switchRead)
        haveRead.isChecked = intent.getBooleanExtra("isRead", false)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        onBackPressedDispatcher.addCallback(this) {
            val intent = Intent().apply {
                putExtra("isRead", haveRead.isChecked)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}