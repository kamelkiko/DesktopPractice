package util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.java.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayInputStream
import java.io.IOException


@Composable
fun loadImage() {
    AsyncImage(
        load = {
            loadImageBitmap(
                "https://img.freepik.com/free-photo/red-white-cat-i-white-studio_155003-13189.jpg?w=2000",
            )
        },
        painterFor = { it },
        contentDescription = "Cat image",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.width(200.dp)
    )
}

@Composable
fun loadSvg() {
    val density = LocalDensity.current
    AsyncSvg(
        load = {
            loadSvgPainter(
                "https://github.com/JetBrains/compose-multiplatform/raw/master/artwork/idea-logo.svg",
                density
            )
        },
        painterFor = { it },
        contentDescription = "Idea logo",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.width(200.dp)
    )
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

@Composable
fun <T> AsyncSvg(
    load: suspend () -> T,
    painterFor: @Composable (T) -> Painter,
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
            painter = painterFor(image!!),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}

private suspend fun loadSvgPainter(url: String, density: Density): Painter =
    urlStream(url).use { androidx.compose.ui.res.loadSvgPainter(it, density) }

private suspend fun loadImageBitmap(url: String): ImageBitmap =
    urlStream(url).use { androidx.compose.ui.res.loadImageBitmap(it) }

private suspend fun urlStream(url: String) = HttpClient(Java).use {
    ByteArrayInputStream(it.get(url).body())
}

@Composable
fun loadImageFromResource() {
    Image(painter = painterResource("batman.jpg"), contentDescription = null)
}