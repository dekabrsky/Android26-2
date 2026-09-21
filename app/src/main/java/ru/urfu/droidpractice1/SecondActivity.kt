package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED
        Log.d("SecondActivity", "onCreate")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.imageView.load(
            "https://avatars.mds.yandex.net/i?id=f7b2df48f6523ce719f7d9f87e9372b14b22a6ad-9068298-images-thumbs&n=13"
        )

        val initialRead = savedInstanceState?.getBoolean("switch_state")
            ?: intent.getBooleanExtra("is_read", false)
        binding.switchRead.isChecked = initialRead

        binding.switchRead.setOnCheckedChangeListener { _, isChecked ->
            setResult(RESULT_OK, Intent().putExtra("is_read", isChecked))
        }

        onBackPressedDispatcher.addCallback(this) {
            setResult(
                RESULT_OK,
                Intent().putExtra("is_read", binding.switchRead.isChecked)
            )
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("switch_state", binding.switchRead.isChecked)
    }

    override fun onStart() {
        super.onStart()
        Log.d("SecondActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SecondActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SecondActivity", "onStop")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d("SecondActivity", "onRestart")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondActivity", "onDestroy")
    }
}