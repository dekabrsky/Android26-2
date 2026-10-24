package ru.urfu.droidpractice1.content

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

private data class NewsArticle(
    val id: String,
    val title: String,
    val date: String,
    val image: Int,
    val preview: String,
    val paragraphs: List<String>,
    val quote: String? = null,
)

private val millionaireArticle = NewsArticle(
    id = "millionaire",
    title = "В УрФУ учится миллионер?",
    date = "17.12.25",
    image = R.drawable.car_photo,
    preview = "Никто не верит, но это факт, в наш университет приехал настоящий Райан Гослинг, только русский.",
    paragraphs = listOf(
        "Никто не верит, но это факт, в наш университет приехал настоящий Райан Гослинг, только русский.",
        "На данный момент точные имя и фамилия этого студента не раскрыты, дабы соблюдать конфиденциальность, но однозначно уже можно сказать, что готовиться отбиваться от толп фанаток нужно уже сейчас.",
        "Время покажет, как будет развиваться его учебная карьера в УрФУ, но уже сейчас можно сказать, что его присутствие в университетской жизни будет значимым, и внимание к нему со стороны студентов и преподавателей только возрастет.",
        "Молодой миллионер уже стал героем множества разговоров, и, судя по всему, это только начало его пути в академическом мире.",
        "Представители университета подчеркивают, что, несмотря на успехи этого студента в бизнесе, его способности и стремление к знаниям вполне соответствуют академическим требованиям учебного заведения.",
        "С ним уже начали сотрудничать некоторые факультеты для реализации новых проектов и инициатив, направленных на развитие предпринимательских навыков у студентов.",
        "Таким образом, УрФУ вновь подтверждает свою репутацию места, где студентам предоставляется возможность не только учиться, но и реализовывать свои самые амбициозные идеи.",
        "Станет ли этот молодой человек новым символом успеха и амбиции для студентов университета — вопрос времени. Однако уже сегодня можно уверенно сказать, что его присутствие в УрФУ привнесет в учебный процесс новые перспективы и идеи.",
    ),
    quote = "❝ Я пришел сюда не только для того, чтобы учиться, но и для того, чтобы вдохновлять других, — заявил он в одном из своих интервью. По его словам, в его жизни важны не только деньги, но и возможности, которые можно получить благодаря обучению и человеческим связям. ❞",
)

private val stepsArticle = NewsArticle(
    id = "steps",
    title = "Маленькие шаги в будущее",
    date = "21.12.25",
    image = R.drawable.shrek_photo,
    preview = "Наши дети успели за короткое время установить принадлежность к всем...",
    paragraphs = listOf(
        "Большие перемены начинаются с маленьких шагов. В университете запускается серия встреч, где студенты смогут обсудить идеи для учебы, проектов и первых стартапов.",
        "Каждая встреча — это возможность познакомиться с единомышленниками, задать вопросы наставникам и попробовать превратить задумку в понятный план.",
        "Организаторы уверены: спокойный темп и поддержка команды помогают не бояться ошибок и двигаться вперед.",
    ),
    quote = "❝ Иногда самый важный результат — просто сделать первый шаг. ❞",
)

private val businessArticle = NewsArticle(
    id = "business",
    title = "Как правильно построить бизнес и не прогореть?",
    date = "13.12.25",
    image = R.drawable.business_photo,
    preview = "Практические советы студентам, которые хотят проверить бизнес-идею.",
    paragraphs = listOf(
        "Перед запуском проекта важно проверить, какую задачу он решает и кому действительно нужен.",
        "Начните с небольшой версии продукта, поговорите с будущими пользователями и только потом планируйте большие расходы.",
        "УрФУ помогает студентам находить наставников и собирать команду для первых предпринимательских экспериментов.",
    ),
)

private val articles = listOf(millionaireArticle, stepsArticle, businessArticle)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityScreen() {
    val context = LocalContext.current
    var selectedArticleId by rememberSaveable { mutableStateOf<String?>(null) }
    var likes by rememberSaveable { mutableIntStateOf(0) }
    var dislikes by rememberSaveable { mutableIntStateOf(0) }
    var secondArticleRead by rememberSaveable { mutableStateOf(false) }
    val secondArticleLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            secondArticleRead = result.data?.getBooleanExtra(SecondActivity.READ_EXTRA, secondArticleRead)
                ?: secondArticleRead
        }
    }

    DroidPractice1Theme {
        val selectedArticle = articles.firstOrNull { it.id == selectedArticleId }
        if (selectedArticle == null) {
            NewsHome(
                onArticleClick = { selectedArticleId = it.id },
                secondArticleRead = secondArticleRead,
            )
        } else {
            ArticlePage(
                article = selectedArticle,
                likes = likes,
                dislikes = dislikes,
                secondArticleRead = secondArticleRead,
                onBack = { selectedArticleId = null },
                onLike = { likes++ },
                onDislike = { dislikes++ },
                onShare = { shareArticle(context, selectedArticle) },
                onOpenSecondArticle = {
                    secondArticleLauncher.launch(
                        Intent(context, SecondActivity::class.java)
                            .putExtra(SecondActivity.READ_EXTRA, secondArticleRead),
                    )
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewsHome(
    onArticleClick: (NewsArticle) -> Unit,
    secondArticleRead: Boolean,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(stringResource(R.string.article_title), fontWeight = FontWeight.SemiBold)
                        Text(
                            stringResource(R.string.article_subtitle),
                            color = colorResource(R.color.news_pink),
                            fontSize = 11.sp,
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(R.color.news_toolbar),
                ),
            )
        },
        bottomBar = { NewsNavigationBar() },
        containerColor = colorResource(R.color.news_background),
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(start = 16.dp, top = 18.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            item { Text("Свежие новости", fontSize = 24.sp, fontWeight = FontWeight.Bold) }
            items(articles, key = { it.id }) { article ->
                NewsCard(
                    article = article,
                    isRead = article.id == "steps" && secondArticleRead,
                    onClick = { onArticleClick(article) },
                )
            }
        }
    }
}

@Composable
private fun NewsCard(article: NewsArticle, isRead: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column {
            Box {
                Image(
                    painter = painterResource(article.image),
                    contentDescription = article.title,
                    modifier = Modifier.fillMaxWidth().height(160.dp).clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)),
                    contentScale = ContentScale.Crop,
                )
                Text(
                    article.title,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.align(Alignment.BottomStart).fillMaxWidth()
                        .background(Color.Black.copy(alpha = 0.52f)).padding(horizontal = 14.dp, vertical = 9.dp),
                )
            }
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    article.preview,
                    color = colorResource(R.color.news_preview),
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                if (isRead) {
                    Text("Прочитано", color = colorResource(R.color.news_pink), fontSize = 12.sp, modifier = Modifier.padding(top = 6.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArticlePage(
    article: NewsArticle,
    likes: Int,
    dislikes: Int,
    secondArticleRead: Boolean,
    onBack: () -> Unit,
    onLike: () -> Unit,
    onDislike: () -> Unit,
    onShare: () -> Unit,
    onOpenSecondArticle: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    TextButton(onClick = onBack) { Text("‹", fontSize = 32.sp, color = colorResource(R.color.news_pink)) }
                },
                title = { Text(article.title, maxLines = 2, fontSize = 18.sp, lineHeight = 21.sp) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = colorResource(R.color.news_toolbar)),
            )
        },
        bottomBar = { NewsNavigationBar() },
        containerColor = colorResource(R.color.news_background),
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).verticalScroll(rememberScrollState()).padding(horizontal = 16.dp, vertical = 14.dp),
        ) {
            Image(
                painter = painterResource(article.image),
                contentDescription = article.title,
                modifier = Modifier.fillMaxWidth().height(220.dp).clip(RoundedCornerShape(18.dp)),
                contentScale = ContentScale.Crop,
            )
            Text(article.date, color = Color.Gray, fontSize = 12.sp, modifier = Modifier.padding(top = 10.dp))
            Text(article.title, fontSize = 25.sp, fontWeight = FontWeight.Bold, lineHeight = 29.sp, modifier = Modifier.padding(top = 4.dp))
            Spacer(Modifier.height(12.dp))
            article.paragraphs.forEachIndexed { index, paragraph ->
                Text(
                    text = paragraph,
                    fontSize = if (index == 0) 17.sp else 15.sp,
                    fontWeight = if (index == 0) FontWeight.SemiBold else FontWeight.Normal,
                    lineHeight = 21.sp,
                    modifier = Modifier.padding(bottom = 11.dp),
                )
                if (index == 1 && article.quote != null) {
                    Text(
                        article.quote,
                        color = colorResource(R.color.news_quote_text),
                        fontStyle = FontStyle.Italic,
                        lineHeight = 21.sp,
                        modifier = Modifier.padding(start = 10.dp, bottom = 12.dp).clip(RoundedCornerShape(12.dp))
                            .background(colorResource(R.color.news_quote_background)).padding(12.dp),
                    )
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = colorResource(R.color.news_divider))
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                OutlinedButton(onClick = onLike, modifier = Modifier.weight(1f)) { Text("♡  $likes") }
                OutlinedButton(onClick = onDislike, modifier = Modifier.weight(1f)) { Text("☹  $dislikes") }
            }
            Button(
                onClick = onShare,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.news_pink)),
            ) { Text(stringResource(R.string.share_article)) }
            if (article.id == "millionaire") {
                Button(
                    onClick = onOpenSecondArticle,
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.news_button_dark)),
                ) { Text(stringResource(R.string.open_second_article)) }
                if (secondArticleRead) {
                    Text(
                        "Вторая статья прочитана",
                        color = colorResource(R.color.news_pink),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 8.dp, bottom = 8.dp),
                    )
                }
            }
            Spacer(Modifier.navigationBarsPadding().height(10.dp))
        }
    }
}

@Composable
private fun NewsNavigationBar() {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(selected = false, onClick = {}, icon = { Text("⌂", fontSize = 22.sp) }, label = { Text("Главная") })
        NavigationBarItem(selected = false, onClick = {}, icon = { Text("◌", fontSize = 22.sp) }, label = { Text("Обсуждения") })
        NavigationBarItem(selected = true, onClick = {}, icon = { Text("▣", fontSize = 22.sp) }, label = { Text("Новости") })
    }
}

private fun shareArticle(context: Context, article: NewsArticle) {
    val text = buildString {
        appendLine(article.title)
        appendLine(article.date)
        appendLine()
        article.paragraphs.forEach { appendLine(it); appendLine() }
    }
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(Intent.createChooser(shareIntent, "Поделиться статьёй"))
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainActivityScreen()
}
