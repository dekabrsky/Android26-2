package ru.urfu.droidpractice1.content

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity

@Composable
fun MainActivityScreen() {

    val context = LocalContext.current

    var likes by rememberSaveable {
        mutableStateOf(0)
    }

    var dislikes by rememberSaveable {
        mutableStateOf(0)
    }

    var secondArticleRead by rememberSaveable {
        mutableStateOf(false)
    }

    // Получение результата
    val secondArticleLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == Activity.RESULT_OK) {
                secondArticleRead =
                    result.data?.getBooleanExtra(
                        "is_read",
                        false
                    ) ?: false
            }
        }

    val articleText = """
        Kotlin используется для разработки Android-приложений.

        Он имеет простой синтаксис и хорошо работает вместе с Java.

        Сейчас Kotlin является одним из основных языков для Android-разработки.

        Для создания интерфейсов можно использовать Jetpack Compose.
        В Compose интерфейс создаётся прямо в Kotlin-коде.
    """.trimIndent()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            text = "Почему Kotlin используют для Android",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "Небольшая статья о Kotlin и Android",
            fontSize = 18.sp
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        AsyncImage(
            model = R.mipmap.ic_launcher,
            contentDescription = "Kotlin",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = articleText,
            fontSize = 17.sp
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Jetpack Compose",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Jetpack Compose позволяет создавать интерфейс приложения с помощью Kotlin.",
            fontSize = 17.sp
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // Поделиться
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_SEND)

                intent.type = "text/plain"

                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    articleText
                )

                context.startActivity(
                    Intent.createChooser(
                        intent,
                        "Поделиться"
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Поделиться")
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "Оцените статью",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Row {

            // Лайк
            Button(
                onClick = {
                    likes++
                }
            ) {
                Text("👍 $likes")
            }

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            // Дизлайк
            Button(
                onClick = {
                    dislikes++
                }
            ) {
                Text("👎 $dislikes")
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // Статус статьи
        if (secondArticleRead) {
            Text(
                text = "✓ Вторая статья прочитана",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )
        }

        // Вторая статья
        Button(
            onClick = {
                val intent = Intent(
                    context,
                    SecondActivity::class.java
                )

                intent.putExtra(
                    "is_read",
                    secondArticleRead
                )

                secondArticleLauncher.launch(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Открыть вторую статью")
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )
    }
}