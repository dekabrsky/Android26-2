package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate restored=${savedInstanceState != null}")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.readSwitch.isChecked = savedInstanceState?.getBoolean(EXTRA_READ)
            ?: intent.getBooleanExtra(EXTRA_READ, false)
        publishReadState()
        binding.readSwitch.setOnCheckedChangeListener { _, _ -> publishReadState() }
        binding.articleImage.load(IMAGE_URL) {
            crossfade(true)
            placeholder(R.drawable.mountain_placeholder)
            error(R.drawable.mountain_placeholder)
        }
        onBackPressedDispatcher.addCallback(this) {
            publishReadState()
            finish()
        }
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.backButton.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    // Keep the result current for both the toolbar and system back navigation.
    private fun publishReadState() {
        setResult(RESULT_OK, Intent().putExtra(EXTRA_READ, binding.readSwitch.isChecked))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(EXTRA_READ, binding.readSwitch.isChecked)
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState read=${binding.readSwitch.isChecked}")
    }

    override fun onStart() { super.onStart(); Log.d(TAG, "onStart") }
    override fun onResume() { super.onResume(); Log.d(TAG, "onResume") }
    override fun onPause() { Log.d(TAG, "onPause"); super.onPause() }
    override fun onStop() { Log.d(TAG, "onStop"); super.onStop() }
    override fun onRestart() { super.onRestart(); Log.d(TAG, "onRestart") }
    override fun onDestroy() { Log.d(TAG, "onDestroy changingConfigurations=$isChangingConfigurations"); super.onDestroy() }

    companion object {
        const val EXTRA_READ = "ru.urfu.droidpractice1.SECOND_ARTICLE_READ"
        private const val TAG = "SecondActivity"
        private const val IMAGE_URL = "https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?w=1200&q=85&fit=crop"
    }
}
