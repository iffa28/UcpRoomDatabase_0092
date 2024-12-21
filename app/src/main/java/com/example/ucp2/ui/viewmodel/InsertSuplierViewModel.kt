package com.example.ucp2.ui.viewmodel

import com.example.ucp2.data.entity.Suplier

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