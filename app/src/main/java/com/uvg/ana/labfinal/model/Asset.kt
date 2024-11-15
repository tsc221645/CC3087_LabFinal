package com.uvg.ana.labfinal.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "assets")
data class Asset(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "symbol")
    val symbol: String,

    @ColumnInfo(name = "priceUsd")
    val priceUsd: String,

    @ColumnInfo(name = "changePercent24Hr")
    val changePercent24Hr: String
)


