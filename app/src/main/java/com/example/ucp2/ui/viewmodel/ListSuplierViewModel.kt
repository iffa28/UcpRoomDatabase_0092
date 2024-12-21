package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Suplier
import com.example.ucp2.repository.RepositorySplr
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class ListSplrViewModel(
    private val repositorySplr: RepositorySplr
): ViewModel() {
    val listSuplierUiState: StateFlow<ListSuplierUiState> = repositorySplr.getAllSplr()
        .filterNotNull()
        .map {
            ListSuplierUiState(
                listSplr = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(ListSuplierUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                ListSuplierUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ListSuplierUiState(
                isLoading = true,)
        )
}

data class ListSuplierUiState(
    val listSplr: List<Suplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)