package ru.urfu.droidpractice1

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class SecondActivity : AppCompatActivity() {

    private lateinit var switchRead: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.d("Lifecycle", "SecondActivity onCreate")

        switchRead = findViewById(R.id.switchRead)

        val imageView: ImageView = findViewById(R.id.ivArticleImage)
        imageView.setImageResource(android.R.drawable.ic_menu_gallery)
        val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val isRead = prefs.getBoolean("second_article_read", false)
        switchRead.isChecked = isRead

        switchRead.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("second_article_read", isChecked).apply()
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