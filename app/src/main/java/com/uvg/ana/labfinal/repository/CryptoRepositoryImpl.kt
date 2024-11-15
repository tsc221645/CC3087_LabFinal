package com.uvg.ana.labfinal.repository

import com.uvg.ana.labfinal.local.AssetDao
import com.uvg.ana.labfinal.model.Asset
import com.uvg.ana.labfinal.model.AssetDetail
import com.uvg.ana.labfinal.remote.ApiService

class CryptoRepositoryImpl(
    private val apiService: ApiService,
    private val assetDao: AssetDao
) : CryptoRepository {

    override suspend fun getAssets(): List<Asset> {
        return try {
            val assets = apiService.getAssets()
            assetDao.insertAssets(assets) // Almacena los datos localmente
            assets
        } catch (e: Exception) {
            // Si hay un error de red, recupera los datos desde la base de datos local
            assetDao.getAssets()
        }
    }

    override suspend fun getAssetDetail(id: String): AssetDetail {
        return try {
            val assetDetail = apiService.getAssetDetail(id)
            assetDetail ?: throw IllegalStateException("El detalle del asset es nulo para el id: $id")
        } catch (e: Exception) {
            // Si no se puede recuperar el detalle de la red, busca en la base de datos local
            throw IllegalStateException("No se pudo obtener el detalle del asset: $id", e)
        }
    }

    override suspend fun saveAssetsOffline(assets: List<Asset>) {
        assetDao.insertAssets(assets)
    }

}
