package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
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
        Log.d(TAG, "onCreate, savedInstanceState = $savedInstanceState")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.articleImage.load(getString(R.string.a2_image_url)) {
            crossfade(true)
            val stub = ColorDrawable(getColor(R.color.image_placeholder))
            placeholder(stub)
            error(stub)
        }

        // После поворота берём значение из сохранённого состояния,
        // при первом открытии - то, что передала первая активити.
        val isRead = savedInstanceState?.getBoolean(EXTRA_IS_READ)
            ?: intent.getBooleanExtra(EXTRA_IS_READ, false)
        binding.readSwitch.isChecked = isRead
        binding.readSwitch.setOnCheckedChangeListener { _, checked ->
            Log.d(TAG, "Прочитано = $checked")
        }

        // Любой возврат назад (стрелка в тулбаре, системная кнопка, жест)
        // проходит через OnBackPressedDispatcher: отдаём результат первой активити.
        onBackPressedDispatcher.addCallback(this) {
            setResult(
                Activity.RESULT_OK,
                Intent().putExtra(EXTRA_IS_READ, binding.readSwitch.isChecked)
            )
            finish()
        }
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

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(EXTRA_IS_READ, binding.readSwitch.isChecked)
        Log.d(TAG, "onSaveInstanceState")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy, isFinishing = $isFinishing")
    }

    companion object {
        private const val TAG = "Lifecycle.Second"
        const val EXTRA_IS_READ = "ru.urfu.droidpractice1.EXTRA_IS_READ"

        fun newIntent(context: Context, isRead: Boolean): Intent =
            Intent(context, SecondActivity::class.java).putExtra(EXTRA_IS_READ, isRead)
    }
}
