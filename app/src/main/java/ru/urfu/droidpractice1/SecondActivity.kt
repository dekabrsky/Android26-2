package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

private const val KEY_LIKES = "likes"
private const val KEY_DISLIKES = "dislikes"

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    // Текущее состояние переключателя
    private var isRead = false
    private var likes = 0
    private var dislikes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "SecondActivity: onCreate")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Загружаем картинку через Coil
        binding.ivArticleImage.load("https://www.gstatic.com/webp/gallery/1.png") {
            crossfade(true)
        }

        // Восстанавливаем состояние переключателя и счётчиков
        isRead = savedInstanceState?.getBoolean(EXTRA_IS_READ, false)
            ?: intent.getBooleanExtra(EXTRA_IS_READ, false)
        likes = savedInstanceState?.getInt(KEY_LIKES, 0) ?: 0
        dislikes = savedInstanceState?.getInt(KEY_DISLIKES, 0) ?: 0

        binding.switchRead.isChecked = isRead
        binding.tvLikeCount.text = likes.toString()
        binding.tvDislikeCount.text = dislikes.toString()

        // изменение переключателя
        binding.switchRead.setOnCheckedChangeListener { _, checked ->
            isRead = checked
            val resultIntent = Intent().apply {
                putExtra(EXTRA_IS_READ, isRead)
            }
            setResult(RESULT_OK, resultIntent)
        }

        // Кнопки лайк / дизлайк
        binding.btnLike.setOnClickListener {
            likes++
            binding.tvLikeCount.text = likes.toString()
        }
        binding.btnDislike.setOnClickListener {
            dislikes++
            binding.tvDislikeCount.text = dislikes.toString()
        }

        // Кнопка "Поделиться"
        binding.btnShare.setOnClickListener {
            val shareText = getString(R.string.article2_title) + "\n\n" +
                    getString(R.string.article2_intro) + "\n\n" +
                    getString(R.string.article2_body1) + "\n\n" +
                    getString(R.string.article2_quote) + "\n\n" +
                    getString(R.string.article2_body2) + "\n\n" +
                    getString(R.string.article2_body3)
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, shareText)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(sendIntent, null))
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(EXTRA_IS_READ, isRead)
        outState.putInt(KEY_LIKES, likes)
        outState.putInt(KEY_DISLIKES, dislikes)
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

    override fun onRestart() {
        super.onRestart()
        Log.d("Lifecycle", "SecondActivity: onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "SecondActivity: onDestroy")
    }
}
