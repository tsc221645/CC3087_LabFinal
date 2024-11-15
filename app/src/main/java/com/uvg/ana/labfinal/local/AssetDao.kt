package com.uvg.ana.labfinal.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uvg.ana.labfinal.model.Asset

@Dao
interface AssetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssets(assets: List<Asset>)

    @Query("SELECT * FROM assets")
    suspend fun getAssets(): List<Asset>
}
