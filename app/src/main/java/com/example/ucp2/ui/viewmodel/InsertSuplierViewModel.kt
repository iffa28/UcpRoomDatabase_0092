package com.example.ucp2.ui.viewmodel

import com.example.ucp2.data.entity.Suplier

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