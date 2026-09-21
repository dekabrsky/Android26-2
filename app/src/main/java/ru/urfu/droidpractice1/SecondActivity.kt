package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    companion object {
        const val EXTRA_IS_READ = "extra_is_read"
    }

    private val tag = "SecondActivity"

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate (savedInstanceState = ${if (savedInstanceState == null) "null" else "not null"})")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.ivArticle.load("https://picsum.photos/id/1060/800/450")

        // При первом открытии берём состояние из первой Activity.
        // При повороте Switch восстановит себя сам (у него есть id).
        if (savedInstanceState == null) {
            binding.switchRead.isChecked = intent.getBooleanExtra(EXTRA_IS_READ, false)
        }
        publishResult(binding.switchRead.isChecked)

        binding.switchRead.setOnCheckedChangeListener { _, isChecked ->
            publishResult(isChecked)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    override fun onPause() {
        Log.d(tag, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(tag, "onStop")
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart")
    }

    override fun onDestroy() {
        Log.d(tag, "onDestroy (isChangingConfigurations = $isChangingConfigurations)")
        super.onDestroy()
    }

    // Сообщаем первой Activity текущее значение "Прочитано"
    private fun publishResult(isRead: Boolean) {
        setResult(RESULT_OK, Intent().putExtra(EXTRA_IS_READ, isRead))
    }
}