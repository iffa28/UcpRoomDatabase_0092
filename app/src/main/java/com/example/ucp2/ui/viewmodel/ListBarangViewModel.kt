package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.repository.RepositoryBrg
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class ListBrgViewModel(
    private val repositoryBrg: RepositoryBrg
): ViewModel() {
    val listBarangUIState: StateFlow<ListBarangUiState> = repositoryBrg.getAllBrg()
        .filterNotNull()
        .map {
            ListBarangUiState(
                listBrg = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(ListBarangUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                ListBarangUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ListBarangUiState(
                isLoading = true,)
        )
}

data class ListBarangUiState(
    val listBrg: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)