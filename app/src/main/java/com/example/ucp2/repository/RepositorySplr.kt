package com.example.ucp2.repository

import com.example.ucp2.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

interface RepositorySplr {
    suspend fun insertSplr(suplier: Suplier)

    fun getAllSplr(): Flow<List<Suplier>>

    fun getSplr(id: Int): Flow<Suplier>

    suspend fun deleteSplr(suplier: Suplier)

    suspend fun updateSplr(suplier: Suplier)
}