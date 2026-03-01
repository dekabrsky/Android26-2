package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private val prefs by lazy { getSharedPreferences("app_prefs", MODE_PRIVATE) }

    private val secondActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        recreate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            MainActivityScreen(
                isSecondArticleRead = prefs.getBoolean(SecondActivity.KEY_SECOND_ARTICLE_READ, false),
                onNavigateToSecondArticle = {
                    secondActivityLauncher.launch(Intent(this, SecondActivity::class.java))
                }
            )
        }
    }
}