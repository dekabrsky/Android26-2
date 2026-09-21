package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

private const val TAG = "SecondActivity_Lifecycle"
private const val PREFS_NAME = "articles_prefs"
private const val KEY_IS_READ = "second_article_read"

class SecondActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_IS_READ = "extra_is_read"
    }

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.articleImage.load(
            getString(R.string.article2_image_url)
        ) {
            listener(
                onSuccess = { _, _ ->
                    Log.d("IMAGE_TEST", "Вторая картинка успешно загружена")
                },
                onError = { _, result ->
                    Log.e(
                        "IMAGE_TEST",
                        "Ошибка второй картинки",
                        result.throwable
                    )
                }
            )
        }

        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val savedIsRead = prefs.getBoolean(KEY_IS_READ, false)
        binding.readSwitch.isChecked = savedIsRead
        publishResult(savedIsRead)

        binding.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(KEY_IS_READ, isChecked).apply()
            publishResult(isChecked)
        }

        binding.backToFirstButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun publishResult(isRead: Boolean) {
        val resultIntent = Intent().putExtra(EXTRA_IS_READ, isRead)
        setResult(RESULT_OK, resultIntent)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}