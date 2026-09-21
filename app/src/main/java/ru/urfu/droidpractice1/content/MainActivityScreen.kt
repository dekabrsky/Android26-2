package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityScreen(
    isSecondArticleRead: Boolean,
    onNavigateToSecond: () -> Unit
) {
    val context = LocalContext.current

    var likes by rememberSaveable { mutableIntStateOf(248) }
    var dislikes by rememberSaveable { mutableIntStateOf(31) }

    val articleText = """
        ROGUELIKE И TOWER DEFENSE: как игры превращают хаос в азарт.

        Прошли времена, когда геймеры считали жанры линейными и предсказуемыми.
        Сегодня в центре внимания — два направления, которые по-разному работают
        с темпом, риском и планированием: roguelike и tower defense.

        Roguelike строится на идее "один забег — одна попытка".
        Это жанр, где каждый выбор имеет значение, а смерть часто означает
        откат к самому началу. Игрок постоянно собирает ресурсы, улучшает
        персонажа и учится на ошибках. Важно не только умение бить врагов,
        но и способность адаптироваться к случайным событиям, которые меняют
        маршрут, награды и опасности.

        В лучших roguelike ощущение прогресса сохраняется даже после поражения.
        С трудом добытые улучшения, новые способности и принципы поведения
        заставляют возвращаться снова и снова. Именно потому такие игры
        создают сильную привязку: игрок не просто проходит уровень, а
        переживает долгий цикл открытия, эксперимента и победы.

        Tower defense — совсем другая форма напряжения. Здесь игрок не бегает
        по уровню, а строит оборону, рассчитывает траектории, расставляет
        башни и предугадывает наступление врагов. Победа зависит не от
        скорости реакции, а от умения планировать заранее. Каждое новое
        волшебное существо, каждая улучшенная башня и каждая закладка
        маршрута меняют стратегию.

        И вот в чем главная магия этих жанров: они оба превращают сложность
        в удовольствие. Один заставляет игрока быстро реагировать и принимать
        рискованные решения, другой — спокойно просчитывать, предвидеть и
        подстраиваться. Их можно назвать разными языками игры, но оба
        говорят о главном: хороший геймдизайн строится на напряжении,
        системах выбора и ощущении, что ты действительно влиял на исход.
    """.trimIndent()

    val articleTitle =
        "Roguelike и tower defense: два способа держать игрока в напряжении"

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Игровые жанры")
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                val sendIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, articleText)
                                    type = "text/plain"
                                }

                                val shareIntent = Intent.createChooser(
                                    sendIntent,
                                    "Поделиться статьёй"
                                )

                                context.startActivity(shareIntent)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Поделиться статьёй"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    text = "Геймдизайн",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                Text(
                    text = articleTitle,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                AsyncImage(
                    model = "https://images.unsplash.com/photo-1542751371-adc38448a05e?auto=format&fit=crop&w=1200&q=80",
                    contentDescription = "Игра на компьютере",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(18.dp)),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = "Рогалик: хаос как часть системы",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "В roguelike игрока постоянно подталкивают к решениям: как улучшить героя, какие предметы подобрать, когда лучше отступить. У каждого забега свои карты, враги и случайные эффекты. Именно эта непредсказуемость создаёт азарт, ведь каждая победа кажется результатом не только мастерства, но и способности адаптироваться.",
                    style = MaterialTheme.typography.bodyLarge,
                    lineHeight = 28.sp
                )

                Text(
                    text = "Tower defense: стратегия вместо спонтанности",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.End
                )

                Text(
                    text = "В tower defense игрок строит систему защиты, а не просто сражается. Он выбирает позиции башен, рассчитывает их силу и учитывает типы врагов. Здесь важна продуманность: чем лучше стратегия, тем меньше вероятность, что волна пройдёт через оборону. Такие игры учат видеть будущие проблемы до того, как они станут катастрофой.",
                    style = MaterialTheme.typography.bodyLarge,
                    lineHeight = 28.sp,
                    textAlign = TextAlign.Justify
                )

                Text(
                    text = "Почему эти жанры цепляют",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )

                Text(
                    text = "Оба жанра работают на ощущение контроля и риска. В roguelike — это шанс после неудачи вернуться и попробовать снова, а в tower defense — предвидение и точный выбор в момент, когда время уже идёт. Именно сочетание азарта, логики и повторяемости делает такие игры такими популярными.",
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 24.sp
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                likes++
                            }
                        ) {
                            Text(
                                text = "👍",
                                fontSize = 24.sp
                            )
                        }

                        Text(
                            text = likes.toString(),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                dislikes++
                            }
                        ) {
                            Text(
                                text = "👎",
                                fontSize = 24.sp
                            )
                        }

                        Text(
                            text = dislikes.toString(),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                Button(
                    onClick = onNavigateToSecond,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (isSecondArticleRead) {
                            "Вторая статья прочитана"
                        } else {
                            "Перейти ко второй статье"
                        }
                    )
                }

                Text(
                    text = "Вывод: жанры разных типов, но общего у них одно — они учат любить процесс, а не только результат.",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen(
        isSecondArticleRead = false,
        onNavigateToSecond = {}
    )
}