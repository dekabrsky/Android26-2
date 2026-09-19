@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen() {
    DroidPractice1Theme {
        val context = LocalContext.current
        val shareText = listOf(
            stringResource(R.string.article_title),
            stringResource(R.string.article_lead),
            stringResource(R.string.article_intro),
            stringResource(R.string.article_section_start),
            stringResource(R.string.article_start_text),
            stringResource(R.string.article_section_background),
            stringResource(R.string.article_background_text),
            stringResource(R.string.article_section_rotation),
            stringResource(R.string.article_rotation_text)
        ).joinToString(separator = "\n\n")
        val chooserTitle = stringResource(R.string.share_chooser_title)
        var likes by rememberSaveable { mutableStateOf(0) }
        var dislikes by rememberSaveable { mutableStateOf(0) }
        var isSecondArticleRead by rememberSaveable { mutableStateOf(false) }
        val secondArticleLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                isSecondArticleRead = result.data?.getBooleanExtra(
                    SecondActivity.EXTRA_IS_READ,
                    isSecondArticleRead
                ) ?: isSecondArticleRead
            }
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(R.string.article_title))
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.article_title),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(R.string.article_lead),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                AsyncImage(
                    model = R.drawable.elections_hero,
                    contentDescription = stringResource(R.string.article_image_description),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .padding(top = 20.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = stringResource(R.string.article_source),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                ArticleParagraph(text = stringResource(R.string.article_intro))
                ArticleSection(
                    title = stringResource(R.string.article_section_start),
                    text = stringResource(R.string.article_start_text)
                )
                ArticleSection(
                    title = stringResource(R.string.article_section_background),
                    text = stringResource(R.string.article_background_text)
                )

                Text(
                    text = stringResource(R.string.article_quote),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    style = MaterialTheme.typography.titleMedium,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.primary
                )

                ArticleSection(
                    title = stringResource(R.string.article_section_rotation),
                    text = stringResource(R.string.article_rotation_text)
                )

                Text(
                    text = stringResource(R.string.article_rating_title),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { likes++ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = stringResource(R.string.like_button, likes))
                    }

                    OutlinedButton(
                        onClick = { dislikes++ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = stringResource(R.string.dislike_button, dislikes))
                    }
                }

                OutlinedButton(
                    onClick = {
                        val sendIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, shareText)
                        }
                        context.startActivity(Intent.createChooser(sendIntent, chooserTitle))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                ) {
                    Text(text = stringResource(R.string.share_article))
                }

                Text(
                    text = stringResource(
                        if (isSecondArticleRead) {
                            R.string.second_article_read_status
                        } else {
                            R.string.second_article_unread_status
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (isSecondArticleRead) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )

                Button(
                    onClick = {
                        val intent = Intent(context, SecondActivity::class.java).apply {
                            putExtra(SecondActivity.EXTRA_IS_READ, isSecondArticleRead)
                        }
                        secondArticleLauncher.launch(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 24.dp)
                ) {
                    Text(text = stringResource(R.string.open_second_article))
                }
            }
        }
    }
}

@Composable
private fun ArticleSection(title: String, text: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.SemiBold
    )
    ArticleParagraph(text = text)
}

@Composable
private fun ArticleParagraph(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Justify
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}
