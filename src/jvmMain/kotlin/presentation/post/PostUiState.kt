package presentation.post

import domain.entity.PostEntity

data class PostUiState(
    val posts: List<Post?> = listOf()
)

data class Post(
    val title: String? = "",
    val body: String? = "",
)

fun PostEntity.toUiState() =
    Post(
        title = title,
        body = body
    )

fun List<PostEntity>.toUiState(): List<Post?> = map {
    it.toUiState()
}
