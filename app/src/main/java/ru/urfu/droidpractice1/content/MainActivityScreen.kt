@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen() {
    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title)
                        )
                    }
                )
            }) { innerPadding ->
            Column (
                modifier = Modifier.padding(innerPadding)
            ) {
                Text (text = stringResource(id = R.string.block_text1))
                AsyncImage(
                    model = "https://thumb.wikimedia.org/wikipedia/commons/thumb/1/11/%D0%93%D0%B0%D1%82%D1%87%D0%B8%D0%BD%D0%B0._%D0%A3%D0%BB%D0%B8%D1%86%D0%B0_%D0%92%D0%BE%D0%BB%D0%BE%D0%B4%D0%B0%D1%80%D1%81%D0%BA%D0%BE%D0%B3%D0%BE%2C_28.jpg/330px-%D0%93%D0%B0%D1%82%D1%87%D0%B8%D0%BD%D0%B0._%D0%A3%D0%BB%D0%B8%D1%86%D0%B0_%D0%92%D0%BE%D0%BB%D0%BE%D0%B4%D0%B0%D1%80%D1%81%D0%BA%D0%BE%D0%B3%D0%BE%2C_28.jpg?utm_source=ru.wikipedia.org&utm_campaign=parser&utm_content=thumbnail",
                    contentDescription = null,
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