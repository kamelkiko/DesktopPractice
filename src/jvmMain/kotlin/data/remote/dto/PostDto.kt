package data.remote.dto

import domain.entity.PostEntity

data class PostDto(
    val body: String?,
    val id: Int?,
    val title: String?,
    val userId: Int?
)

fun List<PostDto>.toDomain() = map {
    it.toDomain()
}

fun PostDto.toDomain() = PostEntity(
    id = id,
    title = title,
    body = body,
    userId = userId,
)