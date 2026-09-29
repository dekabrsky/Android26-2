package ru.urfu.droidpractice1

import android.content.Context
import android.nfc.Tag
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Switch
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private val TAG = "Lifecycle_SecondActivity"

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate: Экран создан и инициализируется")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val article2ImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeJyFxt0oQIo0QWfBe2Ozr870ttvUDGACimwSjWTSKpQ&s=10"

        binding.article2Image.load(article2ImageUrl) {
            crossfade(true)
            placeholder(R.drawable.image_line_icon)
            error(R.drawable.error_image_photo_icon)
        }

        val isArticleRead = "is_article_read"
        val sharedPrefs = getSharedPreferences("app_settings", Context.MODE_PRIVATE)



        binding.switchIsReaded.isChecked = sharedPrefs.getBoolean(isArticleRead, false)

        binding.switchIsReaded.setOnCheckedChangeListener { _, isChecked ->

            Log.d(TAG, "Тумблер Прочитано изменен пользователем на: $isChecked")

            sharedPrefs.edit()
                .putBoolean(isArticleRead, isChecked)
                .apply()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Экран 2 стал видимым")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Экран 2 стал активен (фокус получен)")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Экран 2 частично скрыт (фокус потерян)")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Экран 2 полностью скрылся из вида")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Экран 2 уничтожен")
    }
}