package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    private val prefs by lazy { getSharedPreferences("app_prefs", MODE_PRIVATE) }

    companion object {
        const val KEY_SECOND_ARTICLE_READ = "second_article_read"
        const val IMAGE_URL = "https://cdn.culture.ru/images/39c3745a-be97-5597-a1dc-fcb79f010c93"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.articleImage.load(IMAGE_URL) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_gallery)
            error(android.R.drawable.ic_menu_gallery)
        }

        binding.readSwitch.isChecked = prefs.getBoolean(KEY_SECOND_ARTICLE_READ, false)

        binding.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(KEY_SECOND_ARTICLE_READ, isChecked).apply()
            setResult(RESULT_OK, Intent().putExtra("article_read", isChecked))
        }
    }
            
    override fun onBackPressed() {
        prefs.edit().putBoolean(KEY_SECOND_ARTICLE_READ, binding.readSwitch.isChecked).apply()
        setResult(RESULT_OK, Intent().putExtra("article_read", binding.readSwitch.isChecked))
        super.onBackPressed()
    }
}