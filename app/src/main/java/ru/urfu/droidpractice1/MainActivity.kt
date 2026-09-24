package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : LoggedActivity() {
    private var likes by mutableIntStateOf(0)
    private var dislikes by mutableIntStateOf(0)
    private var secondArticleRead by mutableStateOf(false)

    private val openArticle = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            secondArticleRead = result.data?.getBooleanExtra(SecondActivity.EXTRA_READ, false)
                ?: secondArticleRead
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        likes = savedInstanceState?.getInt("likes") ?: 0
        dislikes = savedInstanceState?.getInt("dislikes") ?: 0
        secondArticleRead = savedInstanceState?.getBoolean("secondArticleRead") ?: false
        setContent {
            MainActivityScreen(
                likes = likes,
                dislikes = dislikes,
                secondArticleRead = secondArticleRead,
                onLike = { likes++ },
                onDislike = { dislikes++ },
                onShare = ::shareArticle,
                onOpenArticle = {
                    openArticle.launch(Intent(this, SecondActivity::class.java).apply {
                        putExtra(SecondActivity.EXTRA_READ, secondArticleRead)
                    })
                }
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("likes", likes)
        outState.putInt("dislikes", dislikes)
        outState.putBoolean("secondArticleRead", secondArticleRead)
        super.onSaveInstanceState(outState)
    }

    private fun shareArticle() {
        val text = listOf(
            R.string.first_title, R.string.first_intro, R.string.first_heading,
            R.string.first_body, R.string.first_quote, R.string.first_end
        ).joinToString("\n\n") { getString(it) }
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.first_title))
            putExtra(Intent.EXTRA_TEXT, text)
        }
        startActivity(Intent.createChooser(intent, getString(R.string.share_article)))
    }
}
