package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private val tag = "Lifecycle_SecondActivity"
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.articleImage.load(getString(R.string.article2_img))

        binding.switchRead.isChecked = intent.getBooleanExtra("isRead", false)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        onBackPressedDispatcher.addCallback(this) {
            val intent = Intent().apply {
                putExtra("isRead", binding.switchRead.isChecked)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    override fun onStart() {
        super.onStart(); Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume(); Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause(); Log.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop(); Log.d(tag, "onStop")
    }

    override fun onRestart() {
        super.onRestart(); Log.d(tag, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy(); Log.d(tag, "onDestroy")
    }
}
