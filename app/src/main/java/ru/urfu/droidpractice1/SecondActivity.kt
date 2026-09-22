package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("LifeCycle", "SecondActivity onCreate")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Картинка
        binding.articleImage.load(
            R.mipmap.ic_launcher
        )

        // Получение состояния
        val isRead = intent.getBooleanExtra("is_read", false)
        binding.readSwitch.isChecked = isRead

        // Смена состояния
        binding.readSwitch.setOnCheckedChangeListener { _, checked ->
            val resultIntent = Intent()
            resultIntent.putExtra("is_read", checked)

            setResult(
                Activity.RESULT_OK,
                resultIntent
            )
        }

        // Кнопка назад
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("LifeCycle", "SecondActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle", "SecondActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycle", "SecondActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycle", "SecondActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle", "SecondActivity onDestroy")
    }
}