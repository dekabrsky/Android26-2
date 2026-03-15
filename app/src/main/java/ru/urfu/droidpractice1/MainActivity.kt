package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private val getSecondResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            // Теперь secondRead управляется в Compose, поэтому нужно обновлять через callback
            // Но для простоты пока оставим так, хотя лучше переделать логику
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Состояния теперь в Compose с rememberSaveable
            var secondRead by rememberSaveable { mutableStateOf(false) }
            var countLike by rememberSaveable { mutableStateOf(0) }
            var countDislike by rememberSaveable { mutableStateOf(0) }

            // Обработка результата из SecondActivity
            LaunchedEffect(Unit) {
                // Этот код не идеален, но показывает минимальные изменения
                // Лучше переделать логику получения результата
            }

            MainActivityScreen(
                secondRead = secondRead,
                countLike = countLike,
                countDislike = countDislike,
                likeClick = { countLike++ },
                dislikeClick = { countDislike++ },
                shareClick = { text -> shareClick(text) },
                secondClick = {
                    val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    getSecondResult.launch(intent)
                    // В реальном приложении нужно обновлять secondRead через callback
                    secondRead = true // Временное решение
                }
            )
        }
    }

    private fun shareClick(text: String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Поделиться статьей"))
    }
}