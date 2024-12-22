package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.BarangDao
import com.example.ucp2.data.dao.SuplierDao
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.data.entity.Suplier

@Database(entities = [Barang::class, Suplier::class], version= 1, exportSchema = false )
abstract class TokoDatabase: RoomDatabase() {

    abstract fun barangDao(): BarangDao
    abstract fun suplierDao(): SuplierDao

    companion object{
        @Volatile
        private var Instance: TokoDatabase? = null

        fun getDatabase(context: Context): TokoDatabase {
            return (Instance ?: synchronized(this)
            {
                Room.databaseBuilder(
                    context,
                    TokoDatabase::class.java,         //Class database
                    "TokoDB"              //Nama database
                )
                    .build().also { Instance = it }
            })
        }

    }
}