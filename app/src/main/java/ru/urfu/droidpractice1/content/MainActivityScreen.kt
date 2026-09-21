@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen() {
    val context = LocalContext.current

    // rememberSaveable переживает поворот экрана и пересоздание процесса
    var likes by rememberSaveable { mutableIntStateOf(0) }
    var dislikes by rememberSaveable { mutableIntStateOf(0) }
    var isSecondRead by rememberSaveable { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            isSecondRead = result.data
                ?.getBooleanExtra(SecondActivity.EXTRA_IS_READ, false) ?: false
        }
    }

    val title = stringResource(R.string.article_title)
    val intro = stringResource(R.string.article_intro)
    val body = stringResource(R.string.article_body)
    val body2 = stringResource(R.string.article_body_2)
    val shareChooser = stringResource(R.string.share_chooser)

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopAppBar(title = { Text(title) }) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.article_subtitle),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                AsyncImage(
                    model = "https://picsum.photos/id/225/800/450",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.medium)
                )

                Text(
                    text = intro,
                    style = MaterialTheme.typography.titleMedium,
                    fontStyle = FontStyle.Italic
                )

                Text(
                    text = stringResource(R.string.article_heading),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(text = body, style = MaterialTheme.typography.bodyLarge)

                Text(
                    text = stringResource(R.string.article_quote),
                    style = MaterialTheme.typography.bodyLarge,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = stringResource(R.string.article_quote_author),
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(text = body2, style = MaterialTheme.typography.bodyLarge)

                HorizontalDivider()

                // Лайки / дизлайки
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = { likes++ }) { Text("👍 $likes") }
                    OutlinedButton(onClick = { dislikes++ }) { Text("👎 $dislikes") }
                }

                // Поделиться
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        val send = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_SUBJECT, title)
                            putExtra(Intent.EXTRA_TEXT, "$title\n\n$intro\n\n$body\n\n$body2")
                        }
                        context.startActivity(Intent.createChooser(send, shareChooser))
                    }
                ) { Text(stringResource(R.string.share_button)) }

                HorizontalDivider()

                // Переход ко второй статье
                Text(
                    text = stringResource(
                        if (isSecondRead) R.string.second_article_read
                        else R.string.second_article_unread
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isSecondRead) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurfaceVariant
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        val intent = Intent(context, SecondActivity::class.java)
                            .putExtra(SecondActivity.EXTRA_IS_READ, isSecondRead)
                        launcher.launch(intent)
                    }
                ) { Text(stringResource(R.string.next_article_button)) }
            }
        }
    }
}