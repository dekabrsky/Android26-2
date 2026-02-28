package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        loadArticleImage()
        initReadSwitch()
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun loadArticleImage() {
        binding.articleImage.load(IMAGE_URL) {
            crossfade(true)
        }
    }

    private fun initReadSwitch() {
        with(binding.switchRead) {
            isChecked = intent.getBooleanExtra(EXTRA_IS_READ, false)

            setOnCheckedChangeListener { _, isChecked ->
                val resultIntent = Intent().apply {
                    putExtra(EXTRA_IS_READ, isChecked)
                }
                setResult(Activity.RESULT_OK, resultIntent)
            }
        }
    }

    companion object {
        const val EXTRA_IS_READ = "IS_READ"
        private const val IMAGE_URL = "https://habrastorage.org/r/w1560/getpro/habr/upload_files/2b8/4c4/ec2/2b84c4ec2c15af5293d324e9b8dc7e0b.jpg"
    }
}