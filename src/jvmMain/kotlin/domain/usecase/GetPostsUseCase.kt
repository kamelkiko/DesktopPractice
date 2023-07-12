package domain.usecase

import data.repo.IPostRepo
import presentation.post.toUiState

class GetPostsUseCase(
    private val repo: IPostRepo
) {
    init {
        println(repo)
    }
    suspend operator fun invoke() = repo.getPosts().toUiState()
}