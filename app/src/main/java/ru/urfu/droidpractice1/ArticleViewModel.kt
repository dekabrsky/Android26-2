package ru.urfu.droidpractice1

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ArticleViewModel : ViewModel() {

    var likes by mutableIntStateOf(0)
        private set

    var dislikes by mutableIntStateOf(0)
        private set

    var secondArticleRead by mutableStateOf(false)

    fun like() {
        likes++
    }

    fun dislike() {
        dislikes++
    }
}