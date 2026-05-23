@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen() {
    val context = LocalContext.current
    val imageUrl = "https://avatars.mds.yandex.net/i?id=16cb7d59a1595afc3cbe69f6a371bb23bd17a15e-5350576-images-thumbs&n=13"
    val articleText = """
        Что такое Android-приложение

        Android-приложение - это программа для телефона, планшета или другого устройства на Android. Оно состоит из экранов, кода и ресурсов: текста, картинок, цветов и настроек.

        Пользователь видит интерфейс, нажимает кнопки и переходит между экранами. А внутри приложение реагирует на эти действия: открывает новые страницы, сохраняет данные или показывает информацию.
    """.trimIndent()

    var likes by rememberSaveable { mutableStateOf(0) }
    var dislikes by rememberSaveable { mutableStateOf(0) }
    var secondArticleRead by rememberSaveable { mutableStateOf(false) }
    val secondActivityLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        result.data?.let { data ->
            secondArticleRead = data.getBooleanExtra("isRead", secondArticleRead)
        }
    }

    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title)
                        )
                    }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Что такое Android-приложение",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Коротко о том, из чего состоит обычное мобильное приложение.",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Android-приложение - это программа для телефона, планшета или другого устройства на Android. Оно состоит из экранов, кода и ресурсов: текста, картинок, цветов и настроек.",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Пользователь видит интерфейс, нажимает кнопки и переходит между экранами. А внутри приложение реагирует на эти действия: открывает новые страницы, сохраняет данные или показывает информацию.",
                    style = MaterialTheme.typography.bodyLarge
                )

                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Android",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { likes++ }
                    ) {
                        Text(text = "Лайк: $likes")
                    }

                    OutlinedButton(
                        onClick = { dislikes++ }
                    ) {
                        Text(text = "Дизлайк: $dislikes")
                    }
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        val sendIntent = Intent(Intent.ACTION_SEND)
                        sendIntent.type = "text/plain"
                        sendIntent.putExtra(Intent.EXTRA_TEXT, articleText)
                        val shareIntent = Intent.createChooser(sendIntent, "Поделиться статьей")
                        context.startActivity(shareIntent)
                    }
                ) {
                    Text(text = "Поделиться")
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        val intent = Intent(context, SecondActivity::class.java)
                        intent.putExtra("isRead", secondArticleRead)
                        secondActivityLauncher.launch(intent)
                    }
                ) {
                    Text(text = "Открыть вторую статью")
                }

                Text(
                    text = if (secondArticleRead) {
                        "Вторая статья прочитана"
                    } else {
                        "Вторая статья еще не прочитана"
                    },
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}
