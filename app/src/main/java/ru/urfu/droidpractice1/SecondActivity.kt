package ru.urfu.droidpractice1

import androidx.activity.ComponentActivity
import android.os.Bundle
import android.widget.ImageView
import coil3.load
import coil3.request.crossfade
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val cover = findViewById<ImageView>(R.id.articleCover)
        cover.load("https://images.cybersport.ru/images/material-card/plain/61/6161771e-e0f0-429f-b667-5cd9bf57ebeb.jpg") {
            crossfade(true)
        }
    }
}