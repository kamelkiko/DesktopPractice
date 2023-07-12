package data.repo

import data.remote.dto.toDomain
import data.remote.service.PostService
import domain.entity.PostEntity

class PostRepo(
    private val postService: PostService
) : IPostRepo {
    init {
        println(postService)
    }

    override suspend fun getPosts(): List<PostEntity> {
        return postService.getPosts().toDomain()
    }
}