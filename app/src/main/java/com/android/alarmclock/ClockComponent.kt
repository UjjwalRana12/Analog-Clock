import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.min

val red = Color(0xFFB81522)
val blue = Color(0xFF1C3064)
val clockblue = Color(0xFF1C6464)
val lightblue = Color(0xFF40C4FF)
val margenta = Color(0xFFB920A9)
val orangered = Color(0xFFFF5252)
@Composable
fun AnalogClock(
    hour: Float,
    minute: Float,
    borderColors: List<Color> = listOf(red,  lightblue, margenta, orangered)) {
    Box(
        modifier = Modifier
            .fillMaxSize(0.7f)
            .aspectRatio(1f)
            .background(Color.Black, shape = CircleShape)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val diameter = min(size.width, size.height) * 0.9f
            val radius = diameter / 2


            val borderWidth = 0.5.dp.toPx()
            val segmentAngle = 360f / borderColors.size

            borderColors.forEachIndexed { index, color ->
                drawArc(
                    color = color,
                    startAngle = segmentAngle * index,
                    sweepAngle = segmentAngle,
                    useCenter = false,
                    topLeft = Offset(
                        (size.width - diameter) / 2,
                        (size.height - diameter) / 2
                    ),
                    size = Size(diameter, diameter),
                    style = Stroke(width = borderWidth)
                )
            }

            val minRatio = minute / 60f
            val hourRatio = hour / 60f

            rotate(hourRatio * 360, center) {
                drawLine(
                    color = Color.Red,
                    start = center - Offset(0f, radius * 0.4f),
                    end = center + Offset(0f, radius * 0.1f),
                    strokeWidth = 3.8.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }

            rotate(minRatio * 360, center) {
                drawLine(
                    color = Color.Blue,
                    start = center - Offset(0f, radius * 0.6f),
                    end = center + Offset(0f, radius * 0.1f),
                    strokeWidth = 3.8.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMultiColoredCircle() {
    MaterialTheme {
        AnalogClock(hour = 10f, minute = 45f)
    }
}
