package data.remote.service

import data.remote.dto.PostDto

interface PostService {
    suspend fun getPosts(): List<PostDto>
}