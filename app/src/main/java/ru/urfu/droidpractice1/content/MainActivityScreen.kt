@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.res.painterResource
import ru.urfu.droidpractice1.R
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    isSecondArticleRead: Boolean = false,
    onNavigateToSecond: () -> Unit = {}
) {
    val context = LocalContext.current
    
    var likes by rememberSaveable { mutableIntStateOf(0) }
    var dislikes by rememberSaveable { mutableIntStateOf(0) }
    
    val articleText = """
        Искусственный интеллект в космических исследованиях
        
        Современные космические миссии становятся все более сложными и амбициозными. 
        Одним из ключевых факторов успеха является применение искусственного интеллекта 
        для анализа данных и управления космическими аппаратами.
        
        Автономные системы на основе ИИ позволяют космическим зондам принимать решения 
        в реальном времени, не дожидаясь команд с Земли. Это особенно важно для миссий 
        к далеким планетам, где задержка связи может составлять десятки минут.
        
        Машинное обучение помогает ученым обрабатывать огромные объемы данных, 
        получаемых с телескопов и космических аппаратов. Алгоритмы могут находить 
        закономерности, которые человеку было бы сложно заметить.
        
        В будущем ИИ станет незаменимым помощником в освоении космоса, 
        делая космические исследования более эффективными и безопасными.
    """.trimIndent()
    
    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Космические технологии")
                    },
                    actions = {
                        IconButton(onClick = {
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, articleText)
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, null)
                            context.startActivity(shareIntent)
                        }) {
                            Icon(Icons.Default.Share, contentDescription = "Поделиться")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Text(
                    text = "Искусственный интеллект в космических исследованиях",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                AsyncImage(
                    model = "https://images.unsplash.com/photo-1446776653964-20c1d3a81b06?w=800",
                    contentDescription = "Космические исследования",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Космические технологии будущего",
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = articleText,
                    style = MaterialTheme.typography.bodyLarge,
                    lineHeight = 24.sp,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { likes++ }) {
                            Icon(Icons.Default.ThumbUp, contentDescription = "Лайк")
                        }
                        Text(
                            text = likes.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { dislikes++ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_thumb_down),
                                contentDescription = "Дизлайк",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Text(
                            text = dislikes.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))

                if (isSecondArticleRead) {
                    Text(
                        text = "✓ Вторая статья прочитана",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                Button(
                    onClick = onNavigateToSecond,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Читать вторую статью")
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}