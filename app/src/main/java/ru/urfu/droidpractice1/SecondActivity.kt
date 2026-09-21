package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var isRead = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { 
            finishWithResult()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishWithResult()
            }
        })

        if (savedInstanceState != null) {
            isRead = savedInstanceState.getBoolean(KEY_IS_READ, false)
        } else {
            isRead = intent.getBooleanExtra(EXTRA_IS_READ, false)
        }
        
        binding.readSwitch.isChecked = isRead

        binding.articleImage.load("https://images.unsplash.com/photo-1635070041078-e363dbe005cb?w=800") {
            crossfade(true)
        }

        binding.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            isRead = isChecked
            Log.d(TAG, "Read status changed: $isRead")
        }
    }
    
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putBoolean(KEY_IS_READ, isRead)
    }
    
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }
    
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }
    
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }
    
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }
    
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
    
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }
    
    
    private fun finishWithResult() {
        val resultIntent = Intent().apply {
            putExtra(EXTRA_IS_READ, isRead)
        }
        setResult(RESULT_OK, resultIntent)
        finish()
    }
    
    companion object {
        const val EXTRA_IS_READ = "is_read"
        private const val KEY_IS_READ = "is_read_state"
        private const val TAG = "SecondActivity"
    }
}