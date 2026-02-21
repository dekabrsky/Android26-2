package ru.urfu.droidpractice1

import android.content.Context
import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ImageView
import android.widget.Switch
import androidx.compose.ui.res.stringResource
import com.bumptech.glide.Glide
import com.google.android.material.materialswitch.MaterialSwitch
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "SecondActivity:onStart")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val imageView = findViewById<ImageView>(R.id.article_image)
        Glide.with(this).load(getString(R.string.epstein_image_url)).into(imageView)

        prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val readSwitch = findViewById<Switch>(R.id.article_read)
        readSwitch.isChecked = prefs.getBoolean("article_read", false)

        readSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("article_read", isChecked).apply()
        }
    }

    // Упражнение 1
    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "SecondActivity:onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "SecondActivity:onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "SecondActivity:onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "SecondActivity:onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Lifecycle", "SecondActivity:onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "SecondActivity:onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("Lifecycle", "SecondActivity:onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("Lifecycle", "SecondActivity:onRestoreInstanceState")
    }
}