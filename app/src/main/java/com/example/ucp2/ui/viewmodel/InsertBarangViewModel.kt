package com.example.ucp2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.repository.RepositoryBrg
import kotlinx.coroutines.launch



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



