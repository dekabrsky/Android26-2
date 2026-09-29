package ru.urfu.droidpractice1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    private val preferences by lazy {
        getSharedPreferences(
            "article_preferences",
            MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("LIFECYCLE", "SecondActivity: onCreate")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Восстанавливаем состояние «Прочитано»
        binding.readSwitch.isChecked =
            preferences.getBoolean(
                "article_2_read",
                false
            )

        // Сохраняем состояние переключателя
        binding.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            preferences.edit()
                .putBoolean("article_2_read", isChecked)
                .apply()
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d("LIFECYCLE", "SecondActivity: onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.d("LIFECYCLE", "SecondActivity: onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.d("LIFECYCLE", "SecondActivity: onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.d("LIFECYCLE", "SecondActivity: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("LIFECYCLE", "SecondActivity: onDestroy")
    }
}