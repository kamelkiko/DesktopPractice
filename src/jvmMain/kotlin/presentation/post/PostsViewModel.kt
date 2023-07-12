package presentation.post

import domain.usecase.GetPostsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PostsViewModel:KoinComponent{
    private val getPostsUseCase: GetPostsUseCase by inject()
    private val _postUiState = MutableStateFlow(PostUiState())
    val postUiState = _postUiState.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        getPosts()
        println(getPostsUseCase)
    }

    private fun getPosts() {
        scope.launch {
            _postUiState.update {
                it.copy(
                    posts = getPostsUseCase()
                )
            }
          onCleared()
        }
    }
    private fun onCleared(){
        scope.cancel()
    }
}