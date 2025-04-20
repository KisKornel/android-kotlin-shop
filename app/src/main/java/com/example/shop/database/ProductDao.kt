package com.example.shop.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.shop.models.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM products ORDER BY updatedAt DESC")
    fun getAllProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM products WHERE name LIKE '%' || :name || '%' ORDER BY updatedAt DESC")
    fun getProductByName(name: String): LiveData<List<Product>>

    @Upsert
    suspend fun addProduct(product: Product)

    @Query("UPDATE products SET name = :name, description = :description, price = :price, updatedAt = CAST(strftime('%s','now') AS INTEGER) * 1000 WHERE id = :id")
    fun updateProduct(id: Long, name: String, description: String, price: Double)

    @Query("DELETE FROM products WHERE id = :id")
    fun deleteProductById(id: Long)
}