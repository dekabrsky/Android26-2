package ru.urfu.droidpractice1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.urfu.droidpractice1.content.MainActivityScreen
import androidx.compose.runtime.mutableStateOf
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : ComponentActivity() {

    val isSecondArticleRead = mutableStateOf(false)

    val secondActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val value = result.data?.getBooleanExtra("isRead", false) ?: false
                isSecondArticleRead.value = value
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainActivityScreen(
                isSecondArticleRead = isSecondArticleRead.value,
                startSecondArticle = { intent -> secondActivityLauncher.launch(intent)
                }
            )
        }
    }
}