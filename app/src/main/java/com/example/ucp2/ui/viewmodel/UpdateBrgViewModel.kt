package com.example.ucp2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.repository.RepositoryBrg
import com.example.ucp2.ui.navigation.DestinasiUpdateBrg
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg
) : ViewModel() {
    var updateBrgUIState by mutableStateOf(BrgUIState())
        private set
    private val _id: String = checkNotNull(savedStateHandle[DestinasiUpdateBrg.ID]) //di modul DestinasiEdit

    init {
        viewModelScope.launch {
            updateBrgUIState = repositoryBrg.getBrg(_id.toInt())
                .filterNotNull()
                .first()
                .toUIStateBrg()
        }
    }

    fun updateState (barangEvent: BarangEvent) {
        updateBrgUIState = updateBrgUIState.copy(
            barangEvent = barangEvent,
        )
    }

    fun validateFields(): Boolean {
        val event = updateBrgUIState.barangEvent
        val errorState = FormErrorState(
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "deskripsi tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga harus berupa angka",
            stok = if (event.stok.toIntOrNull() != null) null else "Stok harus berupa angka",
            namaSuplier = if (event.namaSuplier.isNotEmpty()) null else "Nama Suplier tidak boleh kosong",
        )
        updateBrgUIState = updateBrgUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData() {
        val currentEvent = updateBrgUIState.barangEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryBrg.updateBrg(currentEvent.toBarangEntity())
                    updateBrgUIState = updateBrgUIState.copy(
                        snackBarMessage = "Data berhasil di update",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("snackBarMessage diatur: ${updateBrgUIState.snackBarMessage}")
                } catch (e: Exception) {
                    updateBrgUIState = updateBrgUIState.copy(
                        snackBarMessage = "Data gagal diupdate"
                    )
                }
            }
        } else {
            updateBrgUIState = updateBrgUIState.copy(
                snackBarMessage = "Data gagal diupdate"
            )
        }
    }
    // Fungsi untuk mereset snackBarMessage
    fun resetSnackBarMessage() {
        updateBrgUIState = updateBrgUIState.copy(snackBarMessage = null)
    }

}

fun Barang.toUIStateBrg() : BrgUIState = BrgUIState (
    barangEvent = this.toDetailBrgUiEvent(),
)