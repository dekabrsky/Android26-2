package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import coil.load
import ru.urfu.droidpractice1.content.ArticleImages
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : LifecycleLoggingActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.articleImage.load(ArticleImages.SECOND) {
            crossfade(enable = true)
        }

        val isRead = intent.getBooleanExtra(EXTRA_IS_READ, false)
        binding.readSwitch.isChecked = isRead
        publishResult(isRead)

        binding.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            publishResult(isChecked)
        }
    }
    private fun publishResult(isRead: Boolean) {
        setResult(RESULT_OK, Intent().putExtra(EXTRA_IS_READ, isRead))
    }

    companion object {
        const val EXTRA_IS_READ = "ru.urfu.droidpractice1.extra.IS_READ"
    }
}
