@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
//import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen( isSecondArticleRead: Boolean, startSecondArticle: (Intent) -> Unit) {

    var likes by rememberSaveable { mutableIntStateOf(0) }
    var dislikes by rememberSaveable { mutableIntStateOf(0) }
    val context = LocalContext.current

    val articleText = """
        Первый этап нового сезона Формулы-1 — в традиционно субъективных оценках автора.

        Шарль Леклер — 9
        Если бы не Леклер, первая гонка сезона показалась бы скучноватой. Благодаря молниеносному старту 
        «Феррари» Шарль вырвался в лидеры и 11 кругов возил за собой пилота на самой быстрой машине 
        чемпионата. Затем, увы, шанса подняться выше третьего места не было — слишком уж поосторожничали стратеги.
        
        Исак Хаджар — 9
        В свой первый уикенд в составе «Ред Булл» Хаджар произвёл очень хорошее впечатление, пусть 
        даже сравнить его с Ферстаппеном так и не удалось. В квалификации Исак был лучшим позади 
        «Мерседесов», на старте устоял четвёртым, пропустив лишь «Феррари» с её чудо-стартами, а на 
        момент схода держался пятым — лучшим из остальных.
        
        Джордж Расселл — 8,5
        Джордж начал сезон с убедительной победы и был быстрейшим в квалификации, однако превзойти 
        растерявшего уверенность Антонелли — не самое впечатляющее достижение. Да и в начале гонки 
        Расселл позади Леклера выглядел беспомощным, хотя в распоряжении британца, как показали 
        дальнейшие события, был практически самовоз.
    """.trimIndent()

    val articleText2 = """
        Какой получилась премьера новых болидов:
        «Это не гонки». Почему в Ф-1 массово недовольны новыми болидами и считают их опасными
    """.trimIndent()

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title)
                        )
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
            ) {
                // Заголовок статьи
                Text(
                    text = "Ошибка Пиастри непростительна, подиум Антонелли — заслуга команды. Оценки за ГП Австралии",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Изображение
                AsyncImage(
                    model = "https://img.championat.com/s/732x488/news/big/g/h/ocenki-za-gran-pri-avstralii-f-1-2026_17730014191199775953.jpg",
                    contentDescription = "Фото статьи",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                // Добавьте сюда текст статьи, если нужно
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = articleText,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { likes++ }) {
                            Icon(Icons.Default.ThumbUp, contentDescription = "Лайк")
                        }
                        Text(
                            text = likes.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { dislikes++ }) {
                            Icon(
                                imageVector = Icons.Filled.ThumbUp,
                                modifier = Modifier.rotate(180f),
                                contentDescription = "Дизлайк",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Text(
                            text = dislikes.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(top = 25.dp, bottom = 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = Color(0x669C27B0))
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    ClickableText(
                        text = AnnotatedString(articleText2),
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = if (isSecondArticleRead) Color.Gray else Color.Black
                        ),
                        onClick = { _ ->
                            val intent = Intent(context, SecondActivity::class.java).apply {
                                putExtra("isRead", isSecondArticleRead)
                            }
                            startSecondArticle(intent)
                        }
                    )
                }

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen(false) {}
}