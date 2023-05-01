package com.kodeco.daysprint

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.daysprint.common.SnackBarManager
import com.kodeco.daysprint.common.SnackBarMessage.Companion.toSnackBarMessage
import kotlinx.coroutines.*

open class DaySprintViewModel : ViewModel() {
    fun launchCatching(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        snackBar: Boolean = true,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(
        dispatcher + CoroutineExceptionHandler { _, throwable ->
            if (snackBar) {
                SnackBarManager.showMessage(throwable.toSnackBarMessage())
            }
        },
        block = block
    )
}