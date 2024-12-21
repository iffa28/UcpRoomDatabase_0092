package com.example.ucp2.ui.viewmodel

import com.example.ucp2.data.entity.Suplier

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