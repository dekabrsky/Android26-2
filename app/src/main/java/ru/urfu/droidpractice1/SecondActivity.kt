package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import coil.load

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val imageView = findViewById<ImageView>(R.id.second_image)
        val switchRead = findViewById<SwitchCompat>(R.id.switch_read)
        val btnBack = findViewById<Button>(R.id.btn_back)


        imageView.load("https://picsum.photos/seed/ships/1000/600") {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_report_image)
        }

        btnBack.setOnClickListener {
            val resultIntent = Intent().apply {
                // Передаем состояние свитча (true/false) назад
                putExtra("IS_READ", switchRead.isChecked)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}