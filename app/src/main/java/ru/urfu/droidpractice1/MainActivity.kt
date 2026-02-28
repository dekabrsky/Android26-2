package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {
    private var isSecondArticleRead by mutableStateOf(false)

    private val secondActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            isSecondArticleRead = result.data?.getBooleanExtra(SecondActivity.EXTRA_IS_READ, false) ?: false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(
                isSecondArticleRead = isSecondArticleRead,
                onShareClick = ::shareArticle,
                onOpenSecondArticle = ::openSecondArticle
            )
        }
    }

    private fun shareArticle() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Paramount купила Warner Bros за $110 млрд")
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Поделиться статьей")
        startActivity(shareIntent)
    }

    private fun openSecondArticle() {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(SecondActivity.EXTRA_IS_READ, isSecondArticleRead)
        }
        secondActivityLauncher.launch(intent)
    }
}