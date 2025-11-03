package tw.edu.pu.o365.s1132234

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MoleViewModel: ViewModel() {

    private val GAME_DURATION = 60L

    var counter by mutableLongStateOf(0)
        private set

    var maxX by mutableStateOf(0)
        private set

    var maxY by mutableStateOf(0)
        private set

    var offsetX by mutableStateOf(0)
        private set

    var offsetY by mutableStateOf(0)
        private set

    var stay by mutableLongStateOf(0)
        private set

    var isGameActive by mutableStateOf(true)
        private set

    fun incrementCounter() {
        if (isGameActive) {
            counter++
        }
    }

    init {
        startCounting()
    }

    private fun startCounting() {
        viewModelScope.launch {
            // 將 while(true) 改為 while (stay < GAME_DURATION)
            while (stay < GAME_DURATION) {
                delay(1000L) // 等待 1 秒
                stay++
                moveMole()
            }

            isGameActive = false
        }
    }

    fun getArea(gameSize: IntSize, moleSize:Int) {
        maxX = gameSize.width - moleSize
        maxY = gameSize.height - moleSize
    }

    fun moveMole() {
        if (isGameActive) {
            offsetX = (0..maxX).random()
            offsetY = (0..maxY).random()
        }
    }

}