@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.model.ArticleViewModel
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(articleViewModel: ArticleViewModel = viewModel()) {
    val articleText1 = stringResource(R.string.block_text1)
    val articleText2 = stringResource(R.string.block_text2)
    val articleText3 = stringResource(R.string.block_text3)
    val scrollState = rememberScrollState()

    val uiState by articleViewModel.uiState.collectAsState()

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.article_title),
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    actions = {
                        ShareIconButton(articleText1 + articleText2 + articleText3)
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(scrollState)
                    .padding(10.dp)
            ) {


                Text (
                    text = articleText1,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )

                Spacer(modifier = Modifier.height(10.dp))

                AsyncImage(
                    model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKo6naf1Pbx8Zyg2Ofwbj-uM9nkrsvO9lZQ-V0oifnMQ&s=10",
                    contentDescription = "Фотография дома первого поколения советского типового индустриального домостроения, \"Хрущёвка\"",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.image_line_icon),
                    error = painterResource(R.drawable.error_image_photo_icon)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text (
                    text = articleText2,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )

                Spacer(modifier = Modifier.height(25.dp))

                Text (
                    text = articleText3,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconToggleButton(
                        checked = uiState.isLiked,
                        onCheckedChange = { articleViewModel.onLikeCliked() }
                    ) {
                        Icon(
                            imageVector = if (uiState.isLiked) Icons.Filled.ThumbUp else Icons.Outlined.ThumbUp,
                            contentDescription = "Лайк",
                            tint = if (uiState.isLiked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Text(text = uiState.likesCount.toString())

                    Spacer(modifier = Modifier.width(16.dp))

                    IconToggleButton(
                        checked = uiState.isDisliked,
                        onCheckedChange = { articleViewModel.onDislikeCliked() }
                    ) {
                        Icon(
                            imageVector = if (uiState.isDisliked) Icons.Filled.ThumbUp else Icons.Outlined.ThumbUp,
                            contentDescription = "Дизлайк",
                            modifier = Modifier.rotate(180f),
                            tint = if (uiState.isDisliked) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Text(text = uiState.dislikesCount.toString())
                }

                Spacer(modifier = Modifier.height(15.dp))

                NavigateToXmlActivityButton()
            }
        }
    }
}

@Composable
fun NavigateToXmlActivityButton() {
    val context = LocalContext.current

    Button(onClick = {
        val intent = Intent(context, SecondActivity::class.java)

        context.startActivity(intent)
    }) {
        Text(text = stringResource(R.string.article2_button_name))
    }
}

@Composable
fun ShareIconButton(textToShare: String) {
    val context = LocalContext.current

    IconButton(onClick = {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, textToShare)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Поделиться через")
        context.startActivity(shareIntent)
    }) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "Иконка поделиться"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}