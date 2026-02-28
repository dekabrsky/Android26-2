@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    isSecondArticleRead: Boolean,
    onShareClick: () -> Unit,
    onOpenSecondArticle: () -> Unit
) {
    var likesCount by rememberSaveable { mutableIntStateOf(0) }
    var dislikesCount by rememberSaveable { mutableIntStateOf(0) }

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { ArticleTopAppBar(onShareClick) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                ArticleHeader("Paramount купила Warner Bros за $110 млрд")

                ArticleParagraph(
                    "Paramount Skydance купила Warner Bros Discovery за $110 млрд. " +
                    "Сделка состоялась после того, как из борьбы за конгломерат " +
                    "вышел крупнейший стриминговый сервис Netflix."
                )

                ArticleImage("https://habrastorage.org/r/w1560/getpro/habr/upload_files/df6/985/55b/df698555b4e9dd7bfaee56d6f84a3a71.jpg")

                ArticleParagraph(
                    "Соглашение было подписано 27 февраля после долгих торгов. " +
                    "Paramount предложила за акцию Warner Bros. $31, " +
                    "что оказалось выгоднее $27,75 от Netflix."
                )

                ArticleQuote(
                    "«У Netflix было законное право сравняться с предложением Paramount. " +
                    "Как вы все знаете, в конечном итоге они решили этого не делать. " +
                    "В результате сегодня утром было подписано соглашение с Paramount. " +
                    "Вот на каком этапе всё сейчас», — рассказал Брюс Кэмпбелл, " +
                    "директор по доходам и стратегии Warner Bros., на встрече с общественностью."
                )

                ArticleParagraph(
                    "Отмечается, что после новостей о соглашении в цене выросли " +
                    "как акции Paramount (на 24%), так и Netflix " +
                    "(на 13%: инвесторы стриминговой площадки приветствовали решение " +
                    "компании отказаться от участия в борьбе за Warner Bros."
                )

                FeedbackRow(
                    likesCount = likesCount,
                    dislikesCount = dislikesCount,
                    onLikeClick = { likesCount++ },
                    onDislikeClick = { dislikesCount++ }
                )

                NextArticleCard(
                    isRead = isSecondArticleRead,
                    title = "Инженеры Uber «клонировали» своего босса с помощью ИИ. Он не против",
                    onClick = onOpenSecondArticle
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
private fun ArticleTopAppBar(onShareClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Статья") },
        actions = {
            IconButton(onClick = onShareClick) {
                Icon(Icons.Default.Share, contentDescription = "Поделиться")
            }
        }
    )
}

@Composable
private fun ArticleHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun ArticleParagraph(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun ArticleImage(url: String) {
    AsyncImage(
        model = url,
        contentDescription = "Фото статьи",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun ArticleQuote(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(horizontal = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .width(4.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(2.dp))
                .background(MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun FeedbackRow(
    likesCount: Int,
    dislikesCount: Int,
    onLikeClick: () -> Unit,
    onDislikeClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onLikeClick) {
            Icon(Icons.Default.ThumbUp, contentDescription = "Лайк")
        }
        Text(text = likesCount.toString(), style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.width(24.dp))

        IconButton(onClick = onDislikeClick) {
            Icon(Icons.Default.ThumbDown, contentDescription = "Дизлайк")
        }
        Text(text = dislikesCount.toString(), style = MaterialTheme.typography.titleMedium)
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
private fun NextArticleCard(
    isRead: Boolean,
    title: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isRead) Color(0xFFE8F5E9) else Color(0xFFF3E5F5)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.bodyMedium)
            if (isRead) {
                Text(
                    text = "Прочитано",
                    color = Color(0xFF2E7D32),
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}