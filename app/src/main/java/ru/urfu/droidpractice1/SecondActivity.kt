package ru.urfu.droidpractice1

import android.content.Context
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val article2ImageView = findViewById<ImageView>(R.id.article2Image)
        val article2ImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeJyFxt0oQIo0QWfBe2Ozr870ttvUDGACimwSjWTSKpQ&s=10"

        article2ImageView.load(article2ImageUrl) {
            crossfade(true)
            placeholder(R.drawable.image_line_icon)
            error(R.drawable.error_image_photo_icon)
        }

        val isArticleRead = "is_article_read"
        val switchRead = findViewById<Switch>(R.id.switchIsReaded)
        val sharedPrefs = getSharedPreferences("app_settings", Context.MODE_PRIVATE)
        switchRead.isChecked = sharedPrefs.getBoolean(isArticleRead, false)

        switchRead.setOnCheckedChangeListener { _, isChecked ->
            sharedPrefs.edit()
                .putBoolean(isArticleRead, isChecked)
                .apply()
        }
    }
}