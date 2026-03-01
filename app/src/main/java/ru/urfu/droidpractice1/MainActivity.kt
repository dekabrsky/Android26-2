package ru.urfu.droidpractice1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

class MainActivity : ComponentActivity() {

    private val isSecondReadState = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "MainActivity onCreate")

        isSecondReadState.value = getSharedPreferences("app_prefs", MODE_PRIVATE)
            .getBoolean("second_article_read", false)

        setContent {
            DroidPractice1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArticleScreen(
                        isSecondRead = isSecondReadState.value,
                        onShareClick = { shareArticle() },
                        onNavigateToSecond = {
                            Log.d("MainActivity", "Кнопка нажата")
                            val intent = Intent(this@MainActivity, SecondActivity::class.java)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "MainActivity onResume")
        isSecondReadState.value = getSharedPreferences("app_prefs", MODE_PRIVATE)
            .getBoolean("second_article_read", false)
    }

    private fun shareArticle() {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Прочитал интересную статью! Рекомендую.")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Поделиться статьёй"))
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "MainActivity onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "MainActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "MainActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "MainActivity onDestroy")
    }
}

@Composable
fun ArticleScreen(
    isSecondRead: Boolean,
    onShareClick: () -> Unit,
    onNavigateToSecond: () -> Unit
) {
    var likeCount by rememberSaveable { mutableStateOf(0) }
    var dislikeCount by rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Заголовок первой статьи",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Автор: Рублев",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
            contentDescription = "Изображение к статье",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Text(
            text = "Здесь находится текст статьи.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Button(
            onClick = onShareClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Поделиться")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { likeCount++ }) {
                Text("👍 $likeCount")
            }
            Button(onClick = { dislikeCount++ }) {
                Text("👎 $dislikeCount")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateToSecond,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Читать вторую статью")
        }

        if (isSecondRead) {
            Text(
                text = "Вторая статья прочитана!",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}