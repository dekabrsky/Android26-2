@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.AccentCyan
import ru.urfu.droidpractice1.ui.theme.DarkCardBackground
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.GalacticBlue
import ru.urfu.droidpractice1.ui.theme.MarsRed
import ru.urfu.droidpractice1.ui.theme.NebulaPurple
import ru.urfu.droidpractice1.ui.theme.SecondaryCardBackground
import ru.urfu.droidpractice1.ui.theme.SpaceBlack
import ru.urfu.droidpractice1.ui.theme.SuccessGreenBackground
import ru.urfu.droidpractice1.ui.theme.SuccessGreenIcon
import ru.urfu.droidpractice1.ui.theme.SuccessGreenText

@Composable
fun MainActivityScreen(
    isSecondArticleRead: Boolean,
    onShareClick: (String) -> Unit,
    onNextArticleClick: () -> Unit
) {
    var likes by rememberSaveable { mutableIntStateOf(0) }
    var dislikes by rememberSaveable { mutableIntStateOf(0) }
    var userSelection by rememberSaveable { mutableIntStateOf(0) }

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 4.sp
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = SpaceBlack,
                        titleContentColor = AccentCyan
                    )
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(SpaceBlack, GalacticBlue)
                        )
                    )
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = DarkCardBackground.copy(alpha = 0.8f))
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = stringResource(id = R.string.article_1_title),
                            style = MaterialTheme.typography.headlineMedium,
                            color = AccentCyan,
                            fontWeight = FontWeight.ExtraBold,
                            lineHeight = 34.sp
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        SubcomposeAsyncImage(
                            model = "https://avatars.mds.yandex.net/i?id=46e690e946ee82d237cffe60221e686d_l-7904411-images-thumbs&n=13",
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.White.copy(alpha = 0.1f)),
                            contentScale = ContentScale.Crop,
                            loading = {
                                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    CircularProgressIndicator(color = AccentCyan)
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = stringResource(id = R.string.article_1_text_part_1),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White.copy(alpha = 0.9f),
                            lineHeight = 26.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp, topEnd = 16.dp, bottomEnd = 16.dp))
                                .background(AccentCyan.copy(alpha = 0.1f))
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .width(4.dp)
                                        .height(80.dp)
                                        .background(AccentCyan)
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(
                                    text = stringResource(id = R.string.article_1_quote),
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = AccentCyan,
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = buildAnnotatedString {
                                append(stringResource(id = R.string.article_1_text_part_2))
                                append("\n\n")
                                withStyle(style = SpanStyle(
                                    background = MarsRed.copy(alpha = 0.3f),
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )) {
                                    append(stringResource(id = R.string.article_1_highlight))
                                }
                            },
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White.copy(alpha = 0.9f),
                            lineHeight = 26.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (userSelection == 1) {
                            Button(
                                onClick = {
                                    userSelection = 0
                                    likes--
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = AccentCyan)
                            ) {
                                Icon(Icons.Default.ThumbUp, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = likes.toString(), fontWeight = FontWeight.Bold)
                            }
                        } else {
                            OutlinedButton(
                                onClick = {
                                    if (userSelection == 2) dislikes--
                                    userSelection = 1
                                    likes++
                                },
                                shape = RoundedCornerShape(12.dp),
                                border = ButtonDefaults.outlinedButtonBorder.copy(width = 2.dp),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = AccentCyan)
                            ) {
                                Icon(Icons.Outlined.ThumbUp, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = likes.toString())
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        if (userSelection == 2) {
                            Button(
                                onClick = {
                                    userSelection = 0
                                    dislikes--
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MarsRed)
                            ) {
                                Text(text = "👎 $dislikes", fontWeight = FontWeight.Bold)
                            }
                        } else {
                            OutlinedButton(
                                onClick = {
                                    if (userSelection == 1) likes--
                                    userSelection = 2
                                    dislikes++
                                },
                                shape = RoundedCornerShape(12.dp),
                                border = ButtonDefaults.outlinedButtonBorder.copy(width = 2.dp),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = MarsRed)
                            ) {
                                Text(text = "👎 $dislikes")
                            }
                        }
                    }

                    val shareText = stringResource(id = R.string.article_1_text_part_1) + "\n" + stringResource(id = R.string.article_1_quote)
                    Button(
                        onClick = { onShareClick(shareText) },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = NebulaPurple)
                    ) {
                        Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = stringResource(id = R.string.btn_share))
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    border = if (isSecondArticleRead) null else ButtonDefaults.outlinedButtonBorder.copy(brush = Brush.linearGradient(listOf(AccentCyan, NebulaPurple))),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSecondArticleRead) SuccessGreenBackground.copy(alpha = 0.4f) else DarkCardBackground.copy(alpha = 0.6f)
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (isSecondArticleRead) Icons.Default.CheckCircle else Icons.Default.Info,
                            contentDescription = null,
                            tint = if (isSecondArticleRead) SuccessGreenIcon else AccentCyan
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        val statusText = if (isSecondArticleRead) {
                            stringResource(id = R.string.status_yes)
                        } else {
                            stringResource(id = R.string.status_no)
                        }
                        Text(
                            text = stringResource(id = R.string.second_article_read_status, statusText),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (isSecondArticleRead) SuccessGreenText else AccentCyan
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "МЕЖГАЛАКТИЧЕСКИЕ НОВОСТИ",
                    style = MaterialTheme.typography.labelLarge,
                    color = AccentCyan.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onNextArticleClick() },
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = SecondaryCardBackground)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SubcomposeAsyncImage(
                            model = "https://avatars.mds.yandex.net/i?id=db1fca493d253e9a50f84f3e894a5aef_l-5233303-images-thumbs&n=13",
                            contentDescription = null,
                            modifier = Modifier
                                .size(90.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White.copy(alpha = 0.1f)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(id = R.string.article_2_title),
                                style = MaterialTheme.typography.titleMedium,
                                color = AccentCyan,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = stringResource(id = R.string.article_2_text_1),
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.7f),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                lineHeight = 18.sp
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            tint = AccentCyan
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}
