@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    viewModel: MainViewModel,
    onNavigateToSecond: () -> Unit
) {
    val context = LocalContext.current

    val likes = viewModel.likesCount
    val dislikes = viewModel.dislikesCount
    val isRead = viewModel.isRead

    val articleTitle = "Инсайдеры раскрыли детали четырех новых героев Deadlock"
    val articleItems = listOf(
        "В тестовом билде Deadlock обнаружили упоминания сразу четырех персонажей. Инсайдер Hesy поделился деталями:",
        "Cheshire — герой, вдохновленный Чеширским Котом. Способность Secret Bag позволяет затаскивать союзников в туннели на карте (как у Rem). Пока тиммейт внутри, Cheshire получает баффы.",
        "Ripper — создан на основе старой модели из архива Hero Labs.",
        "Olympia — визуально напоминает Spirit Nuker, подробностей нет.",
        "Kler — новый герой с телепортацией.",
        "⚠️ Инсайдеры предупреждают: это очень ранние наработки, которые, вероятно, никогда не выйдут. Но механика Чеширского Кота уже сейчас выглядит необычно."
    )

    val fullArticle = buildString {
        appendLine(articleTitle)
        appendLine()
        articleItems.forEachIndexed { index, string ->
            appendLine(string)
            if (index < articleItems.size - 1) appendLine()
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
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_TEXT, fullArticle)
                                }
                                context.startActivity(
                                    Intent.createChooser(shareIntent,"Поделиться статьей")
                                )
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Поделиться"
                            )
                        }
                    }
                )
            }) { innerPadding ->
            LazyColumn (
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = articleTitle,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxSize().padding(0.dp),
                    ) {
                        Button(
                            onClick = {
                                viewModel.like()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                contentColor = Color.Black
                            ),
                            modifier = Modifier.padding(0.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ThumbUp,
                                    contentDescription = "Лайк"
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = likes.toString())
                            }
                        }

                        Button(
                            onClick = {
                                viewModel.dislike()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                contentColor = Color.Black
                            ),
                            modifier = Modifier.padding(0.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ThumbDown,
                                    contentDescription = "Дизлайк"
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = dislikes.toString())
                            }
                        }
                    }
                }

                item {
                    AsyncImage(
                        model = R.drawable.cheshire,
                        contentDescription = "Изображение к статье о Deadlock",
                        modifier = Modifier.fillMaxSize().height(220.dp),
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                    )
                }

                items(articleItems) {
                    item -> Text(text = item)
                }

                item {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(8.dp),
                        onClick = {
                            onNavigateToSecond()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isRead) Color.Yellow.copy(alpha=0.5f) else Color.Yellow,
                            contentColor = if (isRead) Color.Black.copy(alpha=0.5f) else Color.Black,
                        )
                    ) {
                        Text(
                            text = "Представляем Ослепительную Селесту", fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}