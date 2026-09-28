package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
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

        binding.image.load(getString(R.string.second_article_image))

        val alreadyRead = intent.getBooleanExtra(EXTRA_IS_READ, false)
        binding.readSwitch.isChecked = alreadyRead

        binding.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            setResult(
                Activity.RESULT_OK,
                Intent().putExtra(EXTRA_IS_READ, isChecked)
            )
        }

        setResult(
            Activity.RESULT_OK,
            Intent().putExtra(EXTRA_IS_READ, binding.readSwitch.isChecked)
        )
    }
}