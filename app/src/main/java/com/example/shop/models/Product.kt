package com.example.shop.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val price: Double,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: Date? = null,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val updatedAt: Date? = null
)
