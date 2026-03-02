package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Switch
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "SecondActivity onCreate")
        setContentView(R.layout.activity_second)

        val imageView = findViewById<ImageView>(R.id.secondImage)
        Glide.with(this)
            .load("https://avatars.mds.yandex.net/i?id=bfbbe8812b6eea1ead7123e2a80c04d57b9c7c48-9666026-images-thumbs&n=13")
            .into(imageView)

        val readSwitch = findViewById<Switch>(R.id.readSwitch)
        val wasRead = intent.getBooleanExtra("was_read", false)
        readSwitch.isChecked = wasRead

        readSwitch.setOnCheckedChangeListener { _, isChecked ->
            val resultIntent = Intent()
            resultIntent.putExtra("is_read", isChecked)
            setResult(RESULT_OK, resultIntent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "SecondActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "SecondActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "SecondActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "SecondActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "SecondActivity onDestroy")
    }
}