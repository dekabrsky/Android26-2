package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private val tag = "Lifecycle_SecondActivity"
    private lateinit var binding: ActivitySecondBinding

    companion object {
        const val EXTRA_IS_READ = "extra_is_read"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.ivSecondArticle.load("https://picsum.photos/600/301")

        val isInitiallyRead = intent.getBooleanExtra(EXTRA_IS_READ, false)
        binding.switchRead.isChecked = isInitiallyRead

        binding.switchRead.setOnCheckedChangeListener { _, isChecked ->
            val resultIntent = Intent().apply {
                putExtra(EXTRA_IS_READ, isChecked)
            }
            setResult(RESULT_OK, resultIntent)
        }
    }

    override fun onStart() { super.onStart(); Log.d(tag, "onStart") }
    override fun onResume() { super.onResume(); Log.d(tag, "onResume") }
    override fun onPause() { super.onPause(); Log.d(tag, "onPause") }
    override fun onStop() { super.onStop(); Log.d(tag, "onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d(tag, "onDestroy") }
}