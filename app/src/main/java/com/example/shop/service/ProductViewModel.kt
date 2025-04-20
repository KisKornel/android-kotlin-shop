package com.example.shop.service

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.shop.models.Product
import com.example.shop.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class ProductViewModel(app: Application): AndroidViewModel(app) {
    private val repo = ProductRepository(app)

    val getAllProducts = repo.getAllProducts

    fun getProductByName(name: String): LiveData<List<Product>> {
        val pattern = "%$name%"
        return repo.getProductByName(pattern)
    }

    fun addProduct(name: String, description: String, price: Double) {
        val now = Date()
        val product = Product(
            name = name,
            description = description,
            price = price,
            createdAt = now,
            updatedAt = now
        )
        viewModelScope.launch(Dispatchers.IO) {
            repo.addProduct(product)
        }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateProduct(product)
        }
    }

    fun deleteProductById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteProductById(id)
        }
    }
}