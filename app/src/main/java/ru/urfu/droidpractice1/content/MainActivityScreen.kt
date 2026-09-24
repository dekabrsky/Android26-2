@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    likes: Int,
    dislikes: Int,
    secondArticleRead: Boolean,
    onLike: () -> Unit,
    onDislike: () -> Unit,
    onShare: () -> Unit,
    onOpenArticle: () -> Unit
) {
    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
                    .verticalScroll(rememberScrollState()).padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Text(stringResource(R.string.first_category), style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary)
                Text(stringResource(R.string.first_title), style = MaterialTheme.typography.headlineLarge)
                Text(stringResource(R.string.first_intro), style = MaterialTheme.typography.titleMedium)
                AsyncImage(
                    model = stringResource(R.string.first_image_url),
                    contentDescription = stringResource(R.string.first_image_description),
                    placeholder = painterResource(R.drawable.mountain_placeholder),
                    error = painterResource(R.drawable.mountain_placeholder),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(230.dp)
                )
                Text(stringResource(R.string.first_caption), style = MaterialTheme.typography.labelSmall)
                Text(stringResource(R.string.first_heading), style = MaterialTheme.typography.headlineSmall)
                Text(stringResource(R.string.first_body), style = MaterialTheme.typography.bodyLarge)
                Card(modifier = Modifier.fillMaxWidth()) {
                    Text(stringResource(R.string.first_quote), modifier = Modifier.padding(20.dp),
                        style = MaterialTheme.typography.titleMedium, fontStyle = FontStyle.Italic)
                }
                Text(stringResource(R.string.first_end), style = MaterialTheme.typography.bodyLarge)
                Text(stringResource(R.string.rate_article), style = MaterialTheme.typography.titleMedium)
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedButton(onClick = onLike, modifier = Modifier.weight(1f)) {
                        Text(stringResource(R.string.likes, likes))
                    }
                    OutlinedButton(onClick = onDislike, modifier = Modifier.weight(1f)) {
                        Text(stringResource(R.string.dislikes, dislikes))
                    }
                }
                Button(onClick = onShare, modifier = Modifier.fillMaxWidth()) {
                    Text(stringResource(R.string.share_article))
                }
                Card(onClick = onOpenArticle, modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(stringResource(R.string.read_next), style = MaterialTheme.typography.labelLarge)
                        Text(stringResource(R.string.second_title), style = MaterialTheme.typography.titleLarge)
                        Text(stringResource(if (secondArticleRead) R.string.read else R.string.not_read),
                            color = MaterialTheme.colorScheme.primary)
                        Text(stringResource(R.string.open_article), style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        }
    }
}
