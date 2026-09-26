package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private var isSecondArticleRead = false

    private val secondActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            isSecondArticleRead = result.data?.getBooleanExtra(
                SecondActivity.EXTRA_IS_READ, false
            ) ?: false
            Log.d("MainActivity", "Вторая статья прочитана: $isSecondArticleRead")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate")

        setContent {
            MainActivityScreen(
                isSecondArticleRead = isSecondArticleRead,
                onNavigateToSecondArticle = {
                    val intent = Intent(this, SecondActivity::class.java)
                    intent.putExtra(SecondActivity.EXTRA_IS_READ, isSecondArticleRead)
                    secondActivityLauncher.launch(intent)
                }
            )
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy")
    }
}