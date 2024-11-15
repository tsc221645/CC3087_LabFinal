package com.uvg.ana.labfinal.repository

import com.uvg.ana.labfinal.model.Asset
import com.uvg.ana.labfinal.model.AssetDetail

interface CryptoRepository {
    suspend fun getAssets(): List<Asset>
    suspend fun saveAssetsOffline(assets: List<Asset>)
    suspend fun getAssetDetail(id: String): AssetDetail
}
