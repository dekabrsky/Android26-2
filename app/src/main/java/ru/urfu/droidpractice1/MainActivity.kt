package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ru.urfu.droidpractice1.content.MainActivityScreen
import ru.urfu.droidpractice1.ui.theme.ArticleAppTheme

class MainViewModel(private val state: SavedStateHandle) : ViewModel() {

    var likes by mutableIntStateOf(state.get<Int>("likes") ?: 124)
        private set

    var dislikes by mutableIntStateOf(state.get<Int>("dislikes") ?: 8)
        private set

    private val _isSecondRead = mutableStateOf(state.get<Boolean>("is_second_read") ?: false)

    var isSecondRead: Boolean
        get() = _isSecondRead.value
        set(value) {
            _isSecondRead.value = value
            state["is_second_read"] = value
        }

    fun onLike() {
        likes++
        state["likes"] = likes
    }

    fun onDislike() {
        dislikes++
        state["dislikes"] = dislikes
    }
}

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val secondActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data
        if (data != null && data.hasExtra("IS_READ")) {
            val isRead = data.getBooleanExtra("IS_READ", false)
            viewModel.isSecondRead = isRead
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ArticleAppTheme {
                MainActivityScreen(
                    likesCount = viewModel.likes,
                    dislikesCount = viewModel.dislikes,
                    isSecondArticleRead = viewModel.isSecondRead,
                    onLikeClick = { viewModel.onLike() },
                    onDislikeClick = { viewModel.onDislike() },
                    onShareClick = { shareArticleText() },
                    onNextArticleClick = {
                        val intent = Intent(this, SecondActivity::class.java).apply {
                            putExtra("IS_READ_INITIAL", viewModel.isSecondRead)
                        }
                        secondActivityLauncher.launch(intent)
                    }
                )
            }
        }
    }

    private fun shareArticleText() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Облигации «Самолета» рухнули почти на 40% из-за частичного погашения. Читать подробнее в Финансовом Вестнике."
            )
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, "Поделиться статьей"))
    }
}