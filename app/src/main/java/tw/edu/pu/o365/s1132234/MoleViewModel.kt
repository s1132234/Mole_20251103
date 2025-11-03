package tw.edu.pu.o365.s1132234

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MoleViewModel: ViewModel() {

    var counter by mutableLongStateOf(0)
        private set

    var stay by mutableLongStateOf(0)
        private set

    fun incrementCounter() {
        counter++
    }

    init {
        startCounting()
    }

    private fun startCounting() {
        viewModelScope.launch {
            while (true) {
                delay(1000L)
                stay++
            }
        }
    }


}