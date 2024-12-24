package com.example.ucp2.ui.viewmodel



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



