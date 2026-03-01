package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    private val TAG = "Lifecycle_MainActivity"

    private var onReadResult: ((Boolean) -> Unit)? = null

    private val secondActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val isRead = result.data?.getBooleanExtra("IS_READ", false) ?: false
            onReadResult?.invoke(isRead)
            Log.d(TAG, "Результат из SecondActivity: прочитано = $isRead")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Состояния лайков и дизлайков
            var isArticle2Read by rememberSaveable { mutableStateOf(false) }
            var likes by rememberSaveable { mutableIntStateOf(0) }
            var dislikes by rememberSaveable { mutableIntStateOf(0) }

            onReadResult = { isArticle2Read = it }

            val context = LocalContext.current

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Плашка "Прочитано"
                if (isArticle2Read) {
                    Surface(
                        color = Color(0xFFE8F5E9),
                        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(
                            text = "✓ Вы узнали всё про Териберку!",
                            modifier = Modifier.padding(12.dp),
                            color = Color(0xFF2E7D32),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Text(
                    text = "Териберка: На краю земли",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                AsyncImage(
                    model = "https://picsum.photos/seed/arctic/1000/600",
                    contentDescription = "Северный пейзаж",
                    modifier = Modifier.fillMaxWidth().height(250.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Териберка — это единственное место в России, куда можно доехать на машине, чтобы увидеть открытый Баренцево море и Арктику. Здесь заброшенные корабли встречаются с суровой красотой тундры, а зимой небо расцветает северным сиянием.",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Ряд с кнопками
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Лайк
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { likes++ }
                    ) {
                        Text("❤️ $likes")
                    }

                    // Дизлайк (Новая кнопка)
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { dislikes++ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE57373))
                    ) {
                        Text("👎 $dislikes")
                    }

                    // Поделиться
                    Button(
                        modifier = Modifier.weight(1.2f),
                        onClick = {
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, "Посмотри, какая магия на Севере! Читаю про Териберку.")
                                type = "text/plain"
                            }
                            context.startActivity(Intent.createChooser(sendIntent, "Поделиться"))
                        }
                    ) {
                        Text("Share")
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        val intent = Intent(context, SecondActivity::class.java).apply {
                            putExtra("ALREADY_READ", isArticle2Read)
                        }
                        secondActivityLauncher.launch(intent)
                    }
                ) {
                    Text("Узнать про кладбище кораблей (XML)")
                }
            }
        }
    }

    override fun onStart() { super.onStart(); Log.d(TAG, "onStart") }
    override fun onResume() { super.onResume(); Log.d(TAG, "onResume") }
    override fun onPause() { super.onPause(); Log.d(TAG, "onPause") }
    override fun onStop() { super.onStop(); Log.d(TAG, "onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy") }
}