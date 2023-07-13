package app

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Tray
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
    val icon = painterResource("batman.jpg")

    Tray(
        icon = icon,
        menu = {
            Item("Quit App", onClick = ::exitApplication)
        }
    )
    Window(onCloseRequest = ::exitApplication, icon = icon) {
        App(koin)
//        runBlocking {
//            val location = LocationManager().getLocation()
//            println("${location.loc}")
//        }
    }
}
