package ru.urfu.droidpractice1.content
import ru.urfu.droidpractice1.R
import android.content.Intent
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

private const val ARTICLE_TITLE =
    "Путешествие к Марсу"

private const val ARTICLE_TEXT =
    "Марс — одна из самых изучаемых планет Солнечной системы. " +
            "Современные автоматические станции помогают учёным исследовать " +
            "его поверхность, атмосферу и геологическую историю. " +
            "Каждая новая миссия приближает человечество к ответу на вопрос, " +
            "могла ли когда-либо существовать жизнь за пределами Земли."

@Composable
fun MainActivityScreen(
    likes: Int,
    dislikes: Int,
    secondArticleRead: Boolean,
    onLikeClick: () -> Unit,
    onDislikeClick: () -> Unit,
    onOpenSecondArticle: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(
            text = ARTICLE_TITLE,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Как человечество исследует Красную планету",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Космос • 5 минут чтения",
            style = MaterialTheme.typography.labelMedium,
            fontStyle = FontStyle.Italic
        )

        Spacer(modifier = Modifier.height(20.dp))

        AsyncImage(
            model = R.drawable.article_mars,
            contentDescription = "Марс",
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = ARTICLE_TEXT,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Исследования Марса важны не только для будущих " +
                    "пилотируемых экспедиций. Они помогают лучше понять, " +
                    "как формировались планеты земной группы и почему Земля " +
                    "оказалась пригодной для жизни.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Оцените статью",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = onLikeClick
            ) {
                Text(text = "👍 $likes")
            }

            OutlinedButton(
                onClick = onDislikeClick
            ) {
                Text(text = "👎 $dislikes")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = {
                val sendIntent = Intent(
                    Intent.ACTION_SEND
                ).apply {
                    type = "text/plain"

                    putExtra(
                        Intent.EXTRA_SUBJECT,
                        ARTICLE_TITLE
                    )

                    putExtra(
                        Intent.EXTRA_TEXT,
                        "$ARTICLE_TITLE\n\n$ARTICLE_TEXT"
                    )
                }

                context.startActivity(
                    Intent.createChooser(
                        sendIntent,
                        "Поделиться статьёй"
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Поделиться")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Text(
                    text = "Следующая статья",
                    style = MaterialTheme.typography.labelLarge
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Возвращение человека на Луну",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (secondArticleRead) {
                    Text(
                        text = "✓ Прочитано",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                } else {
                    Text(
                        text = "Ещё не прочитано",
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onOpenSecondArticle,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = "Читать →")
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}