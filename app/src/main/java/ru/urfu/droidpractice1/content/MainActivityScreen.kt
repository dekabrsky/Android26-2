@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.R.attr.text
import android.R.attr.title
import android.content.Intent
import android.icu.number.Scale
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.ArticleTextStyles
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme


enum class Reaction { LIKE, DISLIKE, NONE }

data class ReactionState(val reaction: Reaction, val counter: Int)

val intent = Intent(Intent.ACTION_SEND).apply {
    type = "text/plain"
    putExtra(Intent.EXTRA_SUBJECT, title)
    putExtra(Intent.EXTRA_TEXT, text)
}

@Composable
fun MainActivityScreen() {
    val context = LocalContext.current

    var reaction by rememberSaveable {
        mutableStateOf(Reaction.NONE)
    }

    var counter by rememberSaveable {
        mutableStateOf(0)
    }

    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Text(
                                stringResource(R.string.article_title)
                            )

                            IconButton(
                                onClick = {context.startActivity(Intent.createChooser(intent, "Поделиться статьей"))},
                                modifier = Modifier
                            ) {
                                Icon(Icons.Default.Share, contentDescription = null)
                            }
                        }
                    }
                )
            }) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                item { TitleBlock(
                    text = "NiKo может закончить карьеру: «В ближайшие несколько лет мне нужно сделать выбор»"
                ) }

                item {
                    AsyncImage(
                        model = "https://images.cybersport.ru/images/material-card/plain/6c/6ccce769-cfa5-4f22-a735-80b8bef621c1.jpg",
                        contentDescription = "картинка Niko",
                        modifier = Modifier
                            .height(180.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop,
                    )
                }

                item {
                    ReactionBar(
                        counter = counter,
                        createdAt = "18-09-2026",
                        onLike = { val r = toggle(reaction, counter, Reaction.LIKE)
                                 counter = r.counter
                                 reaction = r.reaction },
                        onDislike = {val r = toggle(reaction, counter, Reaction.DISLIKE)
                            counter = r.counter
                            reaction = r.reaction },
                        reaction = reaction
                    )
                }

                item { TextBlock(
                    text = "Игрок состава Team Falcons по CS2 Никола NiKo Ковач считает, что ему необходимо принять решение о карьере в ближайшее время. У него есть два варианта развития событий. Цитату опубликовал Telegram-канал CS2NEWS | Counter-Strike 2."
                ) }


                item {
                    QuoteBlock(
                        text = "В ближайшие несколько лет мне нужно сделать выбор: либо перейти на IGL, либо закончить карьеру игрока. Я не готов на 100% снова становиться капитаном.",
                        author = "Никола NiKo Ковач",
                        model = "https://images.cybersport.ru/images/quote-author/plain/22/22e4c87a-c728-42d0-ad33-df9a71d476f3.png"
                    )
                }

                item {
                    TextBlock(
                        text = "Действующий контракт Ковача с Team Falcons продлится до 2028 года. Ранее другие деятели индустрии уже отмечали, что как индивидуальный перформер NiKo уже вряд ли проявит себя, пророча ему место капитана. Прочитать одно из мнений можно в материале."
                    )
                }

                item {
                    NextArticleBlock(
                        title = "ZywOo: «StarLadder StarSeries Fall 2026 показал, что мы всё ещё одна из лучших команд»",
                        date = "19-09-2026",
                        onClick = {}
                    )
                }
            }
        }
    }
}

@Composable
fun TextBlock(text: String, modifier: Modifier = Modifier){
    Text(
        text = text,
        style = ArticleTextStyles.paragraph,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Start,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
fun TitleBlock(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = ArticleTextStyles.title,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 8.dp)
    )
}

@Composable
fun QuoteBlock(text: String, author: String?, model: Any?, modifier: Modifier = Modifier) {
    Spacer(Modifier.height(16.dp))
    Text(
        text = "Публикуется с сохранением орфографии и пунктуации источника",
        style = ArticleTextStyles.paragraph,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Start,
        fontStyle = FontStyle.Italic,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF5EFE6))
            .height(IntrinsicSize.Min)
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = model,
                contentDescription = "картинка Niko",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(30.dp)),
                contentScale = ContentScale.Crop,
            )
            author?.let { author ->
                Text(
                    text = author,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = "« $text »",
            style = ArticleTextStyles.quote,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun ReactionBar(
    counter: Int,
    createdAt: String,
    onLike: () -> Unit,
    onDislike: () -> Unit,
    reaction: Reaction,
    modifier: Modifier = Modifier
){
    val animatedScore by animateIntAsState(
        targetValue = counter,
        label = "score",
    )

    val scoreColor = when {
        animatedScore > 0 -> Color(0xFF2E7D32)
        animatedScore < 0 -> Color(0xFFC62828)
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    Spacer(Modifier.height(16.dp))

    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ReactionButton(
                active = reaction == Reaction.LIKE,
                activeColor = Color(0xFF2E7D32),
                iconActive = Icons.Filled.ThumbUp,
                iconInactive = Icons.Outlined.ThumbUp,
                contentDescription = "Лайк",
                onClick = onLike,
            )

            Spacer(Modifier.width(4.dp))

            ReactionButton(
                active = reaction == Reaction.DISLIKE,
                activeColor = Color(0xFFC62828),
                iconActive = Icons.Filled.ThumbUp,
                iconInactive = Icons.Outlined.ThumbUp,
                contentDescription = "Дизлайк",
                onClick = onDislike,
                modifier = Modifier.scale(1f, -1f)
            )

            Spacer(Modifier.width(12.dp))

            Text(
                text = if (animatedScore > 0) "+$animatedScore" else animatedScore.toString(),
                color = scoreColor,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = createdAt,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
    }

    Spacer(Modifier.height(16.dp))
}

fun toggle(current: Reaction, counter: Int, target: Reaction): ReactionState  {
    if (current == target) {
        return when (target) {
            Reaction.LIKE -> ReactionState(
                reaction = Reaction.NONE,
                counter = counter - 1,
            )
            Reaction.DISLIKE -> ReactionState(
                reaction = Reaction.NONE,
                counter = counter + 1,
            )
            Reaction.NONE -> ReactionState(current, counter)
        }
    }

    val delta = when (current) {
        Reaction.LIKE -> when (target) {
            Reaction.DISLIKE -> -2
            Reaction.NONE -> -1
            Reaction.LIKE -> 0
        }
        Reaction.DISLIKE -> when (target) {
            Reaction.LIKE -> +2
            Reaction.NONE -> +1
            Reaction.DISLIKE -> 0
        }
        Reaction.NONE -> when (target) {
            Reaction.LIKE -> +1
            Reaction.DISLIKE -> -1
            Reaction.NONE -> 0
        }
    }

    return ReactionState(
        reaction = target,
        counter = counter + delta,
    )
}

@Composable
private fun ReactionButton(
    active: Boolean,
    activeColor: Color,
    iconActive: ImageVector,
    iconInactive: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(50)),
    ) {
        Icon(
            imageVector = if (active) iconActive else iconInactive,
            contentDescription = contentDescription,
            tint = if (active) activeColor else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = modifier.size(22.dp),
        )
    }
}

@Composable
fun NextArticleBlock(
    title: String,
    date: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
    ) {
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

        Spacer(Modifier.height(16.dp))

        Text(
            text = "СЛЕДУЮЩАЯ СТАТЬЯ",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable(onClick = onClick, indication = LocalIndication.current, interactionSource = remember { MutableInteractionSource() })
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Spacer(Modifier.width(12.dp))

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(22.dp),
            )
        }

        Spacer(Modifier.height(16.dp))

        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}