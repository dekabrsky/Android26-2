@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Context
import android.content.Intent
import android.widget.ImageButton
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.SecondActivity
import android.content.SharedPreferences
import androidx.compose.runtime.DisposableEffect


@Composable
fun MainActivityScreen() {
    val scrollState = rememberScrollState();
    val context = LocalContext.current;
    val likeCount = rememberSaveable { mutableIntStateOf(0) };
    val dislikeCount = rememberSaveable { mutableIntStateOf(0) };
    val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    var articleRead by remember {
        mutableStateOf(prefs.getBoolean("article_read", false))
    }

    DisposableEffect(prefs) {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPrefs, key ->
            if (key == "article_read") {
                articleRead = sharedPrefs.getBoolean(key, false)
            }
        }
        prefs.registerOnSharedPreferenceChangeListener(listener)
        onDispose { prefs.unregisterOnSharedPreferenceChangeListener(listener) }
    }

    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(id = R.string.article_title)
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            IconButton(onClick = {
                                val intent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, "Роль яблок в здоровом питании. Чем они полезны?")
                                    type = "text/plain"
                                }
                                val chooser = Intent.createChooser(intent, null);
                                context.startActivity(chooser);
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.outline_share_24),
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_back),
                                contentDescription = null
                            )
                        }
                    }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(scrollState)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Роль яблок в здоровом питании. Чем они полезны?", style = MaterialTheme.typography.headlineLarge)
                }

                Row(horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = {
                            likeCount.value++;
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.outline_thumb_up_24),
                            contentDescription = null
                        )
                    }
                    Text(text = likeCount.value.toString(), style = MaterialTheme.typography.bodyMedium)
                    IconButton(
                        onClick = {
                            dislikeCount.value++;
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.outline_thumb_down_24),
                            contentDescription = null
                        )
                    }
                    Text(text = dislikeCount.value.toString(), style = MaterialTheme.typography.bodyMedium)
                }


                Text(
                    text = "Польза для сердца",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 16.dp)
                )
                AsyncImage(
                    model = stringResource(id = R.string.apple_image_url),
                    contentDescription = "Яблоко",
                    modifier = Modifier.padding(innerPadding)
                )

                Text(
                    text = "Яблоки содержат большое количество растворимой клетчатки. Она помогает снизить уровень «плохого» холестерина в крови и препятствует закупорке сосудов. Также в этом фрукте много полифенолов, в том числе флавоноида эпикатехина, снижающих уровень кровяного давления. Помимо этого, плоды также содержат витамин С и калий. Витамин С помогает укрепить иммунную систему, тем самым защищая организм от инфекций, которые могут дать осложнения на сердце. Калий же, в свою очередь, помогает расслабить кровеносные сосуды, снижая тем самым риск высокого кровяного давления и различных сердечно-сосудистых осложнений. Польза для иммунитета. Яблоки – богатый источник витамина C, который, как известно, является антиоксидантом и помогает укрепить иммунную систему, что особенно важно в период сезонных простудных заболеваний",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Обилие антиоксидантов",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Text(
                    text = "Яблоки богаты флавоноидами. Это мощные антиоксиданты, которые обладают противовоспалительными свойствами. Также яблоки содержат катехин, хлоридзин и хлорогеновую кислоту. Все эти вещества помогают защищать организм от пагубного воздействия свободных радикалов, которые могут спровоцировать окислительный стресс и повреждение или мутацию здоровых клеток.",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Польза для мозга",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Text(
                    text = "Яблоки содержат вещество под названием кверцетин, обладающее свойством защищать мозг от повреждений, вызванных окислительным стрессом. Это вещество также предупреждает повреждение нервов благодаря регуляции маркеров окислительного и воспалительного стрессов. Научные исследования показали, что кверцетин помогает нейронам выживать, продолжать функционировать, а также замедляет их потерю с возрастом. Благодаря данному свойству снижается риск развития дегенеративных заболеваний мозга.",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Польза для пищеварения",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Text(
                    text = "Яблоки богаты пищевыми волокнами, которые помогают регулировать пищеварение и поддерживать здоровье кишечника. Регулярное употребление продуктов, богатых клетчаткой, снижает риск возникновения вздутия и запоров.",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Польза для кожи",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Text(
                    text = "Яблочная кислота активно используется в косметологии для продления красоты и молодости кожи. Пилинги на основе яблочной кислоты помогают избавиться от пигментации, сократить глубину морщин, а также выровнять кожу. Помимо этого, в яблоках содержится витамин А, который также оказывает благоприятное воздействие на кожные покровы.",
                    style = MaterialTheme.typography.bodyLarge
                )

                Button(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        val intent = Intent(context, SecondActivity::class.java);
                        context.startActivity(intent);
                    },
                    content = {
                        Box(contentAlignment = Alignment.BottomEnd) {
                            Text(
                                text = "Преступно нажитые миллионы: как разбогател Джеффри Эпштейн?",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(top = 14.dp, bottom = 20.dp)
                            )

                            if (articleRead) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_check_24),
                                    contentDescription = null
                                )
                            }
                        }
                    }
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