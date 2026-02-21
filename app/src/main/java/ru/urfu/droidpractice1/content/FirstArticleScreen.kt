@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

const val ARTICLE1_TITLE =
    "Йоханнес Клебо стал 11-кратным олимпийским чемпионом после победы в марафоне на ОИ-2026"

const val ARTICLE1_IMAGE_URL =
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSsJ3aiaueS1PfCL4hFD-_g8mW5eCy22meyvA&s"

const val ARTICLE1_FULL_TEXT =
    "Норвежский лыжник Йоханнес Клебо после победы в марафоне на Олимпийских играх — 2026 стал " +
            "11-кратным олимпийским чемпионом, выиграв все гонки на текущей Олимпиаде.\n\n" +
            "До этого на Олимпиаде в Италии Клебо выигрывал скиатлон, командный и личный спринты, " +
            "мужскую эстафету, а также гонку 10 км свободным стилем.\n\n" +
            "Также уникальным достижением стал факт завоевания спортсменом шести золотых медалей " +
            "из шести возможных на Олимпийских играх — 2026. Это стало абсолютным рекордом, так как " +
            "ни одному спортсмену не удавалось завоевать шесть золотых медалей на одной зимней Олимпиаде.\n\n" +
            "Пять предыдущих золотых медалей Клебо выиграл на Играх в Пекине в 2022 году и Пхёнчхане в 2018.\n\n" +
            "Олимпиада в Италии завершится в воскресенье, 22 февраля."

@Composable
fun FirstArticleScreen(
    initialIsRead: Boolean = false,
    initialLikeCount: Int = 0,
    initialDislikeCount: Int = 0,
    onShareArticle: (String) -> Unit = {},
    onCountersChanged: (likes: Int, dislikes: Int, isRead: Boolean) -> Unit = { _, _, _ -> },
    onBack: () -> Unit = {}
) {
    var likeCount by rememberSaveable { mutableIntStateOf(initialLikeCount) }
    var dislikeCount by rememberSaveable { mutableIntStateOf(initialDislikeCount) }
    var isRead by rememberSaveable { mutableStateOf(initialIsRead) }

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.article_title)) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            onShareArticle("$ARTICLE1_TITLE\n\n$ARTICLE1_FULL_TEXT")
                        }) {
                            Icon(
                                Icons.Default.Share,
                                contentDescription = stringResource(R.string.share_article)
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
                    .padding(16.dp)
            ) {
                Text(
                    text = ARTICLE1_TITLE,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 30.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(12.dp))

                AsyncImage(
                    model = ARTICLE1_IMAGE_URL,
                    contentDescription = "Йоханнес Клебо",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Фото: Йоханнес Клебо на ОИ-2026",
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(16.dp))

                val annotatedText = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 17.sp)) {
                        append("Норвежский лыжник Йоханнес Клебо")
                    }
                    append(" после победы в марафоне на Олимпийских играх — 2026 стал 11-кратным олимпийским чемпионом, выиграв все гонки на текущей Олимпиаде.")
                    append("\n\n")
                    append("До этого на Олимпиаде в Италии Клебо выигрывал скиатлон, командный и личный спринты, мужскую эстафету, а также гонку 10 км свободным стилем.")
                    append("\n\n")
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                        append("Также уникальным достижением стал факт завоевания спортсменом шести золотых медалей из шести возможных на Олимпийских играх — 2026.")
                    }
                    append(" Это стало абсолютным рекордом, так как ни одному спортсмену не удавалось завоевать шесть золотых медалей на одной зимней Олимпиаде.")
                    append("\n\n")
                    append("Пять предыдущих золотых медалей Клебо выиграл на Играх в ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Пекине в 2022 году")
                    }
                    append(" и ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Пхёнчхане в 2018")
                    }
                    append(".")
                    append("\n\n")
                    withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                        append("Олимпиада в Италии завершится в воскресенье, 22 февраля.")
                    }
                }

                Text(
                    text = annotatedText,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(onClick = {
                        likeCount++
                        onCountersChanged(likeCount, dislikeCount, isRead)
                    }) {
                        Text("\uD83D\uDC4D $likeCount", fontSize = 16.sp)
                    }
                    OutlinedButton(onClick = {
                        dislikeCount++
                        onCountersChanged(likeCount, dislikeCount, isRead)
                    }) {
                        Text("\uD83D\uDC4E $dislikeCount", fontSize = 16.sp)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Switch(
                        checked = isRead,
                        onCheckedChange = { checked ->
                            isRead = checked
                            onCountersChanged(likeCount, dislikeCount, isRead)
                        }
                    )
                    Text(
                        text = stringResource(R.string.read_label),
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstArticleScreenPreview() {
    FirstArticleScreen()
}
