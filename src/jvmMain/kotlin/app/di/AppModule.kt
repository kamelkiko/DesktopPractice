package app.di

import data.remote.service.PostService
import data.remote.service.PostServiceImpl
import data.repo.IPostRepo
import data.repo.PostRepo
import domain.usecase.GetPostsUseCase
import io.ktor.client.*
import io.ktor.client.engine.java.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.gson.*
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import presentation.post.PostsViewModel

val module = module {
    single {
        HttpClient(Java) {
            install(ContentNegotiation) {
                gson()
            }
        }
    }
    singleOf(::PostRepo) { bind<IPostRepo>() }

    singleOf(::PostServiceImpl) { bind<PostService>() }

    single { GetPostsUseCase(get()) }

    single { PostsViewModel(get()) }

}

fun initKoin(): Koin {
    return startKoin {
        modules(modules = module)
    }.koin
}