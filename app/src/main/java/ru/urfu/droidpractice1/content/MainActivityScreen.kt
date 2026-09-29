package ru.urfu.droidpractice1.content

import android.content.Intent
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityScreen(
    secondArticleRead: Boolean,
    onOpenSecondArticle: () -> Unit
) {
    var likes by rememberSaveable {
        mutableIntStateOf(0)
    }

    var dislikes by rememberSaveable {
        mutableIntStateOf(0)
    }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Статья")
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Почему космос чёрный?",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Коротко о том, почему ночью мы видим тёмное небо, несмотря на огромное количество звёзд во Вселенной.",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            AsyncImage(
                model = R.drawable.article_space,
                contentDescription = "Космос",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Основная идея",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Если посмотреть на ночное небо, оно кажется почти полностью чёрным. На первый взгляд это может показаться странным: во Вселенной существуют миллиарды галактик, а в каждой галактике — огромное количество звёзд.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Однако свет от большинства звёзд не достигает Земли достаточно ярким, чтобы сделать всё небо светлым. Кроме того, Вселенная расширяется, а многие источники света находятся настолько далеко, что их излучение становится очень слабым.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Именно поэтому между яркими объектами мы видим огромные области тёмного пространства.",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Оцените статью",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {

                IconButton(
                    onClick = {
                        likes++
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = "Нравится"
                    )
                }

                Text(
                    text = likes.toString(),
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.width(24.dp))

                IconButton(
                    onClick = {
                        dislikes++
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ThumbDown,
                        contentDescription = "Не нравится"
                    )
                }

                Text(
                    text = dislikes.toString(),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"

                        putExtra(
                            Intent.EXTRA_TEXT,
                            """
                            Почему космос чёрный?

                            Если посмотреть на ночное небо, оно кажется почти полностью чёрным. Свет от большинства звёзд не достигает Земли достаточно ярким, чтобы сделать всё небо светлым.
                            """.trimIndent()
                        )
                    }

                    context.startActivity(
                        Intent.createChooser(
                            shareIntent,
                            "Поделиться статьёй"
                        )
                    )
                }
            ) {
                Text("Поделиться статьёй")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onOpenSecondArticle
            ) {
                Text(
                    if (secondArticleRead) {
                        "Вторая статья прочитана"
                    } else {
                        "Открыть вторую статью"
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainActivityScreenPreview() {
    DroidPractice1Theme {
        MainActivityScreen(
            secondArticleRead = false,
            onOpenSecondArticle = {}
        )
    }
}