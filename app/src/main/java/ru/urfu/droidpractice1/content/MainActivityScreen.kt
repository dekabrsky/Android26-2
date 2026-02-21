@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

private const val ARTICLE2_TITLE =
    "Клебо — уже 10-кратный! Но для рекордного золота ему пришлось хорошенько поработать"

private const val ARTICLE2_IMAGE_URL =
    "https://sportnews.24tv.ua/resources/photos/news/202602/3009184.jpg?v=1770988364000&w=1300&h=730&fit=cover&output=webp&q=90"

@Composable
fun MainActivityScreen(
    isFirstArticleRead: Boolean = false,
    isSecondArticleRead: Boolean = false,
    onNavigateToFirst: () -> Unit = {},
    onNavigateToSecond: () -> Unit = {}
) {
    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Олимпийские новости") }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                ArticleCard(
                    title = ARTICLE1_TITLE,
                    imageUrl = ARTICLE1_IMAGE_URL,
                    isRead = isFirstArticleRead,
                    onClick = onNavigateToFirst
                )

                Spacer(modifier = Modifier.height(16.dp))

                ArticleCard(
                    title = ARTICLE2_TITLE,
                    imageUrl = ARTICLE2_IMAGE_URL,
                    isRead = isSecondArticleRead,
                    onClick = onNavigateToSecond
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun ArticleCard(
    title: String,
    imageUrl: String,
    isRead: Boolean,
    onClick: () -> Unit
) {
    val cardAlpha = if (isRead) 0.45f else 1f

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(cardAlpha)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (isRead) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "✓ Прочитано",
                        fontSize = 13.sp,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
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
