package com.example.ucp2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.repository.RepositoryBrg
import kotlinx.coroutines.launch

class InsertBarangViewModel(
    private val repositoryBrg: RepositoryBrg
) : ViewModel(){
    var uiState by mutableStateOf(BrgUIState())  //even nya akan berubah berubah

    //memperbarui state berdasarkan input pengguna
    fun updateState(barangEvent: BarangEvent) {
        uiState = uiState.copy(
            barangEvent = barangEvent,
        )
    }

    //validasi data input pengguna
    private fun validateFields(): Boolean {
        val event = uiState.barangEvent
        val errorState = FormErrorState(
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "deskripsi tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga harus berupa angka",
            stok = if (event.stok.toIntOrNull() != null) null else "Stok harus berupa angka",
            namaSuplier = if (event.namaSuplier.isNotEmpty()) null else "Nama Suplier tidak boleh kosong",
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    //Menyimpan data ke repository
    fun saveData() {
        val currentEvent = uiState.barangEvent //dipanggil karena data nya ada di mahasiswaEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryBrg.insertBrg(currentEvent.toBarangEntity())  //panggil repo
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        barangEvent = BarangEvent(),  //reset input form
                        isEntryValid = FormErrorState() //reset error state
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

    //Reset pesan Snackbar setelah ditampilkan
    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }
}

data class BrgUIState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null
)

data class BarangEvent(
    val id: Int = 0,
    val nama: String = "",
    val deskripsi: String = "",
    val harga: String = "",
    val stok: String = "",
    val namaSuplier: String = "",
)

//Menyimpan input form ke dalam entity
fun BarangEvent.toBarangEntity(): Barang = Barang(
    id= id,
    nama = nama,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok.toIntOrNull() ?: 0,       // Konversi String ke Int
    namaSuplier = namaSuplier
)


data class FormErrorState(
    val id: String? = null,
    val nama: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val namaSuplier: String? = null,
) {
    fun isValid(): Boolean {
        return id == null && nama == null && deskripsi == null && harga == null && stok == null && namaSuplier == null
    }
}



