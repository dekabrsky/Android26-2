package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var isRead: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isRead = intent.getBooleanExtra("IS_READ_INITIAL", false)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishWithResult()
            }
        })

        binding.articleImage.load("https://images.unsplash.com/photo-1611974789855-9c2a0a7236a3?w=800") {
            crossfade(true)
        }

        binding.toolbar.setNavigationOnClickListener {
            finishWithResult()
        }

        updateReadStateUI(isRead)

        binding.readCard.setOnClickListener {
            binding.readSwitch.toggle()
        }

        binding.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            isRead = isChecked
            updateReadStateUI(isRead)
        }
    }

    private fun updateReadStateUI(read: Boolean) {
        if (binding.readSwitch.isChecked != read) {
            binding.readSwitch.isChecked = read
        }

        if (read) {
            binding.readStatusIcon.setImageResource(android.R.drawable.checkbox_on_background)
            binding.readStatusIcon.setColorFilter("#10B981".toColorInt())
            binding.readStatusTitle.text = "Статья прочитана"
            binding.readStatusSubtitle.text = "Отметка передана в главный журнал"
            binding.readCard.setStrokeColor(android.content.res.ColorStateList.valueOf("#10B981".toColorInt()))
        } else {
            binding.readStatusIcon.setImageResource(android.R.drawable.checkbox_off_background)
            binding.readStatusIcon.setColorFilter("#64748B".toColorInt())
            binding.readStatusTitle.text = "Не прочитано"
            binding.readStatusSubtitle.text = "Нажмите, чтобы отметить прогресс"
            binding.readCard.setStrokeColor(android.content.res.ColorStateList.valueOf("#334155".toColorInt()))
        }
    }

    private fun finishWithResult() {
        val resultIntent = Intent().apply {
            putExtra("IS_READ", isRead)
        }
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}