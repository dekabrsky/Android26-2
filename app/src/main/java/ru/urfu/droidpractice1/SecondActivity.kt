package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SecondActivity"
        private const val IMAGE_URL =
            "https://sportnews.24tv.ua/resources/photos/news/202602/3009184.jpg?v=1770988364000&w=1300&h=730&fit=cover&output=webp&q=90"
    }

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            finishWithResult()
        }

        binding.articleImage.load(IMAGE_URL) {
            crossfade(true)
        }

        val isRead = savedInstanceState?.getBoolean(MainActivity.EXTRA_IS_READ)
            ?: intent.getBooleanExtra(MainActivity.EXTRA_IS_READ, false)
        binding.switchRead.isChecked = isRead

        binding.switchRead.setOnCheckedChangeListener { _, isChecked ->
            updateResult(isChecked)
        }

        binding.linkToFirstArticle.setOnClickListener {
            val firstIntent = Intent(this, FirstArticleActivity::class.java)
            startActivity(firstIntent)
        }

        updateResult(isRead)
    }

    private fun updateResult(isRead: Boolean) {
        val data = Intent().apply {
            putExtra(MainActivity.EXTRA_IS_READ, isRead)
        }
        setResult(RESULT_OK, data)
    }

    private fun finishWithResult() {
        updateResult(binding.switchRead.isChecked)
        onBackPressedDispatcher.onBackPressed()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(MainActivity.EXTRA_IS_READ, binding.switchRead.isChecked)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
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

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }
}