package com.example.ucp2.repository

import com.example.ucp2.data.dao.BarangDao
import com.example.ucp2.data.entity.Barang
import kotlinx.coroutines.flow.Flow

class LocalRepositoryBrg(
    private val barangDao: BarangDao
): RepositoryBrg {
    override suspend fun insertBrg(barang: Barang) {
        barangDao.insertBarang(barang)
    }

    override fun getAllBrg(): Flow<List<Barang>> {
        return barangDao.getAllBarang()
    }

    override fun getBrg(id: Int): Flow<Barang> {
        return barangDao.getBarang(id)
    }

    override suspend fun deleteBrg(barang: Barang) {
        return barangDao.deleteBarang(barang)
    }

    override suspend fun updateBrg(barang: Barang) {
        return barangDao.updateBarang(barang)
    }
}