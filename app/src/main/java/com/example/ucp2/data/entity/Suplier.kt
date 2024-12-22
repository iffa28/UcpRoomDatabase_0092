package com.example.ucp2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "suplier")
data class Suplier(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val namaSplr: String,
    val kontak: String,
    val alamat: String,
)
