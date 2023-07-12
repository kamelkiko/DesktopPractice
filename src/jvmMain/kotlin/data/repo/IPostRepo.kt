package data.repo

import domain.entity.PostEntity

interface IPostRepo {
    suspend fun getPosts(): List<PostEntity>
}