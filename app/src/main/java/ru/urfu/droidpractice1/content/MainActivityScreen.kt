@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.draw.alpha
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    isSecondArticleRead: Boolean,
    openSecondArticle: () -> Unit
) {

    val context = LocalContext.current

    val articleText = stringResource(R.string.article1_body)
    val article2Title = stringResource(R.string.article2_title)
    val buttonAlpha = if (isSecondArticleRead) 0.6f else 1f

    var likes by rememberSaveable { mutableIntStateOf(0) }
    var dislikes by rememberSaveable { mutableIntStateOf(0) }

    DroidPractice1Theme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.article1_title)) },
                    actions = {
                        IconButton(onClick = {
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, articleText)
                            }
                            context.startActivity(
                                Intent.createChooser(intent, "Поделиться")
                            )
                        }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Поделиться"
                            )
                        }
                    }
                )
            }
        ) { padding ->

            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.article_image1),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                Text(
                    text = stringResource(R.string.article1_body),
                    style = MaterialTheme.typography.bodyLarge
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = { likes++ }) {
                        Text("👍 $likes")
                    }
                    Button(onClick = { dislikes++ }) {
                        Text("👎 $dislikes")
                    }
                }

                Button(
                    onClick = openSecondArticle,
                    modifier = Modifier.alpha(buttonAlpha)
                ) {
                    Text(article2Title)
                }
            }
        }
    }
}