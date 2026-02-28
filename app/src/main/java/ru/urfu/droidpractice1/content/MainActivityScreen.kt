@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    onNavigateToSecond: () -> Unit = {}
) {
    val context = LocalContext.current

    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    var likeCount by remember { mutableStateOf(prefs.getInt("like_count", 0)) }
    var dislikeCount by remember { mutableStateOf(prefs.getInt("dislike_count", 0)) }

    val isSecondArticleRead = prefs.getBoolean("second_article_read", false)

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
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
                                val shareIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, "Посмотрите статью про Чертово Городище!")
                                    type = "text/plain"
                                }
                                context.startActivity(Intent.createChooser(shareIntent, "Поделиться"))
                            }
                        ) {
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
                    .padding(16.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Горы рядом с Екатеринбургом. Чертово Городище.",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(
                            onClick = {
                                likeCount++
                                prefs.edit().putInt("like_count", likeCount).apply()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ThumbUp,
                                contentDescription = "Нравится",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        Text(
                            text = "$likeCount",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(
                            onClick = {
                                dislikeCount++
                                prefs.edit().putInt("dislike_count", dislikeCount).apply()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ThumbUp,
                                contentDescription = "Не нравится",
                                modifier = Modifier
                                    .size(32.dp)
                                    .rotate(180f)
                            )
                        }
                        Text(
                            text = "$dislikeCount",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.mountains),
                    contentDescription = "Чертово городище",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text =  "Под названием Чёртово Городище понимают сразу и гору," +
                            "и скальный массив на её вершине — он выглядит так, " +
                            "словно сложен из бесчисленного количества плит, но на" +
                            " самом деле такой вид ему придало выветривание. Каменная" +
                            " гряда протянулась на 70 м, её вершины, похожие на отдельные" +
                            " башни, возвышаются на 34 м. При этом южная часть Городища пологая," +
                            " поскольку подвергается температурным перепадам и постепенно" +
                            " разрушается — можно увидеть каменные россыпи неподалёку." +
                            " А северная часть — теневая —меньше подвержена разрушению и представляет" +
                            " собой почти отвесную стену со большим числом выступов.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
                )

                Button(
                    onClick = onNavigateToSecond,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSecondArticleRead)
                            Color(0xFFE6E6FA)  // бледно-сиреневый (lavender)
                        else
                            MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = "Читать другую статью",
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        textDecoration = if (isSecondArticleRead)
                            TextDecoration.LineThrough
                        else
                            TextDecoration.None,
                        color = if (isSecondArticleRead) Color.Black else Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}