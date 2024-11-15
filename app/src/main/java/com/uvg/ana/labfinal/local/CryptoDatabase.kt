package com.uvg.ana.labfinal.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uvg.ana.labfinal.model.Asset

@Database(entities = [Asset::class], version = 1, exportSchema = false)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun assetDao(): AssetDao
}
