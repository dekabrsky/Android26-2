package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import coil.load

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Находим View по ID
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val articleImg = findViewById<ImageView>(R.id.articleImage)
        val switchRead = findViewById<Switch>(R.id.switchRead)

        // Загрузка изображения через Coil
        articleImg.load("https://www.film.ru/sites/default/files/styles/thumb_1024x450/public/articles/1449341-1137506.jpg")

        // Восстанавливаем состояние чекбокса
        switchRead.isChecked = savedInstanceState?.getBoolean("isRead") ?: false

        // Обработка кнопки "Назад" на тулбаре
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        //Обработка системной кнопки "Назад"
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent().apply {
                    putExtra("isRead", switchRead.isChecked)
                }
                setResult(RESULT_OK, intent)
                finish()
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val switchRead = findViewById<Switch>(R.id.switchRead)
        outState.putBoolean("isRead", switchRead.isChecked)
    }
}