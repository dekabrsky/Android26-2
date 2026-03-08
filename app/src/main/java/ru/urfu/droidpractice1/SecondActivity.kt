package ru.urfu.droidpractice1

import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import androidx.activity.addCallback
import android.content.Intent
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

        val articleRead = savedInstanceState?.getBoolean("article_read", false) ?: false ||
                            intent.getBooleanExtra("article_read", false);
        val readSwitch = binding.articleRead;
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        readSwitch.isChecked = articleRead;

        onBackPressedDispatcher.addCallback(this) {
            val intent = Intent().apply {
                putExtra("article_read", readSwitch.isChecked);
            }

            setResult(RESULT_OK, intent);
            finish();
        }

        val imageView = binding.articleImage;
        Glide.with(this).load(getString(R.string.epstein_image_url)).into(imageView)
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

        outState.putBoolean("article_read", binding.articleRead.isChecked);
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("Lifecycle", "SecondActivity:onRestoreInstanceState")
    }
}