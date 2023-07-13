package app

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.di.initKoin
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.java.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.Koin
import presentation.post.PostsScreen
import java.io.ByteArrayInputStream
import java.io.IOException


@Composable
@Preview
fun App(koin: Koin) {
    MaterialTheme {
        PostsScreen(koin.get())
        Image(painterResource("batman.jpg"), contentDescription = null)

//        val density = LocalDensity.current
//        AsyncImage(
//            load = {
//                app.loadImageBitmap(
//                    "https://img.freepik.com/free-photo/red-white-cat-i-white-studio_155003-13189.jpg?w=2000",
//                )
//            },
//            painterFor = { it },
//            contentDescription = "Idea logo",
//            contentScale = ContentScale.FillWidth,
//            modifier = Modifier.width(200.dp)
//        )
    }
}

@Composable
fun <T> AsyncImage(
    load: suspend () -> T,
    painterFor: @Composable (T) -> ImageBitmap,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
) {
    val image: T? by produceState<T?>(null) {
        value = withContext(Dispatchers.IO) {
            try {
                load()
            } catch (e: IOException) {
                // instead of printing to console, you can also write this to log,
                // or show some error placeholder
                e.printStackTrace()
                null
            }
        }
    }

    if (image != null) {
        Image(
            bitmap = painterFor(image!!),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}

private suspend fun loadSvgPainter(url: String, density: Density): Painter =
    urlStream(url).use { loadSvgPainter(it, density) }

private suspend fun urlStream(url: String) = HttpClient(Java).use {
    ByteArrayInputStream(it.get(url).body())
}

private suspend fun loadImageBitmap(url: String): ImageBitmap =
    urlStream(url).use { loadImageBitmap(it) }

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
