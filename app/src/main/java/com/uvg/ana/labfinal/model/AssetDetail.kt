package com.uvg.ana.labfinal.model

data class AssetDetail(
    val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val supply: String,
    val maxSupply: String?,
    val marketCapUsd: String,
    val updatedAt: String
)