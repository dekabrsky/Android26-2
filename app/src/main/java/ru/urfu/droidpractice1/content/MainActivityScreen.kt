@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    secondArticleRead: Boolean = false,
    onSecondArticleClick: () -> Unit = {}
) {

    val context = LocalContext.current

    var positiveVotes by rememberSaveable {
        mutableIntStateOf(0)
    }

    var negativeVotes by rememberSaveable {
        mutableIntStateOf(0)
    }

    val shareText =
        "Леброн Джеймс заключил рекламный контракт на 15 млн долларов в год.\n\n" +
                "Соглашение связано с продвижением американского футбола."

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

                                val sendIntent =
                                    Intent(Intent.ACTION_SEND).apply {

                                        type = "text/plain"

                                        putExtra(
                                            Intent.EXTRA_TEXT,
                                            shareText
                                        )
                                    }

                                context.startActivity(
                                    Intent.createChooser(
                                        sendIntent,
                                        "Поделиться статьёй"
                                    )
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
                    .padding(horizontal = 18.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Леброн выбрал другой спорт ради \$15 млн",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Новый рекламный контракт Леброна Джеймса связан не с баскетболом, " +
                            "а с продвижением американского футбола.",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                Image(
                    painter = painterResource(
                        id = R.drawable.lebron_main
                    ),
                    contentDescription = "Леброн Джеймс",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .clip(
                            RoundedCornerShape(14.dp)
                        ),
                    contentScale = ContentScale.Crop
                )

                Spacer(
                    modifier = Modifier.height(22.dp)
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor =
                            MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "Контракт на \$15 млн",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = "По информации американских СМИ, соглашение с Polymarket " +
                                    "может приносить Джеймсу около 15 миллионов долларов в год. " +
                                    "Он участвует в рекламных кампаниях и публикациях в социальных сетях."
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Text(
                    text = "Почему не баскетбол?",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Правила НБА ограничивают продвижение продуктов, связанных " +
                            "со ставками на баскетбольные матчи. Поэтому рекламная активность " +
                            "Леброна сосредоточена на американском футболе."
                )

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                Image(
                    painter = painterResource(
                        id = R.drawable.lebron_nfl
                    ),
                    contentDescription = "Леброн и американский футбол",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(215.dp)
                        .clip(
                            RoundedCornerShape(14.dp)
                        ),
                    contentScale = ContentScale.Crop
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Text(
                    text = "Доходы за пределами площадки",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Для крупнейших звёзд НБА рекламные соглашения давно стали " +
                            "отдельным источником дохода. В случае Джеймса новый контракт " +
                            "привлёк внимание из-за крупной суммы и необычной тематики."
                )

                Spacer(
                    modifier = Modifier.height(30.dp)
                )

                Text(
                    text = "Оцените материал",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Row(
                    horizontalArrangement =
                        Arrangement.spacedBy(14.dp)
                ) {

                    Button(
                        onClick = {
                            positiveVotes++
                        }
                    ) {
                        Text(
                            text = "👍 $positiveVotes"
                        )
                    }

                    Button(
                        onClick = {
                            negativeVotes++
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor =
                                MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text(
                            text = "👎 $negativeVotes"
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(32.dp)
                )

                Text(
                    text = "А что дальше?",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSecondArticleClick()
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor =
                            if (secondArticleRead) {
                                MaterialTheme.colorScheme.surface
                            } else {
                                MaterialTheme.colorScheme.surfaceVariant
                            }
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment =
                            Alignment.CenterVertically,
                        horizontalArrangement =
                            Arrangement.spacedBy(14.dp)
                    ) {

                        Image(
                            painter = painterResource(
                                id = R.drawable.thompson_brothers
                            ),
                            contentDescription =
                                "Братья Томпсон",
                            modifier = Modifier
                                .width(125.dp)
                                .height(95.dp)
                                .clip(
                                    RoundedCornerShape(10.dp)
                                ),
                            contentScale =
                                ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = "Осар Томпсон получил контракт на \$155 млн",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier.height(6.dp)
                            )

                            Text(
                                text = "Братья Томпсоны стали самыми дорогими близнецами НБА",
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Text(
                                text = if (secondArticleRead) {
                                    "Прочитано ✓"
                                } else {
                                    "Читать статью →"
                                },
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                Spacer(
                    modifier = Modifier.height(32.dp)
                )
            }
        }
    }
}