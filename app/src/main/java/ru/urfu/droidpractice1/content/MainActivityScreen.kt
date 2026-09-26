@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    isSecondArticleRead: Boolean = false,
    onNavigateToSecondArticle: () -> Unit = {}
) {
    var likes by rememberSaveable { mutableIntStateOf(0) }
    var dislikes by rememberSaveable { mutableIntStateOf(0) }

    val context = LocalContext.current
    val shareText = "Рекомендую к прочтению: ${stringResource(id = R.string.article_title)}\n\n" +
            "Фигурное катание – конькобежный олимпийский вид спорта..."

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = R.drawable.figure_skatings,
                    contentDescription = "Фигурист на льду",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(bottom = 16.dp)
                )

                Text(
                    text = "Фигурное катание – конькобежный олимпийский вид спорта, основная идея которого заключается в передвижении спортсмена или пары спортсменов по льду на коньках и выполнении специальных элементов под музыку. Фигурным катанием занимаются как мужчины, так и женщины.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify,
                    lineHeight = 24.sp,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Заголовок раздела: История
                Text(
                    text = "История возникновения и развития фигурного катания",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Text(
                    text = "Прежде чем говорить о возникновении фигурного катания, следует упомянуть о появлении первых коньков. Находки археологов говорят о том, что прототипы коньков были уже в бронзовом веке (35/33 — 13/11 вв. до н. э.), но такие коньки не годились для фигурного катания, их предназначение было лишь для ускорения их владельца.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify,
                    lineHeight = 24.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Text(
                    text = "Фигурное катание зародилось в Голландии, в XII—XIV веке. Заниматься им стало возможно после создания железных коньков с двумя ребрами. Но это было не то фигурное катание, к которому мы с вами привыкли. Спортсмены вычерчивали на льду различные фигуры, сохраняя при этом красивую позу.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify,
                    lineHeight = 24.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { onNavigateToSecondArticle() }
                ) {
                    Text(
                        text = "В 1742 году в Эдинбурге появился первый клуб любителей фигурного катания, тогда же был придуман первый перечень обязательных фигур и первые правила.",
                        style = MaterialTheme.typography.bodyMedium,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                Text(
                    text = "Из Европы фигурное катание быстро распространилось в США, где получило огромное развитие. Стали открываться новые клубы фигуристов, совершенствоваться правила, разрабатываться новые модели коньков. К середине XIX века были сформированы практически все из существующих обязательных фигур, а также технические приемы для их выполнения.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify,
                    lineHeight = 24.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Text(
                    text = "На первом Конгрессе конькобежцев в 1871 году фигурное катание было признано как вид спорта, а ещё спустя 11 лет был проведен первый официальный чемпионат по фигурному катанию. В 1890 году в честь 25-летия катка в Юсуповском саду в Санкт-Петербург съехались все звезды фигурного катания, а получившиеся соревнования носили статус «неофициального чемпионата мира». Продемонстрированный международный размах позволил уже в 1896 году провести в Санкт-Петербурге первый официальный чемпионат мира по фигурному катанию.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify,
                    lineHeight = 24.sp,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { likes++ }) {
                            Icon(
                                imageVector = Icons.Default.ThumbUp,
                                contentDescription = "Лайк",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Text(
                            text = "$likes",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { dislikes++ }) {
                            Icon(
                                imageVector = Icons.Default.ThumbDown,
                                contentDescription = "Дизлайк",
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Text(
                            text = "$dislikes",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, shareText)
                        }
                        context.startActivity(Intent.createChooser(intent, "Поделиться статьей через"))
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Поделиться статьей")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    DroidPractice1Theme {
        MainActivityScreen()
    }
}