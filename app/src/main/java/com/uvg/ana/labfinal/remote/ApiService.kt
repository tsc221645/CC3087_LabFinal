package com.uvg.ana.labfinal.remote

import com.uvg.ana.labfinal.model.Asset
import com.uvg.ana.labfinal.model.AssetDetail
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class ApiService(private val client: HttpClient) {

    private val BASE_URL = "https://api.coincap.io/v2"

    suspend fun getAssets(): List<Asset> {
        return client.get("$BASE_URL/assets") {
            contentType(ContentType.Application.Json)
        }.body()
    }

    suspend fun getAssetDetail(id: String): AssetDetail {
        return client.get("$BASE_URL/assets/$id") {
            contentType(ContentType.Application.Json)
        }.body()
    }
}
