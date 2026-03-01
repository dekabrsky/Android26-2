package ru.urfu.droidpractice1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isSecondArticleRead = mutableStateOf(false)
        val getIsArticleHaveRead =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val result = result.data?.getBooleanExtra("isRead", false) ?: false
                    isSecondArticleRead.value = result
                }
            }
        setContent {
            MainActivityScreen(isSecondArticleRead.value) { intent -> getIsArticleHaveRead.launch(intent)
            }
        }
    }
}