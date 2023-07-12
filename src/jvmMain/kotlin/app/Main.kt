package app

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.di.initKoin
import org.koin.core.Koin
import presentation.post.PostsScreen


@Composable
@Preview
fun App(koin: Koin) {
    MaterialTheme {
        PostsScreen(koin.get())
    }
}

fun main() = application {
    val koin = initKoin()
    Window(onCloseRequest = ::exitApplication) {
        App(koin)
//        runBlocking {
//            val location = LocationManager().getLocation()
//            println("${location.loc}")
//        }
    }
}
