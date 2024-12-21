package com.example.ucp2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Suplier
import com.example.ucp2.repository.RepositorySplr
import kotlinx.coroutines.launch

class InsertSuplierViewModel(private val repositorySplr: RepositorySplr) : ViewModel() {
    var uiState by mutableStateOf(SplrUIState())

    // Fungsi untuk memperbarui state berdasarkan input pengguna
    fun updateState(suplierEvent: SuplierEvent) {
        uiState = uiState.copy(
            suplierEvent = suplierEvent,
        )
    }

    // Fungsi untuk validasi input pengguna
    private fun validateFields(): Boolean {
        val event = uiState.suplierEvent
        val errorState = FormSplrErrorState(
            namaSuplier = if (event.namaSuplier.isNotEmpty()) null else "Nama tidak boleh kosong",
            kontak = if (event.kontak.isNotEmpty()) null else "Kontak tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong"
        )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    // Fungsi untuk menyimpan data ke repository
    fun saveData() {
        val currentEvent = uiState.suplierEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositorySplr.insertSplr(currentEvent.toSuplierEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        suplierEvent = SuplierEvent(), // Reset form input
                        isEntryValid = FormSplrErrorState() // Reset error state
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data anda."
            )
        }
    }

    // Fungsi untuk mereset pesan Snackbar setelah ditampilkan
    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessage = null)
    }
}

data class SplrUIState(
    val suplierEvent: SuplierEvent = SuplierEvent(), // Event input pengguna
    val isEntryValid: FormSplrErrorState = FormSplrErrorState(), // State validasi form
    val snackBarMessage: String? = null // Pesan untuk ditampilkan di Snackbar
)

data class FormSplrErrorState(
    val namaSuplier: String? = null,
    val kontak: String? = null,
    val alamat: String? = null,
){
    fun isValid(): Boolean {
        return namaSuplier == null && kontak == null && alamat == null
    }
}

fun SuplierEvent.toSuplierEntity(): Suplier = Suplier(
    namaSuplier = namaSuplier,
    kontak = kontak,
    alamat = alamat
)

data class SuplierEvent(
    val namaSuplier: String = "",
    val kontak: String = "",
    val alamat: String = "",
)