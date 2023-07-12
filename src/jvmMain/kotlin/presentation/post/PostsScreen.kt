package presentation.post

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PostsScreen(postsViewModel: PostsViewModel) {
    val state by postsViewModel.postUiState.collectAsState()
    PostsContent(state = state)
    println(postsViewModel)
    println(state.hashCode())
}

@Composable
fun PostsContent(state: PostUiState) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(state.posts) {
                Card(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                    shape = RoundedCornerShape(8.dp),
                    elevation = 0.dp,
                    backgroundColor = Color.Black,
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(8.dp),
                    ) {
                        Text(
                            text = it?.title.toString(),
                            color = Color.White
                        )
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = it?.body.toString(),
                            color = Color.White
                        )
                    }

                }
            }

        }
    }
}