package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.secondImage.load("https://picsum.photos/seed/ships/1000/600") {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_report_image)
        }


        binding.btnBack.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("IS_READ", binding.switchRead.isChecked)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}