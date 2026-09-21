@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

private val ToolbarPurple = Color(0xFF3700B3)
private val BodyText = Color(0xFF424242)
private val TitleText = Color(0xFF212121)

@Composable
private fun spResource(id: Int): TextUnit {
    val density = LocalDensity.current
    val px = LocalContext.current.resources.getDimension(id)
    return with(density) { px.toSp() }
}

@Composable
fun MainActivityScreen(
    rating: Int = 0,
    isSecondArticleRead: Boolean = false,
    onShareClick: () -> Unit = {},
    onLikeClick: () -> Unit = {},
    onDislikeClick: () -> Unit = {},
    onOpenSecondArticle: () -> Unit = {},
) {
    val horizontalPadding = dimensionResource(id = R.dimen.article_horizontal_padding)
    val titleSize = spResource(R.dimen.article_title_size)
    val leadSize = spResource(R.dimen.article_lead_size)
    val bodySize = spResource(R.dimen.article_body_size)
    val imageHeight = dimensionResource(id = R.dimen.article_image_height)

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article1_toolbar_title),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    actions = {
                        IconButton(onClick = onShareClick) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = stringResource(id = R.string.share)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = ToolbarPurple,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    )
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = horizontalPadding, vertical = 12.dp)
                    .padding(bottom = 24.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.article1_subtitle),
                    style = MaterialTheme.typography.labelMedium,
                    color = ToolbarPurple,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = stringResource(id = R.string.article1_title),
                    color = TitleText,
                    fontSize = titleSize,
                    fontWeight = FontWeight.Bold,
                    lineHeight = titleSize * 1.25f
                )
                Spacer(modifier = Modifier.height(12.dp))
                val context = LocalContext.current
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(R.drawable.article1_photo)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(id = R.string.article1_title),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFECEFF1)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = stringResource(id = R.string.article1_lead),
                    color = TitleText,
                    fontSize = leadSize,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic,
                    lineHeight = leadSize * 1.35f
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id = R.string.article1_body),
                    color = BodyText,
                    fontSize = bodySize,
                    textAlign = TextAlign.Justify,
                    lineHeight = bodySize * 1.4f
                )
                Spacer(modifier = Modifier.height(16.dp))
                RatingRow(
                    rating = rating,
                    onLikeClick = onLikeClick,
                    onDislikeClick = onDislikeClick
                )
                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = stringResource(id = R.string.read_next),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(6.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = onOpenSecondArticle)
                        .padding(vertical = 6.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.next_article),
                        style = MaterialTheme.typography.bodyLarge,
                        color = ToolbarPurple,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 22.sp
                    )
                    Text(
                        text = stringResource(
                            id = if (isSecondArticleRead) {
                                R.string.article_read
                            } else {
                                R.string.article_unread
                            }
                        ),
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isSecondArticleRead) {
                            Color(0xFF00796B)
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        },
                        fontStyle = if (isSecondArticleRead) FontStyle.Italic else FontStyle.Normal
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = onOpenSecondArticle,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.read_next))
                }
            }
        }
    }
}

@Composable
private fun RatingRow(
    rating: Int,
    onLikeClick: () -> Unit,
    onDislikeClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        IconButton(onClick = onLikeClick) {
            Icon(
                imageVector = Icons.Default.ThumbUp,
                contentDescription = stringResource(id = R.string.like),
                modifier = Modifier.size(24.dp),
                tint = ToolbarPurple
            )
        }
        IconButton(onClick = onDislikeClick) {
            Icon(
                imageVector = Icons.Default.ThumbDown,
                contentDescription = stringResource(id = R.string.dislike),
                modifier = Modifier.size(24.dp),
                tint = ToolbarPurple
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(id = R.string.rating, rating),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = TitleText
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen(rating = 3, isSecondArticleRead = true)
}
