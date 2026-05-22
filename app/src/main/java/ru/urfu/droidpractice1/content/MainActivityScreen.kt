@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.util.Log
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    isArticleRead: Boolean = false,
    onOpenSecondArticle: () -> Unit = {}
) {
    val context = LocalContext.current

    var likes by rememberSaveable { mutableIntStateOf(0) }
    var dislikes by rememberSaveable { mutableIntStateOf(0) }

    val articleText = stringResource(R.string.article_title) + "\n\n" +
            stringResource(R.string.article_intro) + "\n\n" +
            stringResource(R.string.article_body1) + "\n\n" +
            stringResource(R.string.article_quote) + "\n\n" +
            stringResource(R.string.article_body2) + "\n\n" +
            stringResource(R.string.article_body3)

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.article_title))
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Spacer(modifier = Modifier.height(4.dp))

                // Автор и дата
                Text(
                    text = stringResource(R.string.article_author),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Изображение
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data("https://www.atomic-energy.ru/files/images/2020/05/1459754106-v-rossii-vypustyat-onlajn-igru-transliruy_newsall1.jpg")
                        .crossfade(true)
                        .listener(
                            onStart = { Log.d("CoilDebug", "Image load started") },
                            onSuccess = { _, _ -> Log.d("CoilDebug", "Image loaded successfully") },
                            onError = { _, result -> Log.e("CoilDebug", "Image load error", result.throwable) }
                        )
                        .build(),
                    contentDescription = stringResource(R.string.article_title),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                // Вводный абзац
                Text(
                    text = stringResource(R.string.article_intro),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 26.sp
                )

                // Основной текст
                Text(
                    text = stringResource(R.string.article_body1),
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 22.sp
                )

                // Цитата в карточке
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.article_quote),
                        style = MaterialTheme.typography.bodyMedium,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }

                // Продолжение текста
                Text(
                    text = stringResource(R.string.article_body2),
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 22.sp
                )

                Text(
                    text = stringResource(R.string.article_body3),
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 22.sp
                )

                // Кнопка "Поделиться"
                OutlinedButton(
                    onClick = {
                        val sendIntent = Intent(Intent.ACTION_SEND).apply {
                            putExtra(Intent.EXTRA_TEXT, articleText)
                            type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(sendIntent, null))
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.btn_share))
                }

                // Счётчик лайков / дизлайков
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ThumbUp,
                            contentDescription = stringResource(R.string.label_likes),
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .size(48.dp)
                                .clickable { likes++ }
                                .padding(10.dp)
                        )
                        Text(
                            text = "$likes",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.ThumbDown,
                            contentDescription = stringResource(R.string.label_dislikes),
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .size(48.dp)
                                .clickable { dislikes++ }
                                .padding(10.dp)
                        )
                        Text(
                            text = "$dislikes",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                // Отображение статуса "Прочитано"
                if (isArticleRead) {
                    Text(
                        text = "✓ ${stringResource(R.string.label_article_read)}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                // Кнопка перехода ко второй статье
                Button(
                    onClick = onOpenSecondArticle,
                    modifier = Modifier.fillMaxWidth(),
                    colors = if (isArticleRead)
                        ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        )
                    else
                        ButtonDefaults.buttonColors()
                ) {
                    Text(text = stringResource(R.string.btn_next_article))
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}

@Preview(showBackground = true)
@Composable
fun MainScreenReadPreview() {
    MainActivityScreen(isArticleRead = true)
}
