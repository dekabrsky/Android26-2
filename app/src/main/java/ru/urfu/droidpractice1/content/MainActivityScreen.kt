@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    isSecondArticleRead: Boolean,
    onOpenSecondArticle: () -> Unit
) {
    DroidPractice1Theme {
        val context = LocalContext.current
        val shareText = stringResource(id = R.string.first_article_share_text)
        val shareTitle = stringResource(id = R.string.share)

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
                                val intent = Intent(Intent.ACTION_SEND).apply {
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_TEXT, shareText)
                                }
                                context.startActivity(Intent.createChooser(intent, shareTitle))
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_share),
                                contentDescription = stringResource(id = R.string.share_icon_description)
                            )
                        }
                    }
                )
            }) { innerPadding ->
            ArticleContent(
                isSecondArticleRead = isSecondArticleRead,
                onOpenSecondArticle = onOpenSecondArticle,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun ArticleContent(
    isSecondArticleRead: Boolean,
    onOpenSecondArticle: () -> Unit,
    modifier: Modifier = Modifier
) {
    var likes by rememberSaveable { mutableIntStateOf(0) }
    var dislikes by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.first_article_meta),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.first_article_title),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.first_article_subtitle),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(18.dp))
        AsyncImage(
            model = "https://images.unsplash.com/photo-1516321318423-f06f85e504b3?auto=format&fit=crop&w=1200&q=80",
            contentDescription = stringResource(id = R.string.first_article_image_description),
            placeholder = painterResource(id = R.drawable.article_image_placeholder),
            error = painterResource(id = R.drawable.article_image_placeholder),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(20.dp))
        Paragraph(text = stringResource(id = R.string.first_article_paragraph_1))
        Paragraph(text = stringResource(id = R.string.first_article_paragraph_2))
        QuoteBlock(text = stringResource(id = R.string.first_article_quote))
        Paragraph(text = stringResource(id = R.string.first_article_paragraph_3))
        ReactionRow(
            likes = likes,
            dislikes = dislikes,
            onLikeClick = { likes++ },
            onDislikeClick = { dislikes++ }
        )
        Spacer(modifier = Modifier.height(20.dp))
        NextArticleCard(
            isSecondArticleRead = isSecondArticleRead,
            onOpenSecondArticle = onOpenSecondArticle
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun Paragraph(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(bottom = 14.dp)
    )
}

@Composable
private fun QuoteBlock(text: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
private fun ReactionRow(
    likes: Int,
    dislikes: Int,
    onLikeClick: () -> Unit,
    onDislikeClick: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        OutlinedButton(onClick = onLikeClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_thumb_up),
                contentDescription = stringResource(id = R.string.like_icon_description)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(id = R.string.likes_count, likes))
        }
        Spacer(modifier = Modifier.width(12.dp))
        OutlinedButton(onClick = onDislikeClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_thumb_down),
                contentDescription = stringResource(id = R.string.dislike_icon_description)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(id = R.string.dislikes_count, dislikes))
        }
    }
}

@Composable
private fun NextArticleCard(
    isSecondArticleRead: Boolean,
    onOpenSecondArticle: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.next_article),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                if (isSecondArticleRead) {
                    AssistChip(
                        onClick = {},
                        label = { Text(text = stringResource(id = R.string.article_read)) },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = null
                            )
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.second_article_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = stringResource(id = R.string.second_article_description),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(14.dp))
            Button(onClick = onOpenSecondArticle) {
                Text(text = stringResource(id = R.string.open_article))
            }
        }
    }
}

@Composable
private fun PreviewWrapper() {
    Box(
        modifier = Modifier.background(Color.White)
    ) {
        MainActivityScreen(
            isSecondArticleRead = true,
            onOpenSecondArticle = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    PreviewWrapper()
}
