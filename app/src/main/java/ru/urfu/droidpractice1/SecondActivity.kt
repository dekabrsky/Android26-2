package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Используем ViewBinding
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Загрузка изображения через Coil
        binding.articleImage.load("https://www.film.ru/sites/default/files/styles/thumb_1024x450/public/articles/1449341-1137506.jpg")

        // Восстанавливаем состояние чекбокса
        binding.switchRead.isChecked = savedInstanceState?.getBoolean("isRead") ?: false

        // Обработка кнопки "Назад" на тулбаре
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        // Обработка системной кнопки "Назад"
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent().apply {
                    putExtra("isRead", binding.switchRead.isChecked)
                }
                setResult(RESULT_OK, intent)
                finish()
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isRead", binding.switchRead.isChecked)
    }
}