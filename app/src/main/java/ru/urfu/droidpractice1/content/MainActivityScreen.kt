@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

private const val BASE_LIKES = 128
private const val BASE_DISLIKES = 7

/** Голос пользователя: 1 - лайк, -1 - дизлайк, 0 - не голосовал. */
private const val VOTE_LIKE = 1
private const val VOTE_DISLIKE = -1
private const val VOTE_NONE = 0

@Composable
fun MainActivityScreen() {
    val context = LocalContext.current

    // rememberSaveable переживает и поворот экрана, и пересоздание активити
    var vote by rememberSaveable { mutableIntStateOf(VOTE_NONE) }
    var isSecondRead by rememberSaveable { mutableStateOf(false) }

    // Открываем вторую статью и ждём от неё признак "Прочитано"
    val secondArticleLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            isSecondRead = result.data
                ?.getBooleanExtra(SecondActivity.EXTRA_IS_READ, isSecondRead)
                ?: isSecondRead
        }
    }

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.article_title)) },
                    actions = {
                        IconButton(onClick = { shareArticle(context) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_share),
                                contentDescription = stringResource(id = R.string.share)
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                ArticleContent()

                Spacer(modifier = Modifier.height(20.dp))
                ReactionsRow(
                    vote = vote,
                    onLike = { vote = if (vote == VOTE_LIKE) VOTE_NONE else VOTE_LIKE },
                    onDislike = { vote = if (vote == VOTE_DISLIKE) VOTE_NONE else VOTE_DISLIKE },
                    onShare = { shareArticle(context) }
                )

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(16.dp))

                NextArticleCard(
                    isRead = isSecondRead,
                    onOpen = {
                        secondArticleLauncher.launch(
                            SecondActivity.newIntent(context, isSecondRead)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun ArticleContent() {
    val context = LocalContext.current

    Text(
        text = stringResource(R.string.a1_category).uppercase(),
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        text = stringResource(R.string.a1_title),
        style = MaterialTheme.typography.headlineMedium,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        text = stringResource(R.string.a1_meta),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )

    Spacer(modifier = Modifier.height(12.dp))
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(stringResource(R.string.a1_image_url))
            .crossfade(true)
            .build(),
        contentDescription = stringResource(R.string.a1_image_caption),
        contentScale = ContentScale.Crop,
        placeholder = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
        error = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .clip(RoundedCornerShape(12.dp))
    )
    Text(
        text = stringResource(R.string.a1_image_caption),
        style = MaterialTheme.typography.bodySmall,
        fontStyle = FontStyle.Italic,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = stringResource(R.string.a1_lead),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.height(12.dp))
    Text(text = stringResource(R.string.a1_p1), style = MaterialTheme.typography.bodyLarge)

    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = stringResource(R.string.a1_h1),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = stringResource(R.string.a1_p2), style = MaterialTheme.typography.bodyLarge)

    Spacer(modifier = Modifier.height(16.dp))
    Quote(text = stringResource(R.string.a1_quote))

    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = stringResource(R.string.a1_h2),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(8.dp))
    FactLine(value = "1642 м", label = "максимальная глубина")
    FactLine(value = "636 км", label = "длина озера")
    FactLine(value = "≈ 2600", label = "видов животных, больше половины из них живут только здесь")
    FactLine(value = "336", label = "рек и ручьёв впадает в Байкал, а вытекает одна Ангара")

    Spacer(modifier = Modifier.height(12.dp))
    Text(
        text = stringResource(R.string.a1_p3),
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Justify
    )
}

@Composable
private fun Quote(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Box(
            modifier = Modifier
                .width(4.dp)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.primary)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Composable
private fun FactLine(value: String, label: String) {
    val accent = MaterialTheme.colorScheme.primary
    Text(
        text = buildAnnotatedString {
            append("• ")
            withStyle(
                SpanStyle(fontWeight = FontWeight.Bold, color = accent)
            ) {
                append(value)
            }
            append(" - $label")
        },
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun ReactionsRow(
    vote: Int,
    onLike: () -> Unit,
    onDislike: () -> Unit,
    onShare: () -> Unit,
) {
    val likes = BASE_LIKES + if (vote == VOTE_LIKE) 1 else 0
    val dislikes = BASE_DISLIKES + if (vote == VOTE_DISLIKE) 1 else 0

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        FilterChip(
            selected = vote == VOTE_LIKE,
            onClick = onLike,
            label = { Text(likes.toString()) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_thumb_up),
                    contentDescription = stringResource(R.string.like),
                    modifier = Modifier.size(18.dp)
                )
            }
        )
        FilterChip(
            selected = vote == VOTE_DISLIKE,
            onClick = onDislike,
            label = { Text(dislikes.toString()) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_thumb_down),
                    contentDescription = stringResource(R.string.dislike),
                    modifier = Modifier.size(18.dp)
                )
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(onClick = onShare) {
            Icon(
                painter = painterResource(R.drawable.ic_share),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(stringResource(R.string.share))
        }
    }
}

@Composable
private fun NextArticleCard(isRead: Boolean, onOpen: () -> Unit) {
    val context = LocalContext.current

    Text(
        text = stringResource(R.string.read_also),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(8.dp))
    Card(
        onClick = onOpen,
        colors = CardDefaults.cardColors(
            containerColor = if (isRead) {
                MaterialTheme.colorScheme.surfaceVariant
            } else {
                MaterialTheme.colorScheme.primaryContainer
            }
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(stringResource(R.string.a2_image_url))
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(MaterialTheme.colorScheme.surface),
                error = ColorPainter(MaterialTheme.colorScheme.surface),
                // Прочитанную статью делаем "бледнее"
                alpha = if (isRead) 0.5f else 1f,
                modifier = Modifier
                    .size(88.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.a2_title),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isRead) {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    } else {
                        MaterialTheme.colorScheme.onPrimaryContainer
                    }
                )
                Spacer(modifier = Modifier.height(6.dp))
                if (isRead) {
                    AssistChip(
                        onClick = onOpen,
                        label = { Text(stringResource(R.string.read_done)) },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_check_circle),
                                contentDescription = null,
                                modifier = Modifier.size(AssistChipDefaults.IconSize)
                            )
                        }
                    )
                } else {
                    Text(
                        text = stringResource(R.string.not_read_yet),
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
    Button(onClick = onOpen, modifier = Modifier.fillMaxWidth()) {
        Text(stringResource(R.string.read_next))
    }
}

private fun shareArticle(context: Context) {
    val text = listOf(
        R.string.a1_title,
        R.string.a1_lead,
        R.string.a1_p1,
        R.string.a1_h1,
        R.string.a1_p2,
        R.string.a1_quote,
        R.string.a1_p3,
    ).joinToString(separator = "\n\n") { context.getString(it) }

    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.a1_title))
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(
        Intent.createChooser(sendIntent, context.getString(R.string.share_chooser_title))
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}
