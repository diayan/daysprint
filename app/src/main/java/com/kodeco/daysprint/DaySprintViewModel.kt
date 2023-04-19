package com.kodeco.daysprint

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.daysprint.common.SnackBarManager
import com.kodeco.daysprint.common.SnackBarMessage.Companion.toSnackBarMessage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class DaySprintViewModel : ViewModel() {
    fun launchCatching(snackBar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                if (snackBar) {
                    SnackBarManager.showMessage(throwable.toSnackBarMessage())
                }
            },
            block = block
        )
}