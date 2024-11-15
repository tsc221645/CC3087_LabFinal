package com.uvg.ana.labfinal.utils

import android.content.Context
import androidx.room.Room
import com.uvg.ana.labfinal.local.CryptoDatabase

object DatabaseProvider {
    @Volatile
    private var INSTANCE: CryptoDatabase? = null

    fun getDatabase(context: Context): CryptoDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                CryptoDatabase::class.java,
                "crypto_database"
            ).fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            instance
        }
    }
}
