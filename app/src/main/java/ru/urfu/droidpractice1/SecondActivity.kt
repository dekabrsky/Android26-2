package ru.urfu.droidpractice1

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
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.articleImageView.load("https://www.cookipedia.co.uk/wiki/images/e/ea/Cola.jpg") {
            crossfade(true)
        }
        Log.d("Lifecycle", "SecondActivity: onCreate")

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val isAlreadyRead = intent.getBooleanExtra("EXTRA_IS_READ", false)
        binding.switchRead.isChecked = isAlreadyRead

        binding.switchRead.setOnCheckedChangeListener { _, isChecked ->
            val resultIntent = Intent().apply {
                putExtra("EXTRA_IS_READ", isChecked)
            }
            setResult(RESULT_OK, resultIntent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "SecondActivity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "SecondActivity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "SecondActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "SecondActivity: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "SecondActivity: onDestroy")
    }
}