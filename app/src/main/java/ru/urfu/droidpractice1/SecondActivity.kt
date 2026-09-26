package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class SecondActivity : AppCompatActivity() {

    private lateinit var switchRead: Switch
    private var isRead = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.d("SecondActivity", "onCreate")

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Статья"

        isRead = intent.getBooleanExtra(EXTRA_IS_READ, false)

        switchRead = findViewById(R.id.switch_read)
        switchRead.isChecked = isRead

        switchRead.setOnCheckedChangeListener { _, isChecked ->
            isRead = isChecked
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_IS_READ, isRead)
            setResult(Activity.RESULT_OK, resultIntent)
            Log.d("SecondActivity", "Прочитано: $isRead")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val resultIntent = Intent()
        resultIntent.putExtra(EXTRA_IS_READ, isRead)
        setResult(Activity.RESULT_OK, resultIntent)
        super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SecondActivity", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondActivity", "onDestroy")
    }

    companion object {
        const val EXTRA_IS_READ = "extra_is_read"
    }
}