package ru.urfu.droidpractice1.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ArticleUiState (
    val likesCount: Int = 0,
    val dislikesCount: Int = 0,
    val isLiked: Boolean = false,
    val isDisliked: Boolean = false
)

class ArticleViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ArticleUiState())
    val uiState: StateFlow<ArticleUiState> = _uiState.asStateFlow()

    fun onLikeCliked() {
        _uiState.update { currentState ->
            when {
                currentState.isLiked -> {
                    currentState.copy(isLiked = false, likesCount = currentState.likesCount - 1)
                }
                currentState.isDisliked -> {
                    currentState.copy(
                        isLiked = true,
                        likesCount = currentState.likesCount + 1,
                        isDisliked = false,
                        dislikesCount = currentState.dislikesCount - 1
                    )
                }
                else -> {
                    currentState.copy(isLiked = true, likesCount = currentState.likesCount + 1)
                }
            }
        }
    }

    fun onDislikeCliked() {
        _uiState.update { currentState ->
            when {
                currentState.isDisliked -> {
                    currentState.copy(isDisliked = false, dislikesCount = currentState.dislikesCount - 1)
                }
                currentState.isLiked -> {
                    currentState.copy(
                        isDisliked = true,
                        dislikesCount = currentState.dislikesCount + 1,
                        isLiked = false,
                        likesCount = currentState.likesCount - 1
                    )
                }
                else -> {
                    currentState.copy(isDisliked = true, dislikesCount = currentState.dislikesCount + 1)
                }
            }
        }
    }
}