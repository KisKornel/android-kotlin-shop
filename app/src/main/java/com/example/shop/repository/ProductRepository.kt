package com.example.shop.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.shop.database.ProductDatabase
import com.example.shop.models.Product

class ProductRepository(context: Context) {
    private val dao = ProductDatabase.getDatabase(context).getProductDao()

    val getAllProducts: LiveData<List<Product>> = dao.getAllProducts()

    fun getProductByName(name: String): LiveData<List<Product>> = dao.getProductByName("%$name%")

    suspend fun addProduct(product: Product) {
        dao.addProduct(product)
    }

    fun updateProduct(product: Product) {
        dao.updateProduct(id = product.id, name = product.name, description = product.description, price = product.price)
    }

    fun deleteProductById(id: Long) {
        dao.deleteProductById(id)
    }
}
