package presentation.counter

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CounterScreen() {
    var counter by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = counter.toString(),
            fontSize = 30.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
        )
        Spacer(Modifier.height(16.dp))
        Row {
            Button(
                onClick = { ++counter },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "+",
                    fontSize = 25.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
            }
            Spacer(Modifier.width(16.dp))
            Button(
                onClick = { if (counter != 0) --counter },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "-",
                    fontSize = 25.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
            }
        }
    }
}
