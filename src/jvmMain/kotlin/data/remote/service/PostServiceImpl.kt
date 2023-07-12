package data.remote.service

import app.util.HttpRoute
import data.remote.dto.PostDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class PostServiceImpl(private val client: HttpClient) : PostService {
    override suspend fun getPosts(): List<PostDto> {
        return client.get {
            url(HttpRoute.POSTS)
            contentType(ContentType.Application.Json)
        }.body()
    }
}