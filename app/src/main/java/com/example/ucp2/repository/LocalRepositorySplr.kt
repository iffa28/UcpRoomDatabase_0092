package com.example.ucp2.repository

import com.example.ucp2.data.dao.SuplierDao
import com.example.ucp2.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

class LocalRepositorySplr(
    private val suplierDao: SuplierDao
): RepositorySplr {
    override suspend fun insertSplr(suplier: Suplier) {
        suplierDao.insertSuplier(suplier)
    }

    override fun getAllSplr(): Flow<List<Suplier>> {
        return suplierDao.getAllSuplier()
    }

    override fun getSplr(id: Int): Flow<Suplier> {
        return suplierDao.getSuplier(id)
    }

    override suspend fun deleteSplr(suplier: Suplier) {
        return suplierDao.deleteSuplier(suplier)
    }

    override suspend fun updateSplr(suplier: Suplier) {
        return suplierDao.updateSuplier(suplier)
    }

}