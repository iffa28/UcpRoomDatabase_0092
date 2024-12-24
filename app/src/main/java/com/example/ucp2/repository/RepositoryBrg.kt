package com.example.ucp2.repository

import com.example.ucp2.data.entity.Barang
import com.example.ucp2.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

interface RepositoryBrg {
    suspend fun insertBrg(barang: Barang)

    fun getAllBrg(): Flow<List<Barang>>

    fun getBrg(id: Int): Flow<Barang>

    suspend fun deleteBrg(barang: Barang)

    suspend fun updateBrg(barang: Barang)
}