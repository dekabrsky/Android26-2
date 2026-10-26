package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SecondActivityLifecycle", "onCreate")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isReadInitial = savedInstanceState?.getBoolean(KEY_IS_READ)
            ?: intent.getBooleanExtra(EXTRA_IS_READ, false)

        binding.switchRead.isChecked = isReadInitial
        updateResult(isReadInitial)

        binding.switchRead.setOnCheckedChangeListener { _, isChecked ->
            updateResult(isChecked)
        }

        binding.articleImage.load("https://avatars.mds.yandex.net/i?id=db1fca493d253e9a50f84f3e894a5aef_l-5233303-images-thumbs&n=13") {
            crossfade(true)
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                updateResult(binding.switchRead.isChecked)
                isEnabled = false
                onBackPressedDispatcher.onBackPressed()
            }
        })
    }

    private fun updateResult(isRead: Boolean) {
        val resultIntent = Intent().apply {
            putExtra(EXTRA_IS_READ, isRead)
        }
        setResult(RESULT_OK, resultIntent)
    }

    override fun onStart() {
        super.onStart()
        Log.d("SecondActivityLifecycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondActivityLifecycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SecondActivityLifecycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SecondActivityLifecycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondActivityLifecycle", "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_IS_READ, binding.switchRead.isChecked)
    }

    companion object {
        const val EXTRA_IS_READ = "EXTRA_IS_READ"
        private const val KEY_IS_READ = "KEY_IS_READ"
    }
}
