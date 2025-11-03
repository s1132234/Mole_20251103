package tw.edu.pu.o365.s1132234

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import tw.edu.pu.o365.s1132234.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MoleScreen()
            }
        }
    }
}

@Composable
fun MoleScreen(moleViewModel: MoleViewModel = viewModel()) {
    val counter = moleViewModel.counter
    //var counter by rememberSaveable { mutableLongStateOf(0) }
    val stay = moleViewModel.stay

    val density = LocalDensity.current

    // 地鼠Dp轉Px
    val moleSizeDp = 150.dp
    val moleSizePx = with(density) { moleSizeDp.roundToPx() }

    Box (
        modifier = Modifier.fillMaxSize()
            .onSizeChanged { intSize ->
                moleViewModel.getArea(intSize, moleSizePx) },
        Alignment.Center
    ) {
        Text("分數: $counter \n時間: $stay")
    }

    Image(
        painter = painterResource(id = R.drawable.mole),
        contentDescription = "地鼠",
        modifier = Modifier
            .offset { IntOffset(moleViewModel.offsetX, moleViewModel.offsetY) }
            .size(moleSizeDp)
            .clickable {  moleViewModel.incrementCounter() }
    )
}