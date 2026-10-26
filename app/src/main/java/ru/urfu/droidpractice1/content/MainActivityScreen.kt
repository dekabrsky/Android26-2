@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    isRead: Boolean = false,
    onNextArticleClick: () -> Unit = {}
) {
    val context = LocalContext.current
    var score by rememberSaveable { mutableIntStateOf(0) }
    val articleTitle = stringResource(id = R.string.article_title)
    val articleText = stringResource(id = R.string.article_text)

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = articleTitle)
                            if (isRead) {
                                Text(
                                    text = " (Прочитано ✅)",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, "$articleTitle\n\n$articleText")
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, null)
                            context.startActivity(shareIntent)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = stringResource(id = R.string.share_article)
                            )
                        }
                    }
                )
            }) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = articleTitle,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

                item {
                    Text(
                        text = stringResource(id = R.string.article_subtitle),
                        style = MaterialTheme.typography.titleMedium,
                        fontStyle = FontStyle.Italic
                    )
                }

                item {
                    AsyncImage(
                        model = "https://preview.redd.it/about-matthias-v0-ks9pqixv3hdg1.png?auto=webp&s=4073db90d4aee199edac16a7c957c2349d0d7ab2",
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                item {
                    Text(
                        text = articleText,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { score++ }) {
                            Icon(
                                imageVector = Icons.Default.ThumbUp,
                                contentDescription = "Like",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        
                        Text(
                            text = stringResource(id = R.string.article_score, score),
                            modifier = Modifier.padding(horizontal = 8.dp),
                            style = MaterialTheme.typography.titleMedium
                        )

                        IconButton(onClick = { score-- }) {
                            Icon(
                                imageVector = Icons.Default.ThumbDown,
                                contentDescription = "Dislike",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }

                item {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onNextArticleClick
                    ) {
                        Text(text = stringResource(id = R.string.next_article))
                    }
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