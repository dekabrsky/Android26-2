@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.AppTypography
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.CalibriFamily
import com.bumptech.glide.integration.compose.GlideImage
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.colorResource
import androidx.core.content.ContextCompat.getString


enum class VoteState {
    NONE, LIKED, DISLIKED
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MainActivityScreen(switchEnabled: Boolean, onLinkClick: () -> Unit) {
    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = stringResource(id = R.string.article_title), style = AppTypography.titleLarge) }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding).verticalScroll(rememberScrollState())
            ) {
                CompositionLocalProvider(
                    LocalMinimumInteractiveComponentSize provides 0.dp
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp, top = 5.dp),
                        horizontalArrangement = Arrangement.Center) {
                        var voteState by rememberSaveable { mutableStateOf(VoteState.NONE) }
                        val likeCounter by remember(voteState) {
                            derivedStateOf {
                                when (voteState) {
                                    VoteState.LIKED -> 1
                                    VoteState.DISLIKED -> -1
                                    VoteState.NONE -> 0
                                }
                            }
                        }

                        IconButton(onClick = {
                            voteState = if (voteState == VoteState.LIKED) VoteState.NONE else VoteState.LIKED
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_like),
                                contentDescription = "Лайк",
                                tint = if (voteState == VoteState.LIKED) colorResource(R.color.red) else colorResource(R.color.gray)
                            )
                        }

                        IconButton(onClick = {
                            voteState = if (voteState == VoteState.DISLIKED) VoteState.NONE else VoteState.DISLIKED
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_dislike),
                                contentDescription = "Дизлайк",
                                tint = if (voteState == VoteState.DISLIKED) colorResource(R.color.red) else colorResource(R.color.gray)
                            )
                        }

                        Text("$likeCounter", style = AppTypography.bodyLarge, modifier = Modifier.padding(end = 50.dp))

                        val context = LocalContext.current
                        val shareText = buildString {
                            append(getString(context, R.string.article_title))
                            append("\n\n")
                            append(getString(context, R.string.introduction))
                            append("\n\n")
                            append(getString(context, R.string.vtubers_general_1))
                            append("\n\n")
                            append(getString(context, R.string.vtubers_general_2))
                            append("\n\n")
                            append(getString(context, R.string.vtubers_general_3))
                            append("\n\n")
                            append(getString(context, R.string.link))
                        }
                        Button(
                            onClick = {
                                val sendIntent = Intent(Intent.ACTION_SEND).apply {
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_TEXT, shareText)
                                }
                                context.startActivity(Intent.createChooser(sendIntent, null))
                            },
                            colors = buttonColors(
                                containerColor = colorResource(R.color.violet),
                                contentColor = colorResource(R.color.white)
                            )
                        ) {
                            Text(text = stringResource(R.string.share))
                        }
                    }
                }

                Column {
                    Text(text = stringResource(id = R.string.vtubers_general_1), style = AppTypography.bodyLarge, modifier = Modifier.padding(horizontal = 15.dp))
                    GlideImage(
                        model = "https://static.wikia.nocookie.net/virtualyoutuber/images/9/98/ASOBI_MAWARI_TAI_Key_Visual_by_wata.jpg/revision/latest/scale-to-width-down/1000?cb=20260919050019",
                        contentDescription = "Hololive new generation",
                        modifier = Modifier.size(width = 600.dp, height = 300.dp).padding(top = 10.dp)
                    )
                    Text(text = stringResource(id = R.string.vtubers_general_2), style = AppTypography.bodyLarge, modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp), fontFamily = CalibriFamily)
                    Text(text = stringResource(id = R.string.vtubers_general_3), style = AppTypography.bodyLarge, modifier = Modifier.padding(horizontal = 15.dp))

                    val backgroundColor = if (switchEnabled) colorResource(R.color.mint) else colorResource(R.color.light_pink)
                    Text(
                        text = stringResource(id = R.string.link),
                        style = AppTypography.labelSmall,
                        modifier = Modifier
                            .padding(15.dp)
                            .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                            .clickable {
                                onLinkClick()
                            }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}