@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

private const val ARTICLE_TITLE = "Как настольные игры развивают мышление"
private const val ARTICLE_DATE = "18 сентября 2026"
private const val ARTICLE_IMAGE_DESC = "Настольная игра"

private sealed interface ArticleBlock {
    val text: String

    data class Heading(
        override val text: String,
        val style: HeadingStyle
    ) : ArticleBlock

    data class Paragraph(override val text: String) : ArticleBlock
}

private enum class HeadingStyle { LARGE, SEMIBOLD, ITALIC }

private val articleBlocks = listOf(
    ArticleBlock.Heading("Почему люди любят настольные игры?", HeadingStyle.LARGE),
    ArticleBlock.Paragraph(
        "Настольные игры уже давно перестали быть просто развлечением. " +
                "Они позволяют игрокам соревноваться, сотрудничать и принимать " +
                "решения в самых разных игровых ситуациях."
    ),
    ArticleBlock.Heading("Стратегия и принятие решений", HeadingStyle.SEMIBOLD),
    ArticleBlock.Paragraph(
        "Во многих играх необходимо заранее планировать свои действия, " +
                "анализировать действия соперников и адаптироваться к изменяющейся ситуации. " +
                "Поэтому партия может стать настоящей тренировкой стратегического мышления."
    ),
    ArticleBlock.Heading("Главное — удовольствие от процесса", HeadingStyle.ITALIC),
    ArticleBlock.Paragraph(
        "При этом необязательно быть профессиональным игроком. " +
                "Главная ценность настольных игр заключается в возможности провести время " +
                "с друзьями и получить положительные эмоции."
    ),
)

private fun buildShareText(): String = buildString {
    appendLine(ARTICLE_TITLE)
    appendLine()
    articleBlocks.forEach { block ->
        appendLine(block.text)
        appendLine()
    }
}.trim()

@Composable
fun MainActivityScreen(
    secondArticleRead: Boolean,
    onOpenSecondArticle: () -> Unit
) {
    var likes by rememberSaveable { mutableStateOf(0) }
    var dislikes by rememberSaveable { mutableStateOf(0) }

    val context = LocalContext.current

    fun shareArticle() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, buildShareText())
        }

        context.startActivity(
            Intent.createChooser(intent, "Поделиться статьёй")
        )
    }

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Мир настольных игр") }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = ARTICLE_TITLE,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = ARTICLE_DATE,
                    fontSize = 14.sp
                )

                AsyncImage(
                    model = R.drawable.article_image,
                    contentDescription = ARTICLE_IMAGE_DESC,
                    modifier = Modifier.fillMaxWidth()
                )

                articleBlocks.forEach { block ->
                    when (block) {
                        is ArticleBlock.Heading -> Text(
                            text = block.text,
                            style = block.style.toTextStyle()
                        )
                        is ArticleBlock.Paragraph -> Text(
                            text = block.text,
                            fontSize = 16.sp
                        )
                    }
                }

                Button(
                    onClick = { shareArticle() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Поделиться статьёй")
                }

                Text(
                    text = if (secondArticleRead) {
                        "Вторая статья прочитана"
                    } else {
                        "Вторая статья ещё не прочитана"
                    },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Button(
                    onClick = onOpenSecondArticle,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Перейти к следующей статье")
                }

                Button(onClick = { likes++ }) {
                    Text("Лайк $likes")
                }

                Button(onClick = { dislikes++ }) {
                    Text("Дизлайк $dislikes")
                }
            }
        }
    }
}

private fun HeadingStyle.toTextStyle(): TextStyle = when (this) {
    HeadingStyle.LARGE -> TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold)
    HeadingStyle.SEMIBOLD -> TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
    HeadingStyle.ITALIC -> TextStyle(fontSize = 20.sp, fontStyle = FontStyle.Italic)
}