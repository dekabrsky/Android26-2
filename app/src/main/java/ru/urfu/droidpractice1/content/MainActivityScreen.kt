@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    likes: Int,
    dislikes: Int,
    secondArticleRead: Boolean,
    onLike: () -> Unit,
    onDislike: () -> Unit,
    onShare: () -> Unit,
    onNextArticle: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(stringResource(R.string.brand), fontSize = 18.sp,
                    letterSpacing = 3.sp, fontWeight = FontWeight.Bold)
            })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
                .verticalScroll(rememberScrollState()).padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = .4f))
            Text(stringResource(R.string.issue), style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(stringResource(R.string.article_category), color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge, letterSpacing = 2.sp)
            Text(stringResource(R.string.article_title), fontFamily = FontFamily.Serif,
                fontSize = 38.sp, lineHeight = 42.sp, fontWeight = FontWeight.Bold)
            Text(stringResource(R.string.article_meta), style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(stringResource(R.string.article_lead), fontSize = 19.sp, lineHeight = 29.sp)
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://images.unsplash.com/photo-1454496522488-7a8e488e8606?w=1200&q=85&fit=crop")
                        .crossfade(true).build(),
                    contentDescription = stringResource(R.string.image_description),
                    placeholder = painterResource(R.drawable.mountain_placeholder),
                    error = painterResource(R.drawable.mountain_placeholder),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(240.dp).clip(RoundedCornerShape(4.dp))
                )
                Text(stringResource(R.string.image_caption), style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            ArticleHeading(R.string.article_heading_one)
            ArticleParagraph(R.string.article_body_one)
            Text(stringResource(R.string.article_quote), fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic, fontSize = 23.sp, lineHeight = 32.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(24.dp))
            ArticleHeading(R.string.article_heading_two)
            ArticleParagraph(R.string.article_body_two)
            ArticleParagraph(R.string.article_body_three)
            HorizontalDivider()
            Text(stringResource(R.string.reaction_prompt), style = MaterialTheme.typography.titleMedium)
            // FlowRow keeps reaction buttons usable with a large system font.
            ReactionButtons(likes, dislikes, onLike, onDislike)
            OutlinedButton(onClick = onShare, modifier = Modifier.fillMaxWidth().testTag("share")) {
                Text(stringResource(R.string.share))
            }
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp, bottom = 32.dp)
            ) {
                Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(stringResource(R.string.next_label), style = MaterialTheme.typography.labelSmall,
                        letterSpacing = 2.sp)
                    Text(stringResource(if (secondArticleRead) R.string.read_badge else R.string.unread_badge),
                        style = MaterialTheme.typography.labelLarge, modifier = Modifier.testTag("read_status"))
                    Text(stringResource(R.string.second_title_inline), fontFamily = FontFamily.Serif,
                        fontSize = 27.sp, lineHeight = 33.sp)
                    Text(stringResource(R.string.next_description), style = MaterialTheme.typography.bodyMedium)
                    Button(onClick = onNextArticle, modifier = Modifier.fillMaxWidth().testTag("next_article")) {
                        Text(stringResource(if (secondArticleRead) R.string.read_again else R.string.open_article))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ReactionButtons(likes: Int, dislikes: Int, onLike: () -> Unit, onDislike: () -> Unit) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        FilledTonalButton(onClick = onLike, modifier = Modifier.testTag("like")) {
            Text(stringResource(R.string.likes, likes))
        }
        OutlinedButton(onClick = onDislike, modifier = Modifier.testTag("dislike")) {
            Text(stringResource(R.string.dislikes, dislikes))
        }
    }
}

@Composable
private fun ArticleHeading(id: Int) {
    Text(stringResource(id), fontFamily = FontFamily.Serif, fontSize = 26.sp,
        lineHeight = 32.sp, fontWeight = FontWeight.Bold)
}

@Composable
private fun ArticleParagraph(id: Int) {
    Text(stringResource(id), style = MaterialTheme.typography.bodyLarge)
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    DroidPractice1Theme { MainActivityScreen(0, 0, false, {}, {}, {}, {}) }
}
