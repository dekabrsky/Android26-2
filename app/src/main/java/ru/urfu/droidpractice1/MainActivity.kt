package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private val viewModel: ArticleViewModel by viewModels()

    private val secondArticleLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val isRead = result.data?.getBooleanExtra(SecondActivity.EXTRA_IS_READ, false) ?: false
        viewModel.secondArticleRead = isRead
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainActivityScreen(
                viewModel = viewModel,

                onShareClick = {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT,
                            getString(R.string.article_share_text)
                        )
                    }

                    startActivity(
                        Intent.createChooser(
                            shareIntent,
                            getString(R.string.share_action)
                        )
                    )
                },

                onOpenSecondArticle = {
                    secondArticleLauncher.launch(Intent(this, SecondActivity::class.java))
                }
            )
        }
    }
}