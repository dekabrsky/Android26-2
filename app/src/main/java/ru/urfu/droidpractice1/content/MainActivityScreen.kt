@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(isSecondArticleRead: Boolean, startSecondArticle: (Intent) -> Unit) {
    val context = LocalContext.current
    val scrollState = rememberScrollState();

    val isLikeArticle = rememberSaveable { mutableStateOf<Boolean?>(null) }
    val countOfLikes = rememberSaveable { mutableIntStateOf(0) }
    val countOfDislikes = rememberSaveable { mutableIntStateOf(0) }


    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title)
                        )
                    },
                    actions = {
                        IconButton(onClick = {
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, "Смотри какие котики")
                                type = "text/plain"
                            }
                            context.startActivity(sendIntent)
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Share, contentDescription = "share"
                            )
                        }
                    },
                )
            }) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(scrollState)
            ) {
                Column() {
                    Text(
                        text = stringResource(R.string.article1_header),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            if (isLikeArticle.value == null) {
                                isLikeArticle.value = true
                                countOfLikes.value++
                                return@IconButton
                            }
                            if (isLikeArticle.value == true) {
                                isLikeArticle.value = null
                                countOfLikes.value--
                                return@IconButton
                            }
                            isLikeArticle.value = true
                            countOfLikes.value++
                            countOfDislikes.value--
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ThumbUp,
                                contentDescription = "likes",
                                tint = if (isLikeArticle.value == true) Color.Blue else LocalContentColor.current
                            )
                        }
                        if (countOfLikes.value != 0) {
                            Text(
                                text = countOfLikes.value.toString()
                            )
                        }

                        IconButton(onClick = {
                            if (isLikeArticle.value == null) {
                                isLikeArticle.value = false
                                countOfDislikes.value++
                                return@IconButton
                            }
                            if (isLikeArticle.value == false) {
                                isLikeArticle.value = null
                                countOfDislikes.value--
                                return@IconButton
                            }
                            isLikeArticle.value = false
                            countOfDislikes.value++
                            countOfLikes.value--
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ThumbUp,
                                modifier = Modifier.rotate(180f),
                                contentDescription = "dislike",
                                tint = if (isLikeArticle.value == false) Color.Blue else LocalContentColor.current
                            )
                        }
                        if (countOfDislikes.value != 0) {
                            Text(
                                text = countOfDislikes.value.toString()
                            )
                        }
                    }

                    AsyncImage(
                        model = stringResource(R.string.article1_img),
                        contentDescription = "cat",
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    Box(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .padding(horizontal = 50.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(color = Color(0xFFA500A5))
                            .padding(vertical = 10.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.article1_citata),
                            fontStyle = FontStyle.Italic,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(top = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.article1_1),
                            textAlign = TextAlign.Justify
                        )

                        Text(
                            text = stringResource(R.string.article1_2),
                            textAlign = TextAlign.Justify
                        )

                        Text(
                            text = stringResource(R.string.article1_3),
                            textAlign = TextAlign.Justify
                        )
                    }

                    Box(
                        modifier = Modifier
                            .padding(top = 20.dp, bottom = 20.dp)
                            .padding(horizontal = 30.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(color = Color(0x762C2750))
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        ClickableText(
                            text = AnnotatedString(stringResource(R.string.article2_preview)),
                            style = TextStyle(
                                color = if (isSecondArticleRead) Color.Gray else Color.Black
                            ),
                            onClick = { _ ->
                                val secondArticleIntent =
                                    Intent(context, SecondActivity::class.java).apply {
                                        putExtra("isRead", isSecondArticleRead)
                                    }
                                startSecondArticle(secondArticleIntent)
                            })
                    }

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