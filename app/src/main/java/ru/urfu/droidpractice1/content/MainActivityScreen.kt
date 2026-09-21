@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    isSecondArticleRead: Boolean = false,
    onNavigateToSecond: () -> Unit = {}
) {
    val context = LocalContext.current
    var likes by rememberSaveable { mutableIntStateOf(0) }
    var dislikes by rememberSaveable { mutableIntStateOf(0) }

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Статья") },
                    actions = {
                        IconButton(onClick = {
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, "Смотри какую статью нашел! Оказывается чипсы — это сплошной дофамин!")
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, "Поделиться")
                            context.startActivity(shareIntent)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Поделиться"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://upload.wikimedia.org/wikipedia/commons/6/69/Potato-Chips.jpg")
                        .addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 10; Mobile)")
                        .crossfade(true)
                        .build(),
                    contentDescription = "Чипсы",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )

                Text(
                    text = "Хрустящая угроза: почему чипсы сводят с ума",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { likes++ }) {
                        Icon(
                            imageVector = Icons.Default.ThumbUp,
                            contentDescription = "Лайк"
                        )
                    }
                    if (likes > 0) {
                        Text(
                            text = likes.toString(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    IconButton(onClick = { dislikes++ }) {
                        Icon(
                            imageVector = Icons.Default.ThumbDown,
                            contentDescription = "Дизлайк"
                        )
                    }
                    if (dislikes > 0) {
                        Text(
                            text = dislikes.toString(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                Text(
                    text = "Каждый из нас хотя бы раз открывал пачку чипсов «только чтобы попробовать одну», а очнувшись, обнаруживал перед собой пустой пакет. В чем же магия?\n\n" +
                            "Секрет кроется в идеальном сочетании соли, жира и углеводов. Производители еды называют это «точкой блаженства» (bliss point). Это математически выверенная формула, которая заставляет наш мозг вырабатывать дофамин и требовать еще.\n\n" +
                            "Кроме того, важен сам звук хруста. Исследования показывают, что чем громче хрустят чипсы, тем более вкусными и свежими они кажутся нашему мозгу. Именно поэтому производители тратят миллионы на разработку идеальной формы ломтика, который будет ломаться с правильным звуком.\n\n" +
                            "Но последствия такого перекуса не самые радужные. Огромное количество трансжиров, пустых калорий и избыток натрия приводят к задержке воды в организме и повышению давления. Стоит ли минутное удовольствие таких рисков? Решать вам.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .alpha(if (isSecondArticleRead) 0.5f else 1f)
                        .clickable { onNavigateToSecond() },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Следующая статья:",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Правда ли, что кола растворяет желудок?",
                            style = MaterialTheme.typography.titleMedium
                        )
                        if (isSecondArticleRead) {
                            Text(
                                text = "Прочитано",
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}