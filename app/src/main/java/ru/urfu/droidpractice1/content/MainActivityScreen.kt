@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import ru.urfu.droidpractice1.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import ru.urfu.droidpractice1.SecondActivity

@Composable
fun MainActivityScreen(article2Read: Boolean) {
    DroidPractice1Theme {
        val context = LocalContext.current
        var likes by rememberSaveable {
            mutableIntStateOf(0)
        }
        var dislikes by rememberSaveable {
            mutableIntStateOf(0)
        }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Футбольные статьи"
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
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = "Как устроена современная футбольная команда",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 34.sp
                )

                Text(
                    text = "Тактика, роли игроков и командная работа",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "👍",
                            fontSize = 28.sp,
                            modifier = Modifier.clickable {
                                likes++
                            }
                        )

                        Text(
                            text = likes.toString(),
                            fontSize = 18.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "👎",
                            fontSize = 28.sp,
                            modifier = Modifier.clickable {
                                dislikes++
                            }
                        )

                        Text(
                            text = dislikes.toString(),
                            fontSize = 18.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
                HorizontalDivider()

                AsyncImage(
                    model = R.drawable.football_article,
                    contentDescription = "Футбольное поле",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(16.dp))
                )

                Text(
                    text = "Футбол — это не только индивидуальное мастерство игроков. " +
                            "Результат команды зависит от того, насколько хорошо каждый футболист " +
                            "понимает свою роль и взаимодействует с партнёрами.",
                    fontSize = 17.sp,
                    lineHeight = 25.sp
                )

                Text(
                    text = "Главные позиции",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Вратарь отвечает за защиту ворот. Защитники помогают остановить " +
                            "атаки соперника и начинают многие командные атаки.",
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )

                Text(
                    text = "Полузащитники связывают оборону и атаку. Именно через них часто " +
                            "проходит большая часть передач команды.",
                    fontSize = 16.sp,
                    lineHeight = 24.sp


                )

                Text(
                    text = "Нападающие находятся ближе всего к воротам соперника и стараются " +
                            "завершать атаки ударами.",
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )

                Text(
                    text = "Командная работа",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Даже сильный игрок не сможет постоянно добиваться результата один. " +
                            "Команда должна синхронно двигаться, создавать свободное пространство " +
                            "и быстро реагировать на действия соперника.",
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )

                Text(
                    text = "Именно поэтому современный футбол требует не только физической " +
                            "подготовки, но и хорошего понимания тактики.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Justify,
                    lineHeight = 24.sp
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(if (article2Read) 0.5f else 1f)
                        .clickable {
                            context.startActivity(
                                Intent(context, SecondActivity::class.java)
                            )
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = androidx.compose.ui.graphics.Color(0xFFE8E0EC)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "Новая статья",
                            fontSize = 14.sp,
                            color = androidx.compose.ui.graphics.Color(0xFF777077)
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "История футбола",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "От первых матчей до современного спорта. Узнайте, " +
                                    "как развивался футбол и менялась тактика команд.",
                            fontSize = 16.sp,
                            lineHeight = 23.sp
                        )

                        if (article2Read) {
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Прочитано ✓",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Button(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(
                                Intent.EXTRA_TEXT,
                                """
                Как устроена современная футбольная команда

                Футбол — это не только индивидуальное мастерство игроков. 
                Результат команды зависит от того, насколько хорошо каждый футболист 
                понимает свою роль и взаимодействует с партнёрами.

                Вратарь отвечает за защиту ворот, защитники помогают остановить атаки,
                полузащитники связывают оборону и атаку, а нападающие стараются завершать атаки.
                """.trimIndent()
                            )
                        }

                        context.startActivity(
                            Intent.createChooser(shareIntent, "Поделиться статьей")
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Поделиться статьёй")
                }
            }
        }
    }
}