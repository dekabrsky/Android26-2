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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

enum class UserReaction {
    NONE, LIKE, DISLIKE
}

@Composable
fun MainActivityScreen() {
    val context = LocalContext.current

    var userReaction by rememberSaveable { mutableStateOf(UserReaction.NONE) }
    var isSecondArticleRead by rememberSaveable { mutableStateOf(value = false) }

    val likes = if (userReaction == UserReaction.LIKE) 1 else 0
    val dislikes = if (userReaction == UserReaction.DISLIKE) 1 else 0

    val secondArticleLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            isSecondArticleRead = result.data
                ?.getBooleanExtra(SecondActivity.EXTRA_IS_READ, isSecondArticleRead)
                ?: isSecondArticleRead
        }
    }

    ArticleScreen(
        likes = likes,
        dislikes = dislikes,
        userReaction = userReaction,
        isSecondArticleRead = isSecondArticleRead,
        onLike = {
            userReaction = if (userReaction == UserReaction.LIKE) UserReaction.NONE else UserReaction.LIKE
        },
        onDislike = {
            userReaction = if (userReaction == UserReaction.DISLIKE) UserReaction.NONE else UserReaction.DISLIKE
        },
        onShare = { context.shareArticle() },
        onOpenSecondArticle = {
            val intent = Intent(context, SecondActivity::class.java)
                .putExtra(SecondActivity.EXTRA_IS_READ, isSecondArticleRead)
            secondArticleLauncher.launch(intent)
        },
    )
}

@Composable
private fun ArticleScreen(
    likes: Int,
    dislikes: Int,
    userReaction: UserReaction,
    isSecondArticleRead: Boolean,
    onLike: () -> Unit,
    onDislike: () -> Unit,
    onShare: () -> Unit,
    onOpenSecondArticle: () -> Unit,
) {
    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.article_title)) },
                    actions = {
                        IconButton(onClick = onShare) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = stringResource(id = R.string.share_action)
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
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                ArticleText()
                HorizontalDivider()
                FeedbackBlock(
                    likes = likes,
                    dislikes = dislikes,
                    userReaction = userReaction,
                    onLike = onLike,
                    onDislike = onDislike,
                )
                RelatedArticleCard(
                    isRead = isSecondArticleRead,
                    onOpen = onOpenSecondArticle,
                )
            }
        }
    }
}

@Composable
private fun ArticleText() {
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = stringResource(R.string.article1_title),
            style = typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(R.string.article1_lead),
            style = typography.titleMedium,
            fontStyle = FontStyle.Italic,
            color = colors.onSurfaceVariant,
        )
        Text(
            text = stringResource(R.string.article1_byline),
            style = typography.labelMedium,
            color = colors.outline,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth(),
        )

        AsyncImage(
            model = ArticleImages.FIRST,
            contentDescription = stringResource(R.string.article1_image_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .clip(RoundedCornerShape(12.dp))
                .background(colors.surfaceVariant),
        )
        Text(
            text = stringResource(R.string.article1_image_caption),
            style = typography.labelSmall,
            color = colors.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        SectionHeading(stringResource(R.string.article1_h1))
        Text(text = stringResource(R.string.article1_p1), style = typography.bodyLarge)

        SectionHeading(stringResource(R.string.article1_h2))
        val highlight = stringResource(R.string.article1_p2_highlight)
        val paragraph = stringResource(R.string.article1_p2, highlight)
        val highlightColor = colors.primary
        val annotated = remember(paragraph, highlight, highlightColor) {
            buildAnnotatedString {
                append(paragraph)
                val start = paragraph.indexOf(highlight)
                if (start >= 0) {
                    addStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = highlightColor,
                            textDecoration = TextDecoration.Underline,
                        ),
                        start,
                        start + highlight.length,
                    )
                }
            }
        }
        Text(text = annotated, style = typography.bodyLarge)

        QuoteBlock(
            text = stringResource(R.string.article1_quote),
            source = stringResource(R.string.article1_quote_source),
        )

        SectionHeading(stringResource(R.string.article1_h3))
        Text(text = stringResource(R.string.article1_p3), style = typography.bodyLarge)
    }
}

@Composable
private fun SectionHeading(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 8.dp),
    )
}

@Composable
private fun QuoteBlock(text: String, source: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Box(
            modifier = Modifier
                .width(4.dp)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(2.dp))
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "«$text»",
                style = MaterialTheme.typography.titleMedium,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Serif,
            )
            Text(
                text = source,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline,
            )
        }
    }
}

@Composable
private fun FeedbackBlock(
    likes: Int,
    dislikes: Int,
    userReaction: UserReaction,
    onLike: () -> Unit,
    onDislike: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = stringResource(R.string.article_feedback_question),
            style = MaterialTheme.typography.titleMedium,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            val isLiked = userReaction == UserReaction.LIKE
            val isDisliked = userReaction == UserReaction.DISLIKE

            if (isLiked) {
                Button(onClick = onLike, modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(R.string.like_button, likes))
                }
            } else {
                OutlinedButton(onClick = onLike, modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(R.string.like_button, likes))
                }
            }

            if (isDisliked) {
                Button(onClick = onDislike, modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(R.string.dislike_button, dislikes))
                }
            } else {
                OutlinedButton(onClick = onDislike, modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(R.string.dislike_button, dislikes))
                }
            }
        }
    }
}

@Composable
private fun RelatedArticleCard(isRead: Boolean, onOpen: () -> Unit) {
    val colors = MaterialTheme.colorScheme
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isRead) colors.secondaryContainer else colors.surfaceVariant
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = stringResource(R.string.related_label),
                style = MaterialTheme.typography.labelLarge,
                color = colors.outline,
            )
            Text(
                text = stringResource(R.string.article2_title),
                style = MaterialTheme.typography.titleLarge,
            )
            if (isRead) {
                Text(
                    text = stringResource(R.string.related_read_mark),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = colors.primary,
                )
            }
            Button(onClick = onOpen) {
                Text(
                    text = stringResource(
                        if (isRead) R.string.related_open_again else R.string.related_open
                    )
                )
            }
        }
    }
}

private fun Context.shareArticle() {
    val text = listOf(
        getString(R.string.article1_title),
        getString(R.string.article1_lead),
        getString(R.string.article1_h1),
        getString(R.string.article1_p1),
        getString(R.string.article1_h2),
        getString(R.string.article1_p2, getString(R.string.article1_p2_highlight)),
        "«${getString(R.string.article1_quote)}»",
        getString(R.string.article1_h3),
        getString(R.string.article1_p3),
    ).joinToString(separator = "\n\n")

    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, getString(R.string.article1_title))
        putExtra(Intent.EXTRA_TEXT, text)
    }
    startActivity(Intent.createChooser(sendIntent, getString(R.string.share_chooser_title)))
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ArticleScreen(
        likes = 1,
        dislikes = 0,
        userReaction = UserReaction.LIKE,
        isSecondArticleRead = true,
        onLike = {},
        onDislike = {},
        onShare = {},
        onOpenSecondArticle = {},
    )
}
