package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LIFECYCLE", "SecondActivity onCreate")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isRead = savedInstanceState?.getBoolean("article_read", false) ?: false
        binding.switchRead.isChecked = isRead

        binding.toolbar.setNavigationOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("article_read", binding.switchRead.isChecked)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("article_read", binding.switchRead.isChecked)
    }

    override fun onStart() { super.onStart(); Log.d("LIFECYCLE", "SecondActivity onStart") }
    override fun onResume() { super.onResume(); Log.d("LIFECYCLE", "SecondActivity onResume") }
    override fun onPause() { super.onPause(); Log.d("LIFECYCLE", "SecondActivity onPause") }
    override fun onStop() { super.onStop(); Log.d("LIFECYCLE", "SecondActivity onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d("LIFECYCLE", "SecondActivity onDestroy") }
}