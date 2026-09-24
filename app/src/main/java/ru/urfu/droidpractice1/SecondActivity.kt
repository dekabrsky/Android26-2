package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : LoggedActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.articleImage.load(getString(R.string.second_image_url)) {
            crossfade(true)
            placeholder(R.drawable.mountain_placeholder)
            error(R.drawable.mountain_placeholder)
        }
        binding.readSwitch.isChecked = savedInstanceState?.getBoolean(EXTRA_READ)
            ?: intent.getBooleanExtra(EXTRA_READ, false)
        updateResult(binding.readSwitch.isChecked)
        binding.readSwitch.setOnCheckedChangeListener { _, checked -> updateResult(checked) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(EXTRA_READ, binding.readSwitch.isChecked)
        super.onSaveInstanceState(outState)
    }

    private fun updateResult(read: Boolean) {
        setResult(RESULT_OK, Intent().putExtra(EXTRA_READ, read))
    }

    companion object {
        const val EXTRA_READ = "ru.urfu.droidpractice1.ARTICLE_READ"
    }
}
