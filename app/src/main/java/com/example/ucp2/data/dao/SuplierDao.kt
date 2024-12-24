package com.example.ucp2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

@Dao
interface SuplierDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuplier(suplier: Suplier)

    @Query("SELECT * FROM suplier ORDER BY namaSplr ASC")
    fun getAllSuplier(): Flow<List<Suplier>>

    @Query("SELECT * FROM suplier WHERE id = :id")
    fun getSuplier(id: Int): Flow<Suplier>

    @Delete
    suspend fun deleteSuplier(suplier: Suplier)

    @Update
    suspend fun updateSuplier(suplier: Suplier)
}