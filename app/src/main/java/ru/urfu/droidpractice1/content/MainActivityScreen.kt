@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import androidx.compose.material3.ButtonDefaults
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    isSecondArticleRead: Boolean = false,
    onOpenSecondArticle: () -> Unit = {}
) {

    val context = LocalContext.current

    var likes by rememberSaveable {
        mutableStateOf(0)
    }

    var dislikes by rememberSaveable {
        mutableStateOf(0)
    }

    val shareText =
        "Лучшие игроки 5-го тура АПЛ в фэнтези\n\n" +
                "Антуан Семеньо — 17 очков\n" +
                "Брайан Бробби — 15 очков\n" +
                "Паскаль Гросс — 12 очков\n" +
                "Джеймс Тарковски — 10 очков\n" +
                "Льюис Холл — 10 очков"

    DroidPractice1Theme {

        Scaffold(
            modifier = Modifier.fillMaxSize(),

            topBar = {

                TopAppBar(

                    title = {
                        Text(
                            text = stringResource(
                                id = R.string.article_title
                            )
                        )
                    },

                    actions = {

                        IconButton(
                            onClick = {

                                val sendIntent = Intent().apply {
                                    action = Intent.ACTION_SEND

                                    putExtra(
                                        Intent.EXTRA_TEXT,
                                        shareText
                                    )

                                    type = "text/plain"
                                }

                                val shareIntent =
                                    Intent.createChooser(
                                        sendIntent,
                                        "Поделиться статьёй"
                                    )

                                context.startActivity(
                                    shareIntent
                                )
                            }
                        ) {

                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Поделиться"
                            )
                        }
                    }
                )
            }

        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {

                Text(
                    text = "Лучшие игроки 5-го тура АПЛ в фэнтези",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = "Разбираем самых эффективных игроков 5-го тура АПЛ. " +
                            "В подборку вошли футболисты, которые принесли много очков " +
                            "как своим клубам, так и участникам фэнтези.",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Image(
                    painter = painterResource(
                        id = R.drawable.team
                    ),
                    contentDescription = "Состав лучших игроков тура",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Text(
                    text = "Антуан Семеньо — 17 очков",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Семеньо стал одним из главных героев тура. " +
                            "Он забил два мяча и сделал результативную передачу. " +
                            "После пяти туров он остаётся одним из самых активных " +
                            "игроков своей команды в атаке.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Image(
                    painter = painterResource(
                        id = R.drawable.semenyo
                    ),
                    contentDescription = "Антуан Семеньо",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Text(
                    text = "Брайан Бробби — 15 очков",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Бробби не забивал в первых турах, но в матче " +
                            "против Манчестер Сити сразу оформил хет-трик. " +
                            "Он стал одним из лидеров тура по ожидаемым голам и ударам.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Text(
                    text = "Паскаль Гросс — 12 очков",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Гросс отлично начал сезон и продолжил набирать " +
                            "результативные действия. В игре с Арсеналом он отметился " +
                            "голом и передачей, а также остаётся одним из лучших игроков " +
                            "лиги по созданным моментам.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Image(
                    painter = painterResource(
                        id = R.drawable.gross
                    ),
                    contentDescription = "Паскаль Гросс",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Text(
                    text = "Джеймс Тарковски — 10 очков",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Тарковски помог Эвертону сохранить ворота в неприкосновенности " +
                            "и сделал результативную передачу. Защитник стабильно приносит " +
                            "очки и остаётся важным игроком команды.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Text(
                    text = "Льюис Холл — 10 очков",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Холл забил свой первый мяч в сезоне. Помимо игры в обороне, " +
                            "он активно подключается к атакам и входит в число лучших " +
                            "защитников по показателям ожидаемых результативных передач.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(32.dp)
                )

                Text(
                    text = "Оцените статью",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        12.dp
                    )
                ) {

                    Button(
                        onClick = {
                            likes++
                        }
                    ) {
                        Text(
                            text = "👍 $likes"
                        )
                    }

                    Button(
                        onClick = {
                            dislikes++
                        }
                    ) {
                        Text(
                            text = "👎 $dislikes"
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Text(
                    text = "Самые эффективные игроки АПЛ – от кого будет много пользы дальше?",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = if (isSecondArticleRead) {
                        "Прочитано ✓"
                    } else {
                        "Не прочитано"
                    },
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Button(
                    onClick = onOpenSecondArticle,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSecondArticleRead) {
                            MaterialTheme.colorScheme.surfaceVariant
                        } else {
                            MaterialTheme.colorScheme.primary
                        },
                        contentColor = if (isSecondArticleRead) {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        } else {
                            MaterialTheme.colorScheme.onPrimary
                        }
                    )
                ) {
                    Text(
                        text = if (isSecondArticleRead) {
                            "Открыть вторую статью снова"
                        } else {
                            "Перейти ко второй статье"
                        }
                    )
                }

                Spacer(
                    modifier = Modifier.height(32.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}